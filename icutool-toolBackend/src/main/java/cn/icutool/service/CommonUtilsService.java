package cn.icutool.service;

import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.domain.vo.response.AjaxResult;

public interface CommonUtilsService {
    IpInfoDTO searchIpInfo(String ip);
}
