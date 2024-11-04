package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Request_Cancel_Close_Reason extends BaseTest{

private static String moduleName = "RequestCancelCloseReason";

@Override
protected void initializeLocators() {
locators.put("newRequestCancelCloseReasonButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
locators.put("sectorDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Dropdown"));
locators.put("cancelCloseReasonTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cancel/Close Reason Textbox"));
locators.put("professionalPsrRequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Professional/PSR Request Toggle"));
locators.put("liRequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "LI Request Toggle"));
locators.put("icSowRequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "IC/SOW Request Toggle"));
locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
}

@Override
protected void initializeTestData() {
testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Sector Value"));
testData.put("cancelCloseReasonValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Cancel/Close Reason Value"));
}

@Override
protected void initializeExpectedValues() {
expectedValues.put("successMessageForRecordCreation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Message On Record Creation")); 
}

@Test
public void TC_004() {

CommonUtils utils = new CommonUtils(driver, moduleName);
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

}

public void testNewRequestCancelCloseReasonButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "newRequestCancelCloseReasonButton");
        logInfo("User has clicked the new request cancel/close reason button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: new request cancel/close reason button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: new request cancel/close reason button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for new request cancel/close reason button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: new request cancel/close reason button.", e);
    } catch (Exception e) {
        logError("Failed to click new request cancel/close reason button: " + e.getMessage());
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

public void testRequestCancelCloseReasonTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "requestCancelCloseTextbox", testData.get("requestCancelCloseValue")); 
        logInfo("User has entered value: " + testData.get("requestCancelCloseValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: cancel/close reason textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: decline reason textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for cancel/close reason textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: cancel/close reason textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in cancel/close reason textbox: " + e.getMessage());
        throw e;
    }
}

public void testProfessionalPsrRequestToggle() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "professionalPsrRequestToggle");
        logInfo("User has clicked the professional/psr request toggle.");
    } catch (NoSuchElementException e) {
        logError("Element not found: professional/psr request toggle - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: professional/psr request toggle.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for professional/psr request toggle to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: professional/psr request toggle.", e);
    } catch (Exception e) {
        logError("Failed to click professional/psr request toggle: " + e.getMessage());
        throw e;
    }
}

public void testLiRequestToggle() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "liRequestToggle");
        logInfo("User has clicked the li request toggle.");
    } catch (NoSuchElementException e) {
        logError("Element not found: li request toggle - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: li request toggle.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for li request toggle to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: li request toggle.", e);
    } catch (Exception e) {
        logError("Failed to click li request toggle: " + e.getMessage());
        throw e;
    }
}

public void testIcSowRequestToggle() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "icSowRequestToggle");
        logInfo("User has clicked the ic/sow request toggle.");
    } catch (NoSuchElementException e) {
        logError("Element not found: ic/sow request toggle - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: ic/sow request toggle.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for ic/sow request toggle to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: ic/sow request toggle.", e);
    } catch (Exception e) {
        logError("Failed to click ic/sow request toggle: " + e.getMessage());
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

