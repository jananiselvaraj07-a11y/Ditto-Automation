package com.ditto.automation;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ditto.automation.utils.ElementActions;
import com.ditto.automation.utils.TestListener;
import com.ditto.automation.utils.TestAssertions;

@Listeners(TestListener.class)
public class HealthProductTest extends BaseTest {

    @Test
    public void selectOptimaSecure() {

        ElementActions actions = new ElementActions(driver);
        
        // Navigate to Ditto and select Optima Secure
        actions.navigateTo("https://app.joinditto.in/fq");
        actions.click("HomePage", "optimaSecure");
        TestAssertions.assertDisplayed(actions,"PolicyPage","selectedProduct","Optima Secure page display validation");

        // Navigate through the policy coverage and benefits screens
        actions.click("PolicyPage", "nextButton");
        actions.click("PolicyPage", "fullListButton");
        actions.click("PolicyPage", "closeButton");
        actions.click("PolicyPage", "nextButton");
        actions.click("PolicyPage", "nextButton");
        actions.click("PolicyPage", "continueButton");
        
        TestAssertions.assertDisplayed(actions,"FamilyMembersPage","pageHeading","Family Members page display validation");
        
        // Select the policy member and gender
        String memberSelection = "self";
        String genderSelection = "selfFemale";
        
        actions.click("FamilyMembersPage", memberSelection);
        TestAssertions.assertDisplayed(actions,"FamilyMembersPage","selectedMemberCount","Selected member count validation");
        actions.click("FamilyMembersPage", genderSelection);
        actions.click("FamilyMembersPage", "nextStepButton");
        
        TestAssertions.assertDisplayed(actions,"PolicyDetailsPage","pageHeading","Policy details page display validation");
        
        // Enter personal details and calculate the premium
        String age = "26";
        String pincode = "600064";

        actions.sendKeys("PolicyDetailsPage", "age", age);
        actions.sendKeys("PolicyDetailsPage", "pincode", pincode);
        actions.click("PolicyDetailsPage", "loyaltyDiscountNo");
        
        String expectedCoverAmount = "₹15 L";

        TestAssertions.assertText(actions,"PolicyDetailsPage","coverAmount",expectedCoverAmount,"Cover amount is " + expectedCoverAmount);
        actions.click("PolicyDetailsPage", "calculatePremiumButton");
        TestAssertions.assertDisplayed(actions,"PolicyDetailsPage","addOnsRiders","Add Ons & Riders section display validation");
        
        // Select add-ons and verify their selection
        actions.click("PolicyDetailsPage", "unlimitedRestoration");
        boolean unlimitedRestorationSelected = actions.isSelected("PolicyDetailsPage", "unlimitedRestoration");
        TestAssertions.assertSelected(unlimitedRestorationSelected,"PolicyDetailsPage","unlimitedRestoration","Unlimited Restoration selection validation");
        
        actions.click("PolicyDetailsPage", "otherAddonsAccordion");
        actions.click("PolicyDetailsPage", "limitless");
        boolean limitlessSelected = actions.isSelected("PolicyDetailsPage", "limitless");
        TestAssertions.assertSelected(limitlessSelected,"PolicyDetailsPage","limitless","Limitless selection validation");
        
        String expectedPolicyPeriod = "1 Year";
        String policyPeriodElement;

        switch (expectedPolicyPeriod) {

            case "1 Year":
                policyPeriodElement = "policyPeriod1Year";
                break;

            case "2 Years":
                policyPeriodElement = "policyPeriod2Years";
                break;

            case "3 Years":
                policyPeriodElement = "policyPeriod3Years";
                break;

            default:
                throw new IllegalArgumentException("Unsupported policy period: " + expectedPolicyPeriod);
        }
        
        boolean policyPeriodSelected = actions.isSelected("PolicyDetailsPage", policyPeriodElement);

        TestAssertions.assertSelected(policyPeriodSelected,"PolicyDetailsPage",policyPeriodElement,expectedPolicyPeriod + " Policy period selection validation");      
     
        String expectedMemberName = memberSelection.equals("self") ? "Self" : memberSelection;
        
        String expectedGender;

        if (genderSelection.equals("selfFemale")) {
            expectedGender = "F";
        } else if (genderSelection.equals("selfMale")) {
            expectedGender = "M";
        } else {
            throw new IllegalArgumentException("Unsupported gender selection: " + genderSelection);
        }
        
        String expectedMember = expectedMemberName + " (" + expectedGender + ") (" + age + ")";
        
        TestAssertions.assertText(actions,"PolicyDetailsPage","membersIncluded",expectedMember,"Selected member validation");
        TestAssertions.assertText(actions,"PolicyDetailsPage","summaryPincode",pincode,"Pincode validation");
        
        String expectedCoverValue = expectedCoverAmount.replaceAll("[^0-9]", "");
        TestAssertions.assertContains(actions,"PolicyDetailsPage","summaryCover",expectedCoverValue,"Cover amount summary validation");
     
        String basePremiumText = actions.getText("PolicyDetailsPage", "basePremium");
        String recommendedAddOnsText = actions.getText("PolicyDetailsPage", "recommendedAddOns");
        String otherAddOnsText = actions.getText("PolicyDetailsPage", "otherAddOns");
        String totalPremiumText = actions.getText("PolicyDetailsPage", "totalPremium");
        
        double basePremium = Double.parseDouble(basePremiumText.replace("₹", "").replace(",", "").trim());
        double recommendedAddOns = Double.parseDouble(recommendedAddOnsText.replace("₹", "").replace(",", "").trim());
        double otherAddOns = Double.parseDouble(otherAddOnsText.replace("₹", "").replace(",", "").trim());
        double totalPremium = Double.parseDouble(totalPremiumText.replace("₹", "").replace(",", "").trim());
        
        // Calculate and validate the total premium
        double expectedTotalPremium = basePremium + recommendedAddOns + otherAddOns;
        TestAssertions.assertDoubleEquals(totalPremium,expectedTotalPremium,0.01,"PolicyDetailsPage","totalPremium","Total Premium calculation validation");
    }
}