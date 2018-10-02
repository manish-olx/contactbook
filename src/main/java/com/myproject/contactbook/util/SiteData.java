package com.myproject.contactbook.util;

import com.google.common.collect.ImmutableMap;
import com.myproject.contactbook.configuration.ApplicationConfig;
import com.myproject.contactbook.exception.HostNotAllowedException;
import com.myproject.contactbook.exception.NoSiteCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component()
public class SiteData {

    @Autowired
    private RequestUtil requestUtil;
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private LoggerUtil loggerUtil;
    @Autowired
    private NoSiteCodeException noSiteCodeException;
    @Autowired
    private HostNotAllowedException hostNotAllowedException;

    public String getCode() throws NoSiteCodeException {
        Map<String, String> siteCodes = applicationConfig.getSiteCodes();

        HttpServletRequest request = requestUtil.getHttpServletRequest();

        if (request == null) {
            throw noSiteCodeException;
        }
        String serverName = "";

        try {
            serverName = request.getServerName();
            if (siteCodes.containsKey(serverName)) {
                return siteCodes.get(serverName);
            }
        } catch (Exception e) {
            loggerUtil.error(ImmutableMap.of(
                    "message", "No site code found",
                    "site", serverName
            ));
            throw noSiteCodeException;
        }
        throw noSiteCodeException;
    }
}
