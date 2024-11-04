package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Business_Classification extends BaseTest {

	private static String moduleName = "BusinessClassification";

	@Override
	protected void initializeLocators() {
		locators.put("newBusinessClassificationButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("businessClassificationTextbox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Classification Textbox"));
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

		testData.put("businessClassificationValue",
				ReadExcel.getValueByLabel(moduleName, "Test Data", "Business Classification Value"));
	}

	@Override
	protected void initializeExpectedValues() {
		expectedValues.put("successMessageForRecordCreation",
				ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Message On Record Creation"));
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
	public void navigateToListScreen() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.navigateToURL(testData.get("listScreenURL"));
			logInfo("Attempting to navigate to the list screen using the provided URL.");
		} catch (NoSuchElementException e) {
			logError("Element not found during navigation to list screen - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element during navigation.", e);
		} catch (TimeoutException e) {
			logError("Navigation to list screen timed out - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout while navigating to the list screen.", e);
		} catch (Exception e) {
			logError("Failed to navigate to list screen due to an unexpected error: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 4)
	public void assertNavigationToListScreen() throws Exception {
		try {
			String navigatedURL = driver.getCurrentUrl();
			Assert.assertEquals(navigatedURL, testData.get("listScreenURL"),
					"User has not been navigated to the desired list screen successfully.");
			logInfo("User has navigated to the list screen of " + moduleName + " module successfully.");
		} catch (AssertionError e) {
			logError("Assertion failed: Navigation URL does not match expected list screen URL - " + e.getMessage());
			throw e; // rethrow to mark test as failed
		} catch (Exception e) {
			logError("Failed to verify navigation to list screen: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5)
	public void testNewBusinessClassificationButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newBusinessClassificationButton");
			logInfo("User has clicked the new business classification button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new business classification button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new business classification button.", e);
		} catch (TimeoutException e) {
			logError(
					"Timed out waiting for new business classification button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new business classification button.", e);
		} catch (Exception e) {
			logError("Failed to click new business classification button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 6)
	public void testBusinessClassificationTextBox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "businessClassificationTextbox",
					testData.get("businessClassificationValue"));
			logInfo("User has entered value: " + testData.get("businessClassificationValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: business classification text box - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: business classification text box.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for business classification text box to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: business classification text box.", e);
		} catch (Exception e) {
			logError("Failed to enter text in business classification text box: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7)
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

	@Test(priority = 8)
	public void testToastMessageVerification() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			// Retrieve the success message from the toast
			String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
			Assert.assertEquals(toastMessage, expectedValues.get("toastMessage"),
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
