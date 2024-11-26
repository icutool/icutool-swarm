package cn.icutool.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoReplyReq {
    @NotNull
    private Integer id;

    @NotBlank
    private String question;

    @NotBlank
    private String answer;
}
