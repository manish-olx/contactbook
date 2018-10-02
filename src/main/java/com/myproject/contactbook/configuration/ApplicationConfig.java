package com.myproject.contactbook.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {


    private HashMap<String, String> sites;
    private HashMap<String, String> exception;

    public HashMap<String, String> getSites() {
        return sites;
    }

    public void setSites(HashMap<String, String> sites) {
        this.sites = sites;
    }

    public HashMap<String, String> getException() {
        return exception;
    }

    public void setException(HashMap<String, String> exception) {
        this.exception = exception;
    }

    public HashMap<String, String> getSiteCodes() {
        return sites;
    }
}