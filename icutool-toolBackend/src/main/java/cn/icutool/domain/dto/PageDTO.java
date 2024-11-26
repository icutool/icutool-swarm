package cn.icutool.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author xietao
 */
@AllArgsConstructor
@Data
public class PageDTO<T> {
    private List<T> data;
    private long count;
}
