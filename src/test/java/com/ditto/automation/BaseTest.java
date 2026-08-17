package com.ditto.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ditto.automation.base.DriverManager;
import com.ditto.automation.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ScreenshotUtil.clearScreenshots();

        driver = DriverManager.getDriver();

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}