package utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    public DataLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if(dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getData(String key) {
        return properties.getProperty(key);
    }
}
