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

public class Candidate_Decline_Reason extends BaseTest {

	private static String moduleName = "CandidateDeclineReason";

	@Override
	protected void initializeLocators() {
		locators.put("newCandidateDeclineReasonButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("sectorDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Dropdown"));
		locators.put("declineReasonTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Decline Reason Textbox"));
		locators.put("professionalPsrRequestToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "ProfessionalPSRRequest Toggle"));
		locators.put("liRequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "LI Request Toggle"));
		locators.put("icSowRequestToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "IC/SOW Request Toggle"));
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

		testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Sector Value"));
		testData.put("declineReasonValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Decline Reason Value"));
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
	public void testNewCandidateDeclineReasonButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newCandidateDeclineReasonButton");
			logInfo("User has clicked the new candidate decline reason button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new candidate decline reason button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new candidate decline reason button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new candidate decline reason button to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: new candidate decline reason button.", e);
		} catch (Exception e) {
			logError("Failed to click new candidate decline reason button: " + e.getMessage());
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
	public void testDeclineReasonTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "declineReasonTextbox", testData.get("declineReasonValue"));
			logInfo("User has entered value: " + testData.get("declineReasonTextbox"));
		} catch (NoSuchElementException e) {
			logError("Element not found: decline reason textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: decline reason textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for decline reason textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: decline reason textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in decline reason textbox: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7)
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

	@Test(priority = 8)
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

	@Test(priority = 9)
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

	@Test(priority = 10)
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

	@Test(priority = 11)
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