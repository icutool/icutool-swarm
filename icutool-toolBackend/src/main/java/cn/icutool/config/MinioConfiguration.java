package cn.icutool.config;

import io.minio.MinioClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xietao
 * @Desc: minio配置类
 **/
@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
@Slf4j
public class MinioConfiguration {
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
    /**
     * Minio 服务端 api地址
     */
    private String url;
    private String openUrl;

    /**
     * icutool存储桶名字
     */
    private String bucketName;

    /**
     * 外链图片有效期
     */
    private int expired;

    /**
     * 构建 操作Minio的客户端
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
}
