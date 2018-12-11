package com.ctrip.framework.apollo.use.cases.spring.boot.logger;

import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigFileChangeEvent;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.listener.LogConfigListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * JSON格式日志配置
 */
@Service
public class LoggerJsonConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(LoggerJsonConfiguration.class);
    public static final String LOGGER_JSON_NAMESPACE = "log-json";

    /**
     * 非properties的配置
     */
    private static ConfigFile configFile = ConfigService.getConfigFile(LOGGER_JSON_NAMESPACE, ConfigFileFormat.JSON);

    @Autowired
    private LogConfigListener logConfigListener;

    @PostConstruct
    private void refreshLoggingLevels() {

        logger.info("first init log json config start");
        String content = configFile.getContent();
        ConfigFileChangeEvent configFileChangeEvent = new ConfigFileChangeEvent(LOGGER_JSON_NAMESPACE, content, content, null);
        logConfigListener.onChange(configFileChangeEvent);
        logger.info("first init log json config end");

        //LogConfigListener 注意这是一个单例，会有并发问题
        configFile.addChangeListener(logConfigListener);
    }

}
