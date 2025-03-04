package utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    public ConfigLoader() {
        properties =  PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getConfig(String key) {
        return properties.getProperty(key);
    }


}
