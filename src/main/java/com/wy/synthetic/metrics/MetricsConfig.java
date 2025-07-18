package com.wy.synthetic.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import jakarta.annotation.PostConstruct;

@Configuration
public class MetricsConfig {

    @Autowired
    private MeterRegistry registry;

    @Autowired
    private ThreadPoolTaskExecutor commandExecutor;

    @PostConstruct
    public void initMetrics() {
        Gauge.builder("commands.queue.size", () -> commandExecutor.getThreadPoolExecutor().getQueue().size())
                .description("Current number of tasks in queue")
                .register(registry);

    }
}