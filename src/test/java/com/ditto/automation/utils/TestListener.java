package com.ditto.automation.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName()
                );

        ReportManager.setTest(extentTest);

        ReportManager.getTest().info(
                "Test execution started"
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ReportManager.passTest(
                "Test passed successfully"
        );

        extent.flush();

        ReportManager.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ReportManager.failTest(
                result.getThrowable()
        );

        extent.flush();

        ReportManager.clear();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ReportManager.skipTest(
                "Test skipped"
        );

        extent.flush();

        ReportManager.clear();
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}