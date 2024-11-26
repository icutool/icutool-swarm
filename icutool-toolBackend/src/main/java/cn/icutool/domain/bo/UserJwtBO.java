package cn.icutool.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJwtBO {
    private Long id;
    private String username;
}
