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

public class Termination_Reason extends BaseTest {

	private static String moduleName = "TerminationReason";

	@Override
	protected void initializeLocators() {
		locators.put("newTerminationReasonButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("sectorDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
		locators.put("terminationReasonTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Termination Reason TextBox"));
		locators.put("reasonTypePositiveRadioButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Reason Type Positive Radio Button"));
		locators.put("reasonTypeNegativeRadioButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Reason Type Negative Radio Button"));
		locators.put("reasonTypeNeutralRadioButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Reason Type Neutral Radio Button"));

		locators.put("professionalWorkerToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Professional Worker Toggle"));
		locators.put("liWorkerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Li Worker Toggle"));
		locators.put("sowResourcesToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "SOW Resources Toggle"));

		locators.put("backfillNeededToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Backfill Needed Toggle"));
		locators.put("managerSurveyToBeRequestedToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Manager Survey to be Requested Toggle"));
		locators.put("enableWorkerMarkingInDoNotReturnToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements",
				"Enable Worker Marking in Do Not Return Toggle"));

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
		testData.put("terminationReasonValue",
				ReadExcel.getValueByLabel(moduleName, "Test Data", "Termination Reason Value"));
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
	public void testNewTerminationReasonButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newTerminationReasonButton");
			logInfo("User has clicked the new termination reason button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new termination reason button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new termination reason button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new termination reason button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new termination reason button.", e);
		} catch (Exception e) {
			logError("Failed to click new termination reason button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5)
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

	@Test(priority = 6)
	public void testTerminationReasonTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "terminationReasonTextBox", testData.get("terminationReasonValue"));
			logInfo("User has entered value: " + testData.get("terminationReasonValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: termination reason textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: termination reason textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for termination reason textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: termination reason textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in termination reason textbox: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7)
	public void testPositiveReasonTypeRadioButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "reasonTypePositiveRadioButton");
			logInfo("User has clicked the positive reason type radio button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: positive reason type radio button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: positive reason type radio button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for positive reason type radio button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: positive reason type radio button.", e);
		} catch (Exception e) {
			logError("Failed to click positive reason type radio button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 8)
	public void testNegativeReasonTypeRadioButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "reasonTypeNegativeRadioButton");
			logInfo("User has clicked the negative reason type radio button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: negative reason type radio button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: negative reason type radio button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for negative reason type radio button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: negative reason type radio button.", e);
		} catch (Exception e) {
			logError("Failed to click negative reason type radio button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 9)
	public void testNeutralReasonTypeRadioButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "reasonTypeNeutralRadioButton");
			logInfo("User has clicked the neutral reason type radio button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: neutral reason type radio button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: neutral reason type radio button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for neutral reason type radio button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: neutral reason type radio button.", e);
		} catch (Exception e) {
			logError("Failed to click neutral reason type radio button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 10)
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

	@Test(priority = 11)
	public void testLiWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "liWorkerToggle");
			logInfo("User has clicked the LI worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: LI worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: LI worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for LI worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: LI worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click LI worker toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 12)
	public void testSowResourcesToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "sowResourcesToggle");
			logInfo("User has clicked the SOW resources toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: SOW resources toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: SOW resources toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for SOW resources toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: SOW resources toggle.", e);
		} catch (Exception e) {
			logError("Failed to click SOW resources toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 13)
	public void testBackfillNeededToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "backfillNeededToggle");
			logInfo("User has clicked the backfill needed toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: backfill needed toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: backfill needed toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for backfill needed toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: backfill needed toggle.", e);
		} catch (Exception e) {
			logError("Failed to click backfill needed toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 14)
	public void testManagerSurveyToBeRequestedToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "managerSurveyToBeRequestedToggle");
			logInfo("User has clicked the Manager Survey to be Requested toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Manager Survey to be Requested toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Manager Survey to be Requested toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Manager Survey to be Requested toggle to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: Manager Survey to be Requested toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Manager Survey to be Requested toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 15)
	public void testEnableWorkerMarkingInDoNotReturnToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "enableWorkerMarkingInDoNotReturnToggle");
			logInfo("User has clicked the Enable Worker Marking in Do Not Return toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Enable Worker Marking in Do Not Return toggle - " + e.getMessage());
			throw new AssertionError(
					"Test failed due to missing element: Enable Worker Marking in Do Not Return toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Enable Worker Marking in Do Not Return toggle to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: Enable Worker Marking in Do Not Return toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Enable Worker Marking in Do Not Return toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 16)
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

	@Test(priority = 17)
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
