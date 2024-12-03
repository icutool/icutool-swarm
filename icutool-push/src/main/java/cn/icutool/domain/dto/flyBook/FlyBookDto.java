package cn.icutool.domain.dto.flyBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xietao
 * @since 2024-12-03 23:01:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlyBookDto {
    private String msg_type;
    private ContentBody content;
}
