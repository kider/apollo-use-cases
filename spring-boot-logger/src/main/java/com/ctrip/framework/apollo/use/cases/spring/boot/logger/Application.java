package com.ctrip.framework.apollo.use.cases.spring.boot.logger;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.bean.LogConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kl on 2018/6/25.
 * Content :动态日志实例
 */
@Configuration
@SpringBootApplication
@EnableApolloConfig
public class Application {

    @Bean
    public LogConfig getLogConfig() {
        return new LogConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
