package cn.icutool.websocket;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import cn.icutool.domain.enums.WSReqTypeEnum;
import cn.icutool.domain.vo.response.WSBaseResp;
import cn.icutool.domain.vo.ws.WSBaseReq;
import cn.icutool.service.WebSocketService;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Slf4j
// @Sharable表示一个 ChannelHandler 可以在多个 ChannelPipeline 中共享使用
@Sharable
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private WebSocketService webSocketService;

    // 当web客户端连接后，触发该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.webSocketService = getService();
        log.info("客户端连接成功！");
    }

    private WebSocketService getService() {
        return SpringUtil.getBean(WebSocketService.class);
    }

    // 客户端离线
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        userOffLine(ctx);
    }

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        userOffLine(ctx);
    }

    private void userOffLine(ChannelHandlerContext ctx) {
        this.webSocketService.removed(ctx.channel());
        ctx.channel().close();
    }

    /**
     * 心跳检查
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 关闭用户的连接
                userOffLine(ctx);
            }
        } else if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            String token = NettyUtil.getAttr(ctx.channel(), NettyUtil.TOKEN);
            if (StringUtils.isNotBlank(token)) {
                Long uid = NettyUtil.getAttr(ctx.channel(), NettyUtil.UID);
                log.info("用户连接登录成功，uid：{} token = {}",uid, token);
                this.webSocketService.add(ctx.channel());
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    // 处理异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("异常发生，异常消息:{}", cause.getMessage(), cause);
        ctx.channel().close();
    }

    // 读取客户端发送的请求报文
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        WSBaseReq wsBaseReq = JSONUtil.toBean(msg.text(), WSBaseReq.class);
        WSReqTypeEnum wsReqTypeEnum = WSReqTypeEnum.of(wsBaseReq.getType());
        switch (wsReqTypeEnum) {
            case LOGIN:
                this.webSocketService.handleLoginReq(ctx.channel());
                log.info("请求code = {}",  msg.text());
                break;
            case AUTHORIZE:
                this.webSocketService.handleTokenLogin(ctx.channel(), wsBaseReq);
                break;
            case LOGOUT:
                this.webSocketService.removed(ctx.channel());
                break;
            case BIND_CODE:
                this.webSocketService.handleBindCodeReq(ctx.channel(), wsBaseReq);
                break;
            case HEARTBEAT:
                Long uid = NettyUtil.getAttr(ctx.channel(), NettyUtil.UID);
                JSONObject info = new JSONObject();
                info.put("uid", uid);
                WSBaseResp<JSONObject> resp = new WSBaseResp<>();
                resp.setType(WSReqTypeEnum.HEARTBEAT.getType());
                resp.setData(info);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(resp)));
                break;
            case TEST:
                this.webSocketService.test(ctx.channel(), wsBaseReq);
                break;
            default:
                log.info("未知类型");
        }
    }
}
