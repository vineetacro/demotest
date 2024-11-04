package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

public class Event_Reason extends BaseTest {

	private static String moduleName = "EventReason";

	@Override
	protected void initializeLocators() {
		locators.put("newEventReasonButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("sectorDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
		locators.put("eventReasonTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Event Reason TextBox"));
		locators.put("professionalWorkerToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Professional Worker Toggle"));
		locators.put("liWorkerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "LI Worker Toggle"));
		locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
		locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
	}

	@Override
	protected void initializeTestData() {
		testData.put("loginURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Login"));
		testData.put("listScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "List Screen"));
		testData.put("homeScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Home Screen"));
		testData.put("addScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Add Screen"));
		testData.put("userIdValue", ReadExcel.getValueByLabel(moduleName, "RndLoginData", "User Id"));
		testData.put("passwordValue", ReadExcel.getValueByLabel(moduleName, "RndLoginData", "Password"));
		
		testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Business Unit Value"));
		testData.put("eventReasonValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Event Reason Value"));
	}

	@Override
	protected void initializeExpectedValues() {
		expectedValues.put("successMessageForRecordCreation",
				ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message"));
	}

	@Test(priority = 1)
	public void testLogin() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
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
		CommonUtils utils = new CommonUtils(driver, moduleName);

		try {
			utils.navigateToURL(testData.get("listScreenURL"));

			String navigatedURL = driver.getCurrentUrl();

			Assert.assertEquals(navigatedURL, testData.get("listScreenURL"),
					"User has not been navigated to the desired list screen successfully.");

		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}
		logInfo("User has navigated to the list screen of " + moduleName + " module successfully.");
	}
	@Test(priority = 4)
	public void testNewEventReasonButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newEventReasonButton");
			logInfo("User has clicked the new event reason button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new event reason button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new event reason button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new event reason button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new event reason button.", e);
		} catch (Exception e) {
			logError("Failed to click new event reason button: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 5)
	public void testSectorDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "sectorDropdown", testData.get("sectorDropdown"));
			logInfo("User has selected/entered value: " + testData.get("sectorDropdown"));
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
	public void testEventReasonTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "eventReasonTextBox", testData.get("eventReasonValue"));
			logInfo("User has entered value: " + testData.get("eventReasonValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: event reason textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: event reason textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for event reason textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: event reason textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in event reason textbox: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 7)
	public void testProfessionalWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "professionalWorkerToggle");
			logInfo("User has clicked the professional worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: professional worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: professional worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for professional worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: professional worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click professional worker toggle: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 8)
	public void testLiWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "liWorkerToggle");
			logInfo("User has clicked the li worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: li worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: li worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for li worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: li worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click li worker toggle: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 9)
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
	@Test(priority = 10)
	public void testToastMessageVerification() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
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