package common.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    static String currentTestSuite;
    static String currentTest;
    static String currentScreenSize;
    public static String browser;
    public static String applicationName;
    public static String app;
    protected static AppiumDriver appiumDriver;
    protected static String appiumServerURL;
    private static AppiumDriverLocalService appiumService;

    public final static ConfigurationData configurationData = ConfigurationData.getInstance();



    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void startTest(String browser, String screensize, String appName, String testName, String suiteName) {
        logger.debug("Inside Start Test Method");
        currentTestSuite = suiteName;
        currentTest = testName;
        currentScreenSize = screensize;
    }

    static void createInnerDriver(String browser, String appName) throws MalformedURLException {
        Driver.browser = browser;
        String orientation = configurationData.getOrientation();
        String device = configurationData.getMobileDevice();

        String platform = "";
        applicationName = appName;

        if (Driver.browser.equalsIgnoreCase("android")) {
            platform = "ANDROID";
            app = configurationData.getTheScoreAndroid();
        } else if (Driver.browser.equalsIgnoreCase("ios")){
            platform = "iOS";
            app = configurationData.getTheScoreiOS();
        } else {
            logger.info("Please select appropriate OS");
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", platform);
        caps.setCapability("appWaitLaunch", "true");

        if (browser.equalsIgnoreCase("android")){
            caps.setCapability("android","UiAutomator2");
            caps.setCapability("udid","emulator-5554");
            appiumDriver = new AndroidDriver(new URL(appiumServerURL),caps);
        } else if (browser.equalsIgnoreCase("iOS")){
            caps.setCapability("automationName","XCUITest");
            appiumDriver = new IOSDriver(new URL(appiumServerURL), caps);
        }
    }

    public static void closeAppiumServer(){
        if (appiumService != null){
            appiumService.stop();
            logger.info("Goodnight appium");
        } else {
            logger.info("AppiumService not running locally");
        }
    }
}