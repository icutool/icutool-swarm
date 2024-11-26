package cn.icutool.config;

import cn.icutool.service.AutoReplyService;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.init.ResourceReader;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author xietao
 */
@Slf4j
@Configuration
public class CacheConfig {
    public static final Map<String,String> HOT_REPLY_MAP = new ConcurrentHashMap<>();
    @Resource
    private AutoReplyService autoReplyService;

    /**
     * 配置缓存管理器
     *
     * @return 缓存管理器
     */
    @Bean("caffeineCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(8, TimeUnit.HOURS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
        return cacheManager;
    }

    @PostConstruct
    public void initHotAutoRespData() {
        autoReplyService.initHotAutoRespData();
    }

    @Bean("searcher")
    public Searcher searcher() throws IOException {
        InputStream inputStream = ResourceReader.class
                .getClassLoader()
                .getResourceAsStream("file/ip2region.xdb");
        File tempFile = File.createTempFile("ip2region", ".xdb");
        tempFile.deleteOnExit();
        // 将资源写入临时文件
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        String dbPath = tempFile.getAbsolutePath();
        byte[] vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        return Searcher.newWithVectorIndex(dbPath, vIndex);
    }

}