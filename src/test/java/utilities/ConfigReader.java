package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger log = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    // declaring as static because it should start before the class initialization
    static {
        try {
            // reads the config.properties file using the property class
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            log.info("config.properties loaded successfully");
        } catch (IOException e) {
            log.error("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        log.debug("Config key: " + key + " = " + value);
        return value;
    }
}
