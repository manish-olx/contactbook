package com.myproject.contactbook.util;

import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class LoggerUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void error(Map<String, Object> logData) {
        logger.error("", getArgs(logData));
    }

    public void warn(Map<String, Object> logData) {
        logger.warn("", getArgs(logData));
    }

    public void debug(Map<String, Object> logData) {
        logger.debug("", getArgs(logData));
    }

    public void info(Map<String, Object> logData) {
        logger.info("", getArgs(logData));
    }

    public void info(String logData) {
        logger.info(logData);
    }

    private Object[] getArgs(Map<String, Object> logData) {
        List<Object> args = logData.entrySet().stream()
                .map(e -> StructuredArguments.value(e.getKey(), e.getValue()))
                .collect(toList());

        args.add(StructuredArguments.value("type", "applog"));
        return args.toArray();
    }
}
