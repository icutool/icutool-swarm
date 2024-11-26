package cn.icutool.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xietao
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeeklyReportReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 工作主题
     */
    @NotNull
    private String subject;

    /**
     *  工作内容
     */
    @NotNull
    private String content;
}
