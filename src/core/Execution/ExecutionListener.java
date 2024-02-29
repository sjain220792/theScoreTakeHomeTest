package core.Execution;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;
import core.Properties.ConfigurationData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutionListener implements IExecutionListener {
    public static ExtentHtmlReporter htmlReport;
    public static ExtentReports report;
    public static String testId;
    private final static Logger javaLogger = LoggerFactory.getLogger(ExecutionListener.class);

    public void onExecutionStart() {
        ConfigurationData configurationData = ConfigurationData.getInstance();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        testId = "TestRun_" + timestamp;

        String reportLocation = configurationData.getReportLocation();
        htmlReport = new ExtentHtmlReporter(reportLocation + "report.html");
        htmlReport.config().setTheme(Theme.DARK);
        htmlReport.config().setCSS(".step-details > img {width: 95%; }");
        htmlReport.config().setReportName("Test Results");
        htmlReport.config().setDocumentTitle("UI Automation Report");

        report = new ExtentReports();
        report.attachReporter(htmlReport);

        javaLogger.info("Starting test run");
        javaLogger.info("Report location is set in config as: " + reportLocation);

    }

    @Override
    public void onExecutionFinish() {
        report.flush();
    }

}
