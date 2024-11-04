package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

public class Cost_Accounting_Code extends BaseTest{

private static String moduleName = "CostAccountingCode";

@Override
protected void initializeLocators() {
locators.put("newCostAccountingCodeButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
locators.put("sectorDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Dropdown"));
locators.put("descriptionTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Description TextBox"));
locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
}

@Override
protected void initializeTestData() {
testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Sector Value"));
testData.put("descriptionValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Description Value"));
}

@Override
protected void initializeExpectedValues() {
expectedValues.put("successMessageForRecordCreation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Message On Record Creation")); 
}

@Test
public void TC_001() {

CommonUtils utils = new CommonUtils(driver, moduleName);
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

}

public void testNewCostAccountingCodeButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "newCostAccountingCodeButton");
        logInfo("User has clicked the new cost accounting code button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: new cost accounting code button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: new cost accounting code button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for new cost accounting code button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: new cost accounting code button.", e);
    } catch (Exception e) {
        logError("Failed to click new cost accounting code button: " + e.getMessage());
        throw e;
    }
}

public void testSectorDropdown() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "sectorDropdown", testData.get("sectorValue")); 
        logInfo("User has selected/entered value: " + testData.get("sectorValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: sector dropdown - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: sector dropdown.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for sector dropdown to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: sector dropdown.", e);
    } catch (Exception e) {
        logError("Failed to enter text in sector dropdown: " + e.getMessage());
        throw e;
    }
}

public void testDescriptionTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "descriptionTextbox", testData.get("descriptionValue")); 
        logInfo("User has entered value: " + testData.get("descriptionValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: description textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: description textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for description textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: description textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in description textbox: " + e.getMessage());
        throw e;
    }
}

public void testClickSaveButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "saveButton");
        logInfo("User has clicked the save button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: Save button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: Save button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for Save button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: Save button.", e);
    } catch (Exception e) {
        logError("Failed to click Save button: " + e.getMessage());
        throw e;
    }
}

public void testToastMessageVerification() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        // Retrieve the success message from the toast
        String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
        Assert.assertEquals(toastMessage, expectedValues.get("toastMessage"), "The toast success message did not match the expected value.");
        logInfo("The toast success message has been verified successfully.");
    } catch (NoSuchElementException e) {
        logError("Element not found: Toast message - " + e.getMessage());
        throw new AssertionError("Test failed due to missing toast message.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for toast message - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: Toast message.", e);
    } catch (AssertionError e) {
        logError("Assertion failed: The toast success message did not match the expected value. " + e.getMessage());
        throw e; // rethrow the exception to fail the test
    } catch (Exception e) {
        logError("Failed to retrieve or verify the toast message: " + e.getMessage());
        throw e;
    }
}


}