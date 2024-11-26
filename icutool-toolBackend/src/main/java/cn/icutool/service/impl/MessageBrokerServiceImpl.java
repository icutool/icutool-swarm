package cn.icutool.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.icutool.config.MinioConfiguration;
import cn.icutool.constant.MinioFilePathConst;
import cn.icutool.domain.enums.MessageTypeEnum;
import cn.icutool.dao.MessageBrokerDao;
import cn.icutool.dao.UserDao;
import cn.icutool.domain.dto.PageDTO;
import cn.icutool.domain.entity.MessageBroker;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.enums.WSRespTypeEnum;
import cn.icutool.domain.enums.WxMsgTypeEnum;
import cn.icutool.domain.vo.request.MessageBrokerPicReq;
import cn.icutool.domain.vo.request.MessageBrokerReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.MessageBrokerService;
import cn.icutool.service.WebSocketService;
import cn.icutool.utils.MinioUtil;
import cn.icutool.utils.SecurityUtils;
import cn.icutool.utils.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xietao
 * @since 2024-05-22 23:57:29
 */
@Service
@Slf4j
public class MessageBrokerServiceImpl implements MessageBrokerService {
    @Resource
    private MinioConfiguration minioConfiguration;
    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MessageBrokerDao messageBrokerDao;
    @Resource
    private UserDao userDao;
    @Resource
    @Lazy
    private WebSocketService webSocketService;
    @Override
    public WxMpXmlOutMessage wxMsgBroker(WxMpXmlMessage wxMessage, WxMpService weixinService, String content) {
        log.debug("收到消息记录");
        User user = userDao.selectOneByOpenId(wxMessage.getFromUser());
        if (ObjectUtils.isEmpty(user)){
            return new TextBuilder().build("您不是该功能的用户,请访问网站进行注册!\nhttps://icutool.cn", wxMessage, weixinService);
        }
        if ("image".equals(wxMessage.getMsgType())) {
            String picUrl = wxMessage.getPicUrl();
            // fileDownloadExecutor.execute(() -> downloadMaterialAndSaveMinio(picUrl));
            try {
                String fileMinioPath = saveMinio(picUrl);
                savePicInfo(fileMinioPath, user);
            } catch (IOException e) {
                log.error("图片消息记录提交失败:{}",e.getMessage(), e);
                return new TextBuilder().build("图片消息记录提交失败", wxMessage, weixinService);
            }
            webSocketService.sendMsg(WSRespTypeEnum.NEW_MSG, user.getId());
            return new TextBuilder().build("图片消息记录提交完成", wxMessage, weixinService);
        } else {
            MessageBroker messageBroker = MessageBroker.builder()
                    .content(content.replace(WxMsgTypeEnum.MESSAGE_BROKER.getPrefix(),""))
                    .userId(user.getId())
                    .msgType(MessageTypeEnum.TEXT.getCode())
                    .build();
            messageBrokerDao.save(messageBroker);
            webSocketService.sendMsg(WSRespTypeEnum.NEW_MSG, user.getId());
            return new TextBuilder().build("消息记录提交完成", wxMessage, weixinService);
        }
    }

    private void savePicInfo(String fileMinioPath, User user) {
        MessageBroker messageBroker = MessageBroker.builder()
                .content(fileMinioPath)
                .userId(user.getId())
                .msgType(MessageTypeEnum.IMAGE.getCode())
                .build();
        messageBrokerDao.save(messageBroker);
    }

    private String saveMinio(String picUrl) throws IOException {
        File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".jpg");
        String minioFilePath = MinioFilePathConst.WX_IMAGE_MESSAGE_PATH + tempFile.getName();
        HttpUtil.downloadFileFromUrl(picUrl,tempFile);
        minioUtil.uploadFile(tempFile,minioConfiguration.getBucketName(),minioFilePath);
        boolean delete = tempFile.delete();
        if (!delete){
            log.error("删除临时文件失败,路径:{}",tempFile.getAbsolutePath());
        }
        return minioFilePath;
    }
    private String saveMinio(MessageBrokerPicReq messageBrokerPicReq) throws IOException {
        String minioFilePath = MinioFilePathConst.WX_IMAGE_MESSAGE_PATH + messageBrokerPicReq.getName();
        minioUtil.uploadFile(messageBrokerPicReq.getFile(),minioConfiguration.getBucketName(),minioFilePath);
        return minioFilePath;
    }

    @Override
    public AjaxResult page(Integer pageNum, Integer pageSize, String keyword) {
        log.debug("查询消息记录列表");
        pageNum = (pageNum -1)* pageSize;
        List<MessageBroker> messageBrokerList = messageBrokerDao.queryPage(pageNum, pageSize, keyword, SecurityUtils.getUserId());
        messageBrokerList.stream()
                .filter(messageBroker -> messageBroker.getMsgType() == MessageTypeEnum.IMAGE.getCode())
                .forEach(messageBroker -> messageBroker.setContent(minioUtil.getUrl(messageBroker.getContent(),1, TimeUnit.HOURS)));
        int count = messageBrokerDao.count(keyword, SecurityUtils.getUserId());
        PageDTO<MessageBroker> resp = new PageDTO<>(messageBrokerList, count);
        return AjaxResult.success("分页查询成功",resp);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult edit(MessageBrokerReq messageBrokerReq) {
        if (ObjectUtils.isEmpty(messageBrokerReq.getId())){
            return AjaxResult.error("消息记录ID不能为空");
        }
        MessageBroker messageBroker = new MessageBroker();
        BeanUtils.copyProperties(messageBrokerReq,messageBroker);
        messageBroker.setUserId(SecurityUtils.getUserId());
        messageBrokerDao.update(messageBroker);
        return AjaxResult.success("修改成功");
    }

    @Override
    public AjaxResult delete(List<Integer> ids) {
        messageBrokerDao.removeByIds(ids);
        return AjaxResult.success("删除成功");
    }

    @Override
    public AjaxResult add(MessageBrokerReq messageBrokerReq) {
        MessageBroker messageBroker = MessageBroker.builder()
                .content(messageBrokerReq.getContent())
                .userId(SecurityUtils.getUserId())
                .msgType(MessageTypeEnum.TEXT.getCode())
                .build();
        messageBrokerDao.save(messageBroker);
        return AjaxResult.success("新增成功");
    }

    @Override
    public AjaxResult addPic(MessageBrokerPicReq messageBrokerPicReq) throws IOException {
        String fileName = messageBrokerPicReq.getName();
        String prefix = FileUtil.getPrefix(fileName);
        String suffix = FileUtil.getSuffix(fileName);
        messageBrokerPicReq.setName(prefix+"_"+System.currentTimeMillis()+"."+suffix);
        String fileMinioPath = saveMinio(messageBrokerPicReq);
        savePicInfo(fileMinioPath, SecurityUtils.getLoginUser().getUser());
        return AjaxResult.success("图片消息新增成功");
    }

    @Override
    public WxMpXmlOutMessage listMsg(WxMpXmlMessage wxMessage, WxMpService weixinService, String content) {
        User user = userDao.selectOneByOpenId(wxMessage.getFromUser());
        if (ObjectUtils.isEmpty(user)){
            return new TextBuilder().build("您不是该功能的用户,请访问网站进行注册!\nhttps://icutool.cn", wxMessage, weixinService);
        }
        String[] split = content.split(" ");
        if (split.length != 4){
            return new TextBuilder().build("获取消息 请参考格式[list 页码 条数 关键词] \n例如 list 1 10 无", wxMessage, weixinService);
        }
        int pageNum = Integer.parseInt(split[1]);
        pageNum = (pageNum -1)* Integer.parseInt(split[2]);
        int pageSize = Integer.parseInt(split[2]);
        if (pageNum <0 || pageSize<0 || pageSize>20) {
            return new TextBuilder().build("页码或条数不合法 页码条数需要大于0 条数需要小于20", wxMessage, weixinService);
        }
        String keyword = "无".equals(split[3]) ? "" : split[3];
        int count = messageBrokerDao.count(keyword, user.getId());
        List<MessageBroker> messageBrokerList = messageBrokerDao.queryPage(pageNum, pageSize, keyword, user.getId());
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("总条数：%d 总页数：%d",count,(int) Math.ceil((double) count / pageSize))).append("\n");
        AtomicInteger line = new AtomicInteger(1);
        messageBrokerList.stream()
                .map(MessageBroker::getContent)
                .forEach(text -> sb.append(line.getAndIncrement()).append(":").append(text).append("\n"));
        return new TextBuilder().build(sb.toString(), wxMessage, weixinService);
    }


}
