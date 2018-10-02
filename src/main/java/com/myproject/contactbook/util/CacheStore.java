package com.myproject.contactbook.util;

import com.myproject.contactbook.exception.NoSiteCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheStore {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SiteData siteData;

    public void add(String group, String key, Object data) throws NoSiteCodeException {
        String mainKey = getKey(group, key);
        redisTemplate.opsForValue().set(mainKey, (String) data);
    }

    public void add(String group, String key, Object data, long timeout, TimeUnit unit) throws NoSiteCodeException {
        String mainKey = getKey(group, key);
        redisTemplate.opsForValue().set(mainKey, (String) data, timeout, unit);
    }

    public void delete(String group, String key) {
        try {
            String mainKey = getKey(group, key);
            redisTemplate.opsForValue().set(mainKey, "");
        } catch (NoSiteCodeException e) {
        }

    }

    public Boolean contains(String group, String key) throws NoSiteCodeException {
        String mainKey = getKey(group, key);
        String data = redisTemplate.opsForValue().get(mainKey);
        if (data == null || data.equals("")) {
            return false;
        }
        return true;
    }

    public String get(String group, String key) throws NoSiteCodeException {
        String mainKey = getKey(group, key);
        return redisTemplate.opsForValue().get(mainKey);

    }

    private String getKey(String parent, String key) throws NoSiteCodeException {
        return siteData.getCode() + "_" + parent + "_" + key;
    }
}
