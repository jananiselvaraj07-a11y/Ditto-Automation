# Ditto Health Insurance Automation Framework

## Overview

Selenium + Java automation framework developed to automate the Ditto Health Insurance product selection and policy details flow.

## Tech Stack

- Java 17
- Selenium WebDriver 4.46.0
- TestNG 7.12.0
- Maven
- Jackson
- ExtentReports

## Framework Structure

- `base` - WebDriver management
- `utils` - Reusable framework utilities
- `objectrepository` - JSON-based UI locators
- `test` - Test cases and TestNG setup
- `TestAssertions` - Centralized assertion and reporting methods
- `TestListener` - Test execution and ExtentReport integration

## Automated Scenario

The automation covers:

1. Navigate to Ditto Health Insurance flow
2. Select Optima Secure
3. Navigate through policy coverage and benefits
4. Select Self and Female
5. Enter age and pincode
6. Select loyalty discount option
7. Calculate premium
8. Select add-ons
9. Validate policy period
10. Validate member, pincode and cover details
11. Validate total premium calculation

## How to Run

### Prerequisites

- Java 17
- Maven
- Chrome browser

### Execute Tests

```bash
mvn clean test