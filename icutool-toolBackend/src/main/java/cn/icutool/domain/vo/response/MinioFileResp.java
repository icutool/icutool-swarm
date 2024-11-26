package cn.icutool.domain.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinioFileResp {
    private String fileName;
    private String url;
    private Long time;
    private TimeUnit timeUnit;
}
