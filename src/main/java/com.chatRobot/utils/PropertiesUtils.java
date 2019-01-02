package com.chatRobot.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

//
public class PropertiesUtils {
    /**
     *
     */
    public static String getThe(String key) {
        Properties properties=new Properties();
        try {
            properties.load(new InputStreamReader(Object.class.getResourceAsStream("/the.properties"),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);

    }


}