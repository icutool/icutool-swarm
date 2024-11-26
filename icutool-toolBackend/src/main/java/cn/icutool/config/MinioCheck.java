package cn.icutool.config;

import cn.icutool.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 在项目启动时检测minio桶是否存在,不存在就创建
 * @author xietao
 */
@Component
@Slf4j
public class MinioCheck {
    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MinioConfiguration minioConfiguration;
    @PostConstruct
    public void check(){
        try {
            String bucketName = minioConfiguration.getBucketName();
            create(bucketName);
        } catch (Exception e) {
            log.error("桶创建失败 {}", e.getMessage());
        }
    }

    private void create(String bucketName) throws Exception {
        boolean checked = minioUtil.checkBucketExist(bucketName);
        if (!checked){
            minioUtil.createBucket(bucketName);
        } else {
            log.info("桶 {} 已存在",bucketName);
        }
    }
}
