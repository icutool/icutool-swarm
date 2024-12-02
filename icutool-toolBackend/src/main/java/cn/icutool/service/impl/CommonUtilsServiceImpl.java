package cn.icutool.service.impl;

import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.service.CommonUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CommonUtilsServiceImpl implements CommonUtilsService {

    private final Searcher searcher;

    public CommonUtilsServiceImpl(Searcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public IpInfoDTO searchIpInfo(String ip) {
        IpInfoDTO ipInfoDTO = new IpInfoDTO();
        String region = null;
        try {
            region = searcher.search(ip);
            String[] ipSplit = region.split("\\|");
            ipInfoDTO.setCountry(ipSplit[0]);
            ipInfoDTO.setRegion(ipSplit[1]);
            ipInfoDTO.setProvince(ipSplit[2]);
            ipInfoDTO.setCity(ipSplit[3]);
            ipInfoDTO.setIsp(ipSplit[4]);
            if ("内网IP".equals(ipInfoDTO.getIsp())) {
                ipInfoDTO.setCountry("内网IP");
            }
        } catch (Exception e) {
            log.error("ip信息查询失败:{}", e.getMessage(), e);
        }
        return ipInfoDTO;
    }
}
