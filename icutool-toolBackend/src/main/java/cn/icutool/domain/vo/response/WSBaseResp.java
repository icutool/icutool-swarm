package cn.icutool.domain.vo.response;

import lombok.Data;
/**
 * @author xietao
 */
@Data
public class WSBaseResp<T> {
    private Integer type;
    private T data;
}
