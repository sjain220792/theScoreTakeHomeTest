package common.core;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;

import java.net.MalformedURLException;

@Listeners({IExecutionListener.class})
public class theScoreAppTest {
    private static final Logger logger = LoggerFactory.getLogger(theScoreAppTest.class);
    public String appName;
    public String testName;
    public String suiteName;
    public static ExtentTest test;
    public static ExtentTest step;
    public static ExtentTest suite;

    @Parameters({"browser","screenSize"})
    @BeforeSuite
    public void runBeforeSuite(ITestContext context, @Optional("Android") String browser, @Optional("ANDROID_APP_PHONE") String screenSize) throws MalformedURLException {
        suiteName = getSuiteName(context);
        suite = ExecutionListener.report.createTest(suiteName);
    appName = "theScoreApp";
        Driver.createInnerDriver(browser, appName);
    }

    @Parameters({"browser","screenSize"})
    @BeforeMethod
    public void runBeforeMethod(ITestContext context, @Optional("Android") String browser, @Optional("ANDROID_APP_PHONE") String screenSize){
        testName = context.getCurrentXmlTest().getName();
        Driver.startTest(browser, screenSize, appName, testName, suiteName);
        test.log(Status.INFO,"Running with Parameters:" + browser+", "+ screenSize);
    }

    @Parameters({"browser","screenSize"})
    @BeforeTest
    public void runBeforeTest(ITestContext context, @Optional("Android") String browser, @Optional("ANDROID_APP_PHONE") String screenSize){
        logger.info("Before Test");
    }

    @AfterMethod(alwaysRun = true)
    public void runAfterMethod(ITestContext result){
        logger.info("End of test method");
    }

    @AfterSuite
    public void runAfterSuite(ITestContext context){
        try{
            Driver.closeAppiumServer();
        } catch (Exception e){
            logger.info("Unable to quite driver");
            logger.info(e.toString());
        }
    }

    public static String getSuiteName(ITestContext context){
        XmlSuite suite = context.getCurrentXmlTest().getSuite().getParentSuite();
        if (suite == null){
            suite = context.getCurrentXmlTest().getSuite();
        }
        return suite.getName();
    }
}
