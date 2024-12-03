package cn.icutool.domain.dto.flyBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xietao
 * @since 2024-12-03 23:07:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgTypeAndContent {
    private String tag;
    private String text;
}
