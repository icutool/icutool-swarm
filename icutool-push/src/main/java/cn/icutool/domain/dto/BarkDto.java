package cn.icutool.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BarkDto {

    private String title;

    private String body;

    private String device_key;

    private Integer badge;

    private String sound;

    private String icon;

    private String group;

    private String url;

    public BarkDto(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
