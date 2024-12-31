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
    private String ip;

    public void setAll(String text) {
        this.setCountry(text);
        this.setRegion(text);
        this.setProvince(text);
        this.setCity(text);
        this.setIsp(text);
        this.setIp(text);
    }
}
