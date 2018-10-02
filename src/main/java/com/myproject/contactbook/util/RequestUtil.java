package com.myproject.contactbook.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestUtil {

    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
        if (attribs instanceof ServletRequestAttributes) {
            HttpServletRequest request = (HttpServletRequest) ((ServletRequestAttributes) attribs).getRequest();
            return request;
        }
        return null;
    }

}
