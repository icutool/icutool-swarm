package cn.icutool.service;

import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.common.domain.vo.response.AjaxResult;

public interface CommonUtilsService {
    IpInfoDTO searchIpInfo(String ip);
}
