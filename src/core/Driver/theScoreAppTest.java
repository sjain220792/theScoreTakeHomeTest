package core.Driver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;
import core.Execution.ExecutionListener;

import java.io.IOException;
import java.net.MalformedURLException;

@Listeners({ExecutionListener.class})
public class theScoreAppTest extends Driver {
    private static final Logger logger = LoggerFactory.getLogger(theScoreAppTest.class);
    public String appName;
    public String testName;
    public String suiteName;
    public static ExtentTest test;
    public static ExtentTest step;
    public static ExtentTest suite;
    public static boolean failedTest = false;
    public static boolean takeScreenshots = true;

    @Parameters({"browser", "screenSize"})
    @BeforeSuite
    public void runBeforeSuite(ITestContext context, String browser, String screenSize) throws MalformedURLException {
        suiteName = getSuiteName(context);
        suite = ExecutionListener.report.createTest(suiteName);
        appName = "theScoreApp";
        Driver.createInnerDriver(browser, appName, suiteName);
    }

    @Parameters({"browser", "screenSize"})
    @BeforeMethod
    public void runBeforeMethod(ITestContext context, String browser, String screenSize) throws MalformedURLException {
        testName = context.getCurrentXmlTest().getName();
        suiteName = getSuiteName(context);
        Driver.startTest(browser, screenSize, appName, testName, suiteName);
        test.log(Status.INFO, "Running with Parameters:" + browser + ", " + screenSize);
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {
        step = test;
    }


    @Parameters({"browser", "screenSize"})
    @BeforeTest
    public void runBeforeTest(ITestContext context, String browser, String screenSize) {
        logger.info("Before Test");
        test = suite.createNode(context.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void runAfterMethod(ITestContext context, ITestResult result) {
        logger.info("End of test method");
        String imageLocation;
        try {
            imageLocation = MobileApp.captureScreenshot("FinalImage " + this.getClass().getSimpleName() + System.currentTimeMillis());
            step.log(Status.INFO, "Final Image: ", MediaEntityBuilder.createScreenCaptureFromPath(imageLocation).build());
        } catch (Exception e) {
            step.log(Status.FAIL, "Screenshot failed");
        } finally {
            if (result.getStatus() != ITestResult.SUCCESS) {
                MobileApp.closeApp();
                Driver.quitDriver();
                failedTest = true;

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                MobileApp.closeApp();
                Driver.quitDriver();
            }

            if (result.getStatus() == ITestResult.SUCCESS) {
                step.log(Status.PASS, "Test Passed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                step.log(Status.FAIL, "Test Failed");
            }
            test.log(Status.INFO, "Tearing down test " + this.getClass().getName() + ".....");
        }
    }

    @AfterSuite
    public void afterSuite() {
        Driver.quitDriver();
    }

    public static String getSuiteName(ITestContext context) {
        XmlSuite suite = context.getCurrentXmlTest().getSuite().getParentSuite();
        if (suite == null) {
            suite = context.getCurrentXmlTest().getSuite();
        }
        return suite.getName();
    }

    protected void log(Status status, String message) throws IOException {
        logger.info(message);
        String fileName;
        if (takeScreenshots) {
            fileName = MobileApp.captureScreenshot(message);
            step.log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        }
    }
}