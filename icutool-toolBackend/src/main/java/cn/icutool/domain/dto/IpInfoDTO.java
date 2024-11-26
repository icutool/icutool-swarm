package cn.icutool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpInfoDTO {
    private String country;
    private String region;
    private String province;
    private String city;
    private String isp;
}
