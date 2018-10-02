package com.myproject.contactbook.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = "prototype")
public class BodyWrapper implements Serializable {
    private Map<Object, Object> body = new HashMap<Object, Object>();

    public void append(Object key, Object value) {
        body.put(key, value);
    }

    public Object get(Object key) {
        return body.get(key);
    }

    public Map<Object, Object> getBody() {
        return body;
    }
}
