package com.ctrip.framework.apollo.use.cases.spring.boot.logger;

import java.util.concurrent.Executors;

import com.ctrip.framework.apollo.use.cases.spring.boot.logger.bean.LogConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by kl on 2018/6/25.
 * Content :
 */
@Service
public class PrintLogger {
    private static Logger logger = LoggerFactory.getLogger(PrintLogger.class);

    @Autowired
    private LogConfig logConfig;

    @PostConstruct
    public void printLogger() throws Exception {
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                System.out.println("logger.level." + logConfig.getLoggingLevelInfo());
                logger.info("我是info级别日志");
                logger.error("我是error级别日志");
                logger.warn("我是warn级别日志");
                logger.debug("我是debug级别日志");
                TimeUnit.SECONDS.sleep(1);
            }
        });
    }
}
