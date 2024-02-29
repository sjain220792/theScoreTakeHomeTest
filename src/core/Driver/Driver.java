package core.Driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.Properties.ConfigurationData;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver {

    static String currentTestSuite;
    static String currentTest;
    static String currentScreenSize;
    public static String browser;
    public static String applicationName;
    public static String app;
    protected static AppiumDriver appiumDriver;
    protected static String appiumServerURL;
    public final static ConfigurationData configurationData = ConfigurationData.getInstance();
    protected static String imagesFolderName = configurationData.getImageFolderName();
    protected static String imagesFolderPath = configurationData.getImageFolderName();
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void startTest(String browser, String screensize, String appName, String testName, String suiteName) throws MalformedURLException {
        logger.debug("Inside Start Test Method");
        if (appiumDriver.getSessionId() == null) {
            createInnerDriver(browser, appName, suiteName);
        }
        currentTestSuite = suiteName;
        currentTest = testName;
        currentScreenSize = screensize;
        MobileApp.openApp();
    }

    static void createInnerDriver(String browser, String appName, String suiteName) throws MalformedURLException {
        Driver.browser = browser;
        String device = configurationData.getMobileDevice();
        appiumServerURL = configurationData.getAppiumUrl();
        logger.info("Appium Server started at: " + appiumServerURL);

        String platform = "";
        applicationName = appName;

        if (Driver.browser.equalsIgnoreCase("android")) {
            platform = "ANDROID";
            app = configurationData.getTheScoreAndroidApp();
            logger.info("App location is: " + app);
        } else if (Driver.browser.equalsIgnoreCase("ios")) {
            platform = "iOS";
            app = configurationData.getTheScoreIOSApp();
        } else {
            logger.info("Please select appropriate OS");
        }

        String currentDate = new SimpleDateFormat("MM/dd/yyy HH:mm").format(new Date());

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", platform);
        caps.setCapability("build", suiteName + "-" + device + "-" + currentDate);
        caps.setCapability("name", suiteName);
        caps.setCapability("deviceName", device);
        caps.setCapability("os_version", configurationData.getMobileDeviceOsVersion());
        caps.setCapability("deviceOrientation", configurationData.getOrientation());
        caps.setCapability("app", app);
        caps.setCapability("platform", platform);
        caps.setCapability("appWaitForLaunch", "true");

        if (browser.equalsIgnoreCase("android")) {
            caps.setCapability("automationName", "UiAutomator2");
            //caps.setCapability("udid", "emulator-5554");
            appiumDriver = new AndroidDriver(new URL(appiumServerURL), caps);
        } else if (browser.equalsIgnoreCase("iOS")) {
            caps.setCapability("automationName", "XCUITest");
            appiumDriver = new IOSDriver(new URL(appiumServerURL), caps);
        }
    }

    public static void quitDriver() {
        if (appiumDriver.getSessionId() != null) {
            appiumDriver.quit();
        }
        logger.info("Goodnight drivers");
    }
}
