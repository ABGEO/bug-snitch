package dev.abgeo.bugsnitch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Defines Async Task Executor.
 *
 * @author Temuri Takalandze
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

        executor.setCorePoolSize(POOL_SIZE);
        executor.setMaxPoolSize(POOL_SIZE);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("BugSnitchThread-");
        executor.initialize();

        return executor;
    }

}
