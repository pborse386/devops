package Utilities;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.beust.jcommander.converters.PathConverter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

import com.relevantcodes.*;

import static org.seleniumhq.jetty9.http.HttpParser.LOG;


public class GenerateExtentReport implements IReporter {
    private ExtentReports extent;
    String name;
//    Page page;



    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

//        System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';");
//        System.setProperty("jenkins.model.DirectoryBrowserSupport.CSP", "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';");

        String className = suites.get(0).getName();
        extent = new ExtentReports(outputDirectory+ File.separator +className +File.separator +className + ".html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(outputDirectory, context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(outputDirectory, context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(outputDirectory, context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        extent.flush();
        extent.close();
    }

    private void buildTestNodes(String outputDirectory, IResultMap tests, LogStatus status) {
        ExtentTest test;
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
//
//                test.getTest(). = getTime(result.getStartMillis());
//                test.getTest().endedTime = getTime(result.getEndMillis());

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null) message = result.getThrowable().getMessage();

                String suiteName = result.getTestContext().getName();
                String name = result.getName();

                if(!result.isSuccess()){

//                    String path = Page.takeScreenshot(outputDirectory, suiteName, name);
                    test.log(LogStatus.INFO, "Snapshot below: " +  test.addScreenCapture(name+".png"));
                }
                test.log(status, message);
                extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


}