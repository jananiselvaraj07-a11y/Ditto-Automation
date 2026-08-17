package com.ditto.automation.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    // Create and configure the ExtentReports instance
    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir") + "/test-output/extent-report/ExtentReport.html";

            File reportFolder = new File(System.getProperty("user.dir") + "/test-output/extent-report/");

            if (!reportFolder.exists()) {
                reportFolder.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Ditto Automation Report");
            sparkReporter.config().setReportName("Ditto Health Insurance Automation");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "Ditto");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", "Selenium + Java + TestNG");
        }

        return extent;
    }
}