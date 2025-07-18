package com.wy.synthetic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {

    @Value("${executor.core-pool-size}")
    private int corePool;

    @Value("${executor.max-pool-size}")
    private int maxPool;

    @Value("${executor.queue-capacity}")
    private int queueCapacity;

    @Bean(name = "commandExecutor")
    public ThreadPoolTaskExecutor commandExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(corePool);
        exec.setMaxPoolSize(maxPool);
        exec.setQueueCapacity(queueCapacity);
        exec.setThreadNamePrefix("cmd-exec-");
        exec.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
        exec.initialize();
        return exec;
    }
}
