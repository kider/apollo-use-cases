package com.ctrip.framework.apollo.use.cases.spring.boot.logger.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 日志ConfigBean
 */
@XmlRootElement(name = "logConfig")
public class LogConfig {

    private String loggingLevelInfo;

    @XmlElement(name = "loggingLevelInfo")
    public String getLoggingLevelInfo() {
        return loggingLevelInfo;
    }

    public void setLoggingLevelInfo(String loggingLevelInfo) {
        this.loggingLevelInfo = loggingLevelInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"loggingLevelInfo\":\"")
                .append(loggingLevelInfo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
