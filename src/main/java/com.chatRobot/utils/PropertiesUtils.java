package com.chatRobot.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

//
public class PropertiesUtils {
    /**
     *配置文件获取
     */
    private  static Properties properties;

    public PropertiesUtils() {

    }


    public static String getThe(String key) {
        if(properties==null){
            properties =new Properties();

        }

        try {
            properties.load(new InputStreamReader(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("the.properties"),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);

    }


}