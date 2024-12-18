package cn.icutool.service;

import cn.icutool.domain.dto.BarkDto;

/**
 * @author xietao
 * @since 2024-12-03 22:47:23
 */
public interface PlatFormService {
    void sendMsgToPlatForm(String msg);
    void sendMsgToPlatForm(BarkDto barkDto);
}
