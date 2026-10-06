package com.ditto.automation.utils;

import org.testng.Assert;

public class TestAssertions {

    // ANSI formatting codes
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String BOLD = "\u001B[1m";
    private static final String RESET = "\u001B[0m";
    
    // Assert element is displayed
    public static void assertDisplayed(ElementActions actions,String module,String element,String message) {

        try {

            boolean displayed = actions.isDisplayed(module,element);

            Assert.assertTrue(displayed,message);

            System.out.println(
                    GREEN + BOLD
                    + "[PASS] "
                    + module + "." + element
                    + " - "
                    + message
                    + RESET
            );

            ReportManager.passTest(message);

        } catch (AssertionError e) {

            System.out.println(
                    RED + BOLD
                    + "[FAIL] "
                    + module + "." + element
                    + " - "
                    + message
                    + RESET
            );

            ReportManager.failTest(message);

            throw e;
        }
    }

   

    // Assert text value
    public static void assertText(ElementActions actions, String module, String element, String expectedText, String message) {

        String actualText = actions.getText(module, element);

        // Normalize non-breaking spaces
        String normalizedActual = actualText.replace('\u00A0', ' ').trim();

        String normalizedExpected = expectedText.replace('\u00A0', ' ').trim();

        try {

            Assert.assertEquals(
                    normalizedActual,
                    normalizedExpected,
                    message
            );

            String resultMessage = message + " | Expected: " + normalizedExpected + ", Actual: " + normalizedActual;

            System.out.println(
                    GREEN + BOLD
                    + "[PASS] "
                    + module + "." + element
                    + " - "
                    + resultMessage
                    + RESET
            );

            ReportManager.passTest(resultMessage);

        } catch (AssertionError e) {

            String resultMessage = message + " | Expected: " + normalizedExpected + ", Actual: " + normalizedActual;

            System.out.println(
                    RED + BOLD
                    + "[FAIL] "
                    + module + "." + element
                    + " - "
                    + resultMessage
                    + RESET
            );

            ReportManager.failTest(resultMessage);

            throw e;
        }
    }

    // Assert checkbox / radio button is selected
    public static void assertSelected(
         boolean actual,
         String module,
         String element,
         String message) {

     try {

         Assert.assertTrue(actual, message);

         System.out.println(
                 GREEN + BOLD
                 + "[PASS] "
                 + module + "." + element
                 + " - "
                 + message
                 + RESET
         );

         ReportManager.passTest(message);

     } catch (AssertionError e) {

         System.out.println(
                 RED + BOLD
                 + "[FAIL] "
                 + module + "." + element
                 + " - "
                 + message
                 + RESET
         );

         ReportManager.failTest(message);

         throw e;
     }
 }
 
    public static void assertContains(
	        ElementActions actions,
	        String module,
	        String element,
	        String expectedText,
	        String message) {

	    String actualText = actions.getText(module, element);

	    try {

	        Assert.assertTrue(
	                actualText.contains(expectedText),
	                message + " | Expected to contain: " + expectedText + ", Actual: " + actualText
	        );

	        String resultMessage =
	                message + " | Expected to contain: " + expectedText + ", Actual: " + actualText;

	        System.out.println(
	                GREEN + BOLD
	                + "[PASS] "
	                + module + "." + element
	                + " - "
	                + resultMessage
	                + RESET
	        );

	        ReportManager.passTest(resultMessage);

	    } catch (AssertionError e) {

	        String resultMessage =
	                message + " | Expected to contain: " + expectedText + ", Actual: " + actualText;

	        System.out.println(
	                RED + BOLD
	                + "[FAIL] "
	                + module + "." + element
	                + " - "
	                + resultMessage
	                + RESET
	        );

	        ReportManager.failTest(resultMessage);

	        throw e;
	    }
	}
 

    // Assert double value
    public static void assertDoubleEquals(double actual, double expected, double delta, String module, String element, String message) {

  try {

      Assert.assertEquals(actual,expected,delta,message);

      String resultMessage = message + " | Expected: " + expected + ", Actual: " + actual;

      System.out.println(
              GREEN + BOLD
              + "[PASS] "
              + module + "." + element
              + " - "
              + resultMessage
              + RESET
      );
      
      ReportManager.passTest(resultMessage);

  } catch (AssertionError e) {

      String resultMessage = message + " | Expected: " + expected + ", Actual: " + actual;

      System.out.println(
              RED + BOLD
              + "[FAIL] "
              + module + "." + element
              + " - "
              + resultMessage
              + RESET
      );

      ReportManager.failTest(resultMessage);

      throw e;
  }
}
}