package com.circuitBreakerSamples.albumSample.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfiguration {
    CircuitBreakerConfig circuitBreakerConfig =
            CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)
                    .waitDurationInOpenState(Duration.ofMillis(1000))
                    .slidingWindowSize(2)
                    .build();
    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(4))
            .build();

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory>
    globalCustomConfiguration() {
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory>
    globalCustomConfiguration1() {
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory>
    globalCustomConfiguration2() {
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
                        .timeLimiterConfig(timeLimiterConfig).build(),
                "circuitBreaker1", "circuitBreaker2", "circuitBreaker3");
    }

}
