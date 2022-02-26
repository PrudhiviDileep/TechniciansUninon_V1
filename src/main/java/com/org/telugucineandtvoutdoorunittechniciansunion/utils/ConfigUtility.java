package com.org.telugucineandtvoutdoorunittechniciansunion.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:properties.properties")
public class ConfigUtility {
	@Autowired
    private Environment env;
	
    public String getProperty(String pPropertyKey) {	
        return env.getProperty(pPropertyKey);
    }
}
