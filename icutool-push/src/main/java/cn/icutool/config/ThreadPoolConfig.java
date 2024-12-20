package cn.icutool.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean("pushJobThread")
    public ThreadPoolTaskExecutor pushJobThreadPool() {
        return createThreadPool(Runtime.getRuntime().availableProcessors() * 4, 500, "pushJobThread");
    }

    private ThreadPoolTaskExecutor createThreadPool(int size, int queueCapacity, String poolName) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(size);
        threadPoolTaskExecutor.setMaxPoolSize(size);
        threadPoolTaskExecutor.setKeepAliveSeconds(0);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        if (StringUtils.isNotBlank(poolName)) {
            threadPoolTaskExecutor.setThreadFactory(r -> {
                Thread thread = new Thread(r);
                thread.setName(poolName + thread.getId());
                thread.setDaemon(true);
                return thread;
            });
        }
        return threadPoolTaskExecutor;
    }
}
