package cn.icutool.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author xietao
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageBrokerPicReq {
    @NotNull
    private MultipartFile file;
    @NotNull
    private String name;
}
