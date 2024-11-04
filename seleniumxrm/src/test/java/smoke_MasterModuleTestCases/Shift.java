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

public class Shift extends BaseTest{

private static String moduleName = "Shift";

@Override
protected void initializeLocators() {
locators.put("newShiftButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
locators.put("sectorDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Dropdown"));
locators.put("shiftNameTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Shift Name TextBox"));
locators.put("shiftUtilizedAtSpecificLocationToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Shift Utilized At Specific Location Toggle"));
locators.put("startTimeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Start Time TextBox"));
locators.put("endTimeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "End Time TextBox"));
locators.put("workerWorkingDaysMultiselect", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Worker Working Days Multiselect"));
locators.put("shiftDifferentialValueTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Shift Differential Value TextBox"));
locators.put("punchInRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch In Radio Button"));	
locators.put("punchOutRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch Out Radio Button"));
locators.put("locationDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Location Dropdown"));
locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
}

@Override
protected void initializeTestData() {
testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Sector Value"));
testData.put("shiftValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Shift Value"));
testData.put("startTimeValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Start Time Value"));
testData.put("endTimeValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "End Time Value"));
testData.put("workerWorkingDaysValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Worker Working Days Value"));
testData.put("locationValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Location Value"));
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

public void testNewShiftButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "newShiftButton");
        logInfo("User has clicked the new shift button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: new shift button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: new shift button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for new shift button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: new shift button.", e);
    } catch (Exception e) {
        logError("Failed to click new shift button: " + e.getMessage());
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

public void testShiftNameTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "shiftNameTextbox", testData.get("shiftNameValue")); 
        logInfo("User has entered value: " + testData.get("shiftNameValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: shift name textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: shift name textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for shift name textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: shift name textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in shift name textbox: " + e.getMessage());
        throw e;
    }
}

public void testShiftUtilizedAtSpecificLocationToggle() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "shiftUtilizedAtSpecificLocationToggle");
        logInfo("User has clicked the shift utilized at specific location toggle.");
    } catch (NoSuchElementException e) {
        logError("Element not found: shift utilized at specific location toggle - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: shift utilized at specific location toggle.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for shift utilized at specific location toggle to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: shift utilized at specific location toggle.", e);
    } catch (Exception e) {
        logError("Failed to click shift utilized at specific location toggle: " + e.getMessage());
        throw e;
    }
}

public void testLocationDropdown() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "locationDropdown", testData.get("locationValue")); 
        logInfo("User has selected/entered value: " + testData.get("locationValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: location dropdown - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: location dropdown.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for location dropdown to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: location dropdown.", e);
    } catch (Exception e) {
        logError("Failed to enter text in location dropdown: " + e.getMessage());
        throw e;
    }
}

public void testShiftDifferentialValueTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "shiftDifferentialValueTextbox", testData.get("shiftDifferentialValue")); 
        logInfo("User has entered value: " + testData.get("shiftDifferentialValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: shift differential value textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: shift differential value textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for shift differential value textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: shift differential value textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in shift differential value textbox: " + e.getMessage());
        throw e;
    }
}

public void testPunchInRadioButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "punchInRadioButton");
        logInfo("User has clicked the punch in radio button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: punch in radio button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: punch in radio button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for punch in radio button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: punch in radio button.", e);
    } catch (Exception e) {
        logError("Failed to click punch in radio button: " + e.getMessage());
        throw e;
    }
}

public void testPunchOutRadioButton() throws Exception {
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.clickElementWhenClickable(locators, "punchOutRadioButton");
        logInfo("User has clicked the punch out radio button.");
    } catch (NoSuchElementException e) {
        logError("Element not found: punch out radio button - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: punch out radio button.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for punch out radio button to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: punch out radio button.", e);
    } catch (Exception e) {
        logError("Failed to click punch out radio button: " + e.getMessage());
        throw e;
    }
}


public void testStartTimeTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "startTimeTextbox", testData.get("startTimeValue")); 
        logInfo("User has entered value: " + testData.get("startTimeValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: start time textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: start time textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for start time textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: start time textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in start time textbox: " + e.getMessage());
        throw e;
    }
}

public void testEndTimeTextbox() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "endTimeTextbox", testData.get("endTimeValue")); 
        logInfo("User has entered value: " + testData.get("endTimeValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: end time textbox - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: end time textbox.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for end time textbox to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: end time textbox.", e);
    } catch (Exception e) {
        logError("Failed to enter text in end time textbox: " + e.getMessage());
        throw e;
    }
}

public void testWorkerWorkingDaysMultiselect() throws Exception { 
    CommonUtils utils = new CommonUtils(driver, moduleName);
    try {
        utils.enterTextsWhenClickable(locators, "workerWorkingDaysMultiselect", testData.get("workerWorkingDaysValue")); 
        logInfo("User has selected/entered value: " + testData.get("workerWorkingDaysValue"));
    } catch (NoSuchElementException e) {
        logError("Element not found: worker working days multiselect - " + e.getMessage());
        throw new AssertionError("Test failed due to missing element: worker working days multiselect.", e);
    } catch (TimeoutException e) {
        logError("Timed out waiting for worker working days multiselect to become clickable - " + e.getMessage());
        throw new AssertionError("Test failed due to timeout: worker working days multiselect.", e);
    } catch (Exception e) {
        logError("Failed to enter text in worker working days multiselect: " + e.getMessage());
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
