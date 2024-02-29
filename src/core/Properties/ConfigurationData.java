package core.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import core.Driver.DeviceData;

public class ConfigurationData {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationData.class);
    private static ConfigurationData instance;
    public DeviceData deviceData;
    private final Properties prop = new Properties();

    public ConfigurationData() {
        deviceData = new DeviceData();
        logger.info("Loading and reading from properties files");
        String configurationFile = "ConfigurationData.properties";
        try {
            InputStream is = getClass().getResourceAsStream(configurationFile);
            prop.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties file: " + configurationFile, e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when reading properties file: ", e);
        }
    }

    public static ConfigurationData getInstance() {
        if (instance == null) {
            instance = new ConfigurationData();
        }
        return instance;
    }

    public String getReportLocation() {
        return prop.getProperty("report.location");
    }

    public String getImageFolderName() {
        return prop.getProperty("image.folder.name");
    }

    public String getMobileDevice() {
        return deviceData.getMobileDevice();
    }

    public String getOrientation() {
        return deviceData.getMobileDeviceOrientation();
    }

    public String getMobileDeviceOsVersion() {
        return deviceData.getMobileDeviceOsVersion();
    }

    public String getTheScoreAndroidApp() {
        return prop.getProperty("mobile.app.android");
    }

    public String getTheScoreIOSApp() {
        return prop.getProperty("mobile.app.ios");
    }

    public String getAppiumUrl() {
        return prop.getProperty("mobile.appium.url");
    }
}