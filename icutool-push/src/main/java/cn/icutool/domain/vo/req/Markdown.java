package cn.icutool.domain.vo.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author no1plus
 * @since 2024-12-18 21:50:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Markdown {
    private String title;
    private String text;
}
