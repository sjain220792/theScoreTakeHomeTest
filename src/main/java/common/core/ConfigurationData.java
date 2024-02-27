package common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationData {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationData.class);
    private static ConfigurationData instance;
    public DeviceData deviceData;
    private final Properties prop = new Properties();

    private ConfigurationData() {
        logger.info("Loading and reading from properties files");
        String filename = "ConfigurationData.properties";
        try {
            InputStream is = getClass().getResourceAsStream(filename);
            prop.load(is);
        } catch (IOException e){
            throw new RuntimeException("Unable to load properties file: " + filename, e);
        } catch (Exception e){
            throw new RuntimeException("An error occurred when reading properties file: ", e);
        }
    }
    public static ConfigurationData getInstance(){
        if (instance == null) {
            instance = new ConfigurationData();
        }
    return instance;
    }

    public String getBaseUrlPortal(){return prop.getProperty("baseURL");}
    public String getOrientation(){return deviceData.getMobileDeviceOrientation();}

}
