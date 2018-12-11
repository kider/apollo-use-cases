package com.ctrip.framework.apollo.use.cases.spring.boot.logger.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ctrip.framework.apollo.ConfigFileChangeListener;
import com.ctrip.framework.apollo.model.ConfigFileChangeEvent;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.LoggerJsonConfiguration;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.LoggerXMLConfiguration;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.bean.LogConfig;
import com.ctrip.framework.apollo.use.cases.spring.boot.logger.tools.JaxbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

/**
 * 日志配置Listener
 */
@Component
public class LogConfigListener implements ConfigFileChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(LogConfigListener.class);

    @Autowired
    private LoggingSystem loggingSystem;

    @Override
    public void onChange(ConfigFileChangeEvent configFileChangeEvent) {

        String content = configFileChangeEvent.getNewValue();

        logger.info("log config:{}", content);

        LogConfig logConfig = null;

        String nameSpace = configFileChangeEvent.getNamespace();

        //截取 nameSpace
        if (nameSpace.contains(".")) {
            nameSpace = nameSpace.split("\\.")[0];
        }

        //json
        if (LoggerJsonConfiguration.LOGGER_JSON_NAMESPACE.equals(nameSpace)) {
            logConfig = JSON.parseObject(content, new TypeReference<LogConfig>() {
            });
        }
        //xml
        else if (LoggerXMLConfiguration.LOGGER_XML_NAMESPACE.equals(nameSpace)) {
            logConfig = JaxbUtil.converyToJavaBean(content, LogConfig.class);
        }

        if (null == logConfig) {
            logger.error("没有查到相应NameSpace内容,请检查NameSpace配置是否存在：{}", nameSpace);
            return;
        }

        LogLevel level = LogLevel.valueOf(logConfig.getLoggingLevelInfo().toUpperCase());

        loggingSystem.setLogLevel("", level);

        logger.info("{}:{}", "logging.level.", logConfig.getLoggingLevelInfo().toUpperCase());
    }
}
