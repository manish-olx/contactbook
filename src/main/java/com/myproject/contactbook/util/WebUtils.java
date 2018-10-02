package com.myproject.contactbook.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebUtils {

    private HttpServletRequest request;

    private String traceId;

    public String getTraceId() {
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
        }
        return traceId;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public String getUserAgent() {
        return request.getHeader("User-Agent");
    }

}