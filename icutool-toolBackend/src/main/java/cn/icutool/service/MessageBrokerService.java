package cn.icutool.service;

import cn.icutool.domain.vo.request.MessageBrokerPicReq;
import cn.icutool.domain.vo.request.MessageBrokerReq;
import cn.icutool.domain.vo.response.AjaxResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.io.IOException;
import java.util.List;

/**
 * (MessageBroker)表服务接口
 * @author xietao
 * @since 2024-05-22 23:57:29
 */
public interface MessageBrokerService {

    WxMpXmlOutMessage wxMsgBroker(WxMpXmlMessage wxMessage, WxMpService weixinService, String content);

    AjaxResult page(Integer pageNum, Integer pageSize, String keyword);

    AjaxResult edit(MessageBrokerReq messageBrokerReq);

    AjaxResult delete(List<Integer> ids);

    AjaxResult add(MessageBrokerReq messageBrokerReq);

    AjaxResult addPic(MessageBrokerPicReq messageBrokerPicReq) throws IOException;

    WxMpXmlOutMessage listMsg(WxMpXmlMessage wxMessage, WxMpService weixinService, String content);
}
