package com.ditto.automation.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

    private WebDriver driver;

    private WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigate to the specified URL
    public void navigateTo(String url) {

        System.out.println("[INFO] Navigating to: " + url);

        driver.get(url);

        System.out.println("[INFO] Page loaded: " + driver.getTitle());
    }

    // Capture a screenshot and attach it to the ExtentReport
    private void captureStepScreenshot(String stepName) {

        try {

            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, stepName);

            ReportManager.logStep(stepName, screenshotPath);

        } catch (Exception e) {

            System.out.println("[WARN] Unable to capture screenshot: " + e.getMessage());
        }
    }

    // Click an element after waiting until it is clickable
    public void click(String module, String element) {

        String locator = JsonReader.getLocator(module, element);

        System.out.println("[INFO] Clicking element: " + module + "." + element );

        System.out.println("[INFO] Locator: " + locator);

        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

        webElement.click();

        System.out.println("[INFO] Click successful: " + module + "." + element);

        captureStepScreenshot(module + "_" + element);
    }

    // Enter text into an element after waiting until it is visible
    public void sendKeys(String module, String element, String value) {

        String locator = JsonReader.getLocator(module, element);

        System.out.println("[INFO] Entering text into: " + module + "." + element);

        System.out.println("[INFO] Locator: " + locator);
        System.out.println("[INFO] Value: " + value);

        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

        webElement.clear();
        webElement.sendKeys(value);

        System.out.println("[INFO] Text entered successfully: " + module + "." + element);

        captureStepScreenshot(module + "_" + element);
    }

    // Retrieve the visible text of an element
    public String getText(String module, String element) {

        String locator = JsonReader.getLocator(module, element);

        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

        return webElement.getText();
    }

    // Verify whether an element is displayed
    public boolean isDisplayed(String module, String element) {

        String locator = JsonReader.getLocator(module, element);

        System.out.println("[INFO] Verifying element: " + module + "." + element);

        System.out.println("[INFO] Locator: " + locator);

        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

        boolean displayed = webElement.isDisplayed();

        System.out.println("[INFO] Element displayed: " + displayed);

        if (displayed) {

            captureStepScreenshot(module + "_" + element);
        }

        return displayed;
    }
    
    // Verify whether a checkbox or radio button is selected
    public boolean isSelected(String module, String element) {

        String locator = JsonReader.getLocator(module, element);

        System.out.println("[INFO] Verifying selection: " + module + "." + element);
        System.out.println("[INFO] Locator: " + locator);

        WebElement webElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))
        );

        boolean selected = webElement.isSelected();

        System.out.println("[INFO] Selection status: " + selected);

        if (selected) {
            captureStepScreenshot(module + "_" + element + "_selected");
        }

        return selected;
    }
}