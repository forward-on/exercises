package com.worthy.ly.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ly on 2018/3/7.
 */
public class PropertiesUtil {

    private static Properties properties;

    public static void getValue(String key) {
        properties = new Properties();
        InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/keyvalue.properties");
        try {
            properties.load(inputStream);
            String value = properties.getProperty(key);
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        getValue("myName");
        getValue("1");
    }

}
