package cn.icutool.domain.dto.flyBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xietao
 * @since 2024-12-03 23:08:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewContent {
    private String title;
    private List<MsgTypeAndContent> content;
}
