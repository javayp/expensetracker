package com.app.penpaid.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:errorCode.properties")
public class PropertiesReaderUtil {

    private final Environment environment;

    @Autowired
    public PropertiesReaderUtil(Environment environment) {
        this.environment = environment;
    }

    public String getValue(String key){
        return environment.getProperty(key);
    }
}
