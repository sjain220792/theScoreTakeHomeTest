package common.core.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {

    static String currentTestSuite;
    static String currentTest;
    static String currentScreenSize;

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void startTest(String browser, String screensize, String appName, String testName, String suiteName) {
        logger.debug("Inside Start Test Method");
        currentTestSuite = suiteName;
        currentTest = testName;
        currentScreenSize = screensize;
    }
}
