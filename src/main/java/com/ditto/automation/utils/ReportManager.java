package com.ditto.automation.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ReportManager {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    // Store the current test instance for the executing thread
    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    // Retrieve the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }

    // Log a test step with its screenshot
    public static void logStep(String stepName, String screenshotPath) {

        if (test.get() == null) {
            return;
        }

        try {

            test.get().info(stepName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        } catch (Exception e) {

            test.get().warning("Unable to attach screenshot for step " + stepName + ": " + e.getMessage());
        }
    }

    // Log a passed test step
    public static void passTest(String message) {

        if (test.get() != null) {
            test.get().pass(message);
        }
    }
    
    // Log a failed test step
    public static void failTest(String message) {

        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    // Log a failure using the exception details
    public static void failTest(Throwable throwable) {

        if (test.get() != null) {
            test.get().fail(throwable);
        }
    }

    // Log a skipped test
    public static void skipTest(String message) {

        if (test.get() != null) {
            test.get().skip(message);
        }
    }

    // Remove the test instance from the current thread
    public static void clear() {
        test.remove();
    }
}