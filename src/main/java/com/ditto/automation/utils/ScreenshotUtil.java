package com.ditto.automation.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	// Capture a screenshot with a timestamp
    public static String captureScreenshot(WebDriver driver, String screenshotName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

        String directory = System.getProperty("user.dir") + "/test-output/screenshots/";

        File folder = new File(directory);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = directory + screenshotName + "_" + timestamp + ".png";

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File destination = new File(filePath);

        source.renameTo(destination);

        System.out.println("[INFO] Screenshot captured: " + filePath);

        return filePath;
    }

    // Clear previously captured screenshots before test execution
    public static void clearScreenshots() {

        String directory = System.getProperty("user.dir") + "/test-output/screenshots/";

        File folder = new File(directory);

        if (folder.exists()) {

            File[] files = folder.listFiles();

            if (files != null) {

                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}