package com.ditto.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static WebDriver driver;

    // Create and return a reusable WebDriver instance
    public static WebDriver getDriver() {

        if (driver == null) {
            driver = new ChromeDriver();
        }

        return driver;
    }

    // Close the browser and release the WebDriver instance
    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}