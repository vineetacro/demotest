package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectUtilities.AppConstants;
import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Organization_Level_2 extends BaseTest {

	@Override
	protected void initializeLocators() {
		locators.put(AppConstants.newOrgLevel2Button, ReadExcel.getValueByLabel(AppConstants.organizationLevel2,
				AppConstants.commonElement, AppConstants.Add_Button_on_List_Screen));
		locators.put(AppConstants.sectorDropdownKey, ReadExcel.getValueByLabel(AppConstants.organizationLevel2,
				AppConstants.commonElement, AppConstants.Business_Unit_Dropdown));
		locators.put(AppConstants.orgLevel2TextBox, ReadExcel.getValueByLabel(AppConstants.organizationLevel2,
				AppConstants.commonElement, "Org Level 2 TextBox"));
		locators.put("saveButton",
				ReadExcel.getValueByLabel(AppConstants.organizationLevel2, AppConstants.commonElement, "Save Button"));
		locators.put("toast", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, AppConstants.commonElement, "Toast"));
	}

	@Override
	protected void initializeTestData() {
		testData.put("loginURL", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndURLs", "Login"));
		testData.put("listScreenURL", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndURLs", "List Screen"));
		testData.put("homeScreenURL", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndURLs", "Home Screen"));
		testData.put("addScreenURL", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndURLs", "Add Screen"));
		testData.put("userIdValue", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndLoginData", "User Id"));
		testData.put("passwordValue", ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "RndLoginData", "Password"));
		
		testData.put("sectorValue",
				ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "Test Data", "Business Unit Value"));
		testData.put("orgLevel2Value",
				ReadExcel.getValueByLabel(AppConstants.organizationLevel2, "Test Data", "Organization Level 2 Value"));
	}

	@Override
	protected void initializeExpectedValues() {
		expectedValues.put("successMessageForRecordCreation", ReadExcel.getValueByLabel(AppConstants.organizationLevel2,
				"Expected Values", "Success Toast Message"));
	}

	@Test(priority = 1)
	public void testLogin() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			utils.login(driver, testData.get("userIdValue"), testData.get("passwordValue"), testData.get("loginURL"));
		} catch (Exception e) {
			logError("Failed to login: " + e.getMessage());
			throw e;
		}
		logInfo("User has been logged in successfully.");

	}

	@Test(priority = 2, dependsOnMethods = "testLogin")
	public void testNavigationToHomeScreen() throws Exception {
		try {
			// Wait for the home screen URL
			logger.info("Waiting for the login URL to be the home page URL.");
			wait.until(ExpectedConditions.urlToBe(testData.get("homeScreenURL")));
			String navigatedURL = driver.getCurrentUrl();
			Assert.assertEquals(navigatedURL, testData.get("homeScreenURL"),
					"User has not been navigated to the Home Screen successfully.");
		} catch (TimeoutException e) {
			logError("Timeout while waiting for the home screen URL: " + e.getMessage());
			throw e;
		} catch (AssertionError e) {
			logError("Assertion failed: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logError("Failed to redirect to home screen: " + e.getMessage());
			throw e;
		}
		logInfo("User has been redirected to the home screen successfully.");
	}

	@Test(priority = 3)
	public void testRedirectionToListScreen() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);

		try {
			utils.navigateToURL(testData.get("listScreenURL"));

			String navigatedURL = driver.getCurrentUrl();

			Assert.assertEquals(navigatedURL, testData.get("listScreenURL"),
					"User has not been navigated to the desired list screen successfully.");

		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}
		logInfo("User has navigated to the list screen of " + AppConstants.organizationLevel2 + " module successfully.");
	}
	@Test(priority = 4)
	public void testnewOrgLevel2Button() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
		try {
			utils.clickElementWhenClickable(locators, AppConstants.newOrgLevel2Button);
			logInfo("User has clicked the new org level 2 button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new org level 2 button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new org level 2 button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new org level 2 button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new org level 2 button.", e);
		} catch (Exception e) {
			logError("Failed to click new org level 2 button: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 5)
	public void testSectorDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
		try {
			utils.enterTextsWhenClickable(locators, AppConstants.sectorDropdownKey, testData.get("sectorValue"));
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
	@Test(priority = 6)
	public void testOrgLevel2Textbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
		try {
			utils.enterTextsWhenClickable(locators, AppConstants.orgLevel2TextBox, testData.get("orgLevel2Value"));
			logInfo("User has entered value: " + testData.get("orgLevel2Value"));
		} catch (NoSuchElementException e) {
			logError("Element not found: org level 2 textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: org level 2 textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for org level 2 textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: org level 2 textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in org level 2 textbox: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 7)
	public void testClickSaveButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
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
	@Test(priority = 8)
	public void testToastMessageVerification() throws Exception {
		CommonUtils utils = new CommonUtils(driver, AppConstants.organizationLevel2);
		try {
			// Retrieve the success message from the toast
			String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
			Assert.assertEquals(toastMessage, expectedValues.get("successMessageForRecordCreation"),
					"The toast success message did not match the expected value.");
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
