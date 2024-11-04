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

public class Expense_Type extends BaseTest {

	private static String moduleName = "ExpenseType";

	@Override
	protected void initializeLocators() {
		locators.put("newExpenseTypeButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("sectorDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
		locators.put("expenseTypeTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Expense Type TextBox"));
		locators.put("descriptionTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Description TextBox"));
		locators.put("accountingExpenseCodeTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Accounting Expense Code TextBox"));
		locators.put("availableToWorkerToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Available to Worker Toggle"));
		locators.put("amountToBeEnteredWithMSPFeeToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Amount to be Entered with MSP Fee Toggle"));
		locators.put("natureOfExpenseDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Nature Of Expense Dropdown"));

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
		testData.put("expenseTypeValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Expense Type Value"));
		testData.put("descriptionValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Description Value"));
		testData.put("accountingExpenseCodeValue",
				ReadExcel.getValueByLabel(moduleName, "Test Data", "Accounting Expense Code Value"));
		testData.put("natureOfExpenseValue",
				ReadExcel.getValueByLabel(moduleName, "Test Data", "Nature Of Expense Value"));
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
	public void testNewExpenseTypeButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newExpenseTypeButton");
			logInfo("User has clicked the new expense type button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new expense type button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new expense type button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new expense type button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new expense type button.", e);
		} catch (Exception e) {
			logError("Failed to click new expense type button: " + e.getMessage());
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
	public void testExpenseTypeTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "expenseTypeTextbox", testData.get("expenseTypeValue"));
			logInfo("User has entered value: " + testData.get("expenseTypeValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: expense type textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: expense type textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for expense type textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: expense type textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in expense type textbox: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 7)
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
	@Test(priority = 8)
	public void testAccountingExpenseCodeTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "accountingExpenseCodeTextbox",
					testData.get("accountingExpenseCodeValue"));
			logInfo("User has entered value: " + testData.get("accountingExpenseCodeValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: accounting expense code textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: accounting expense code textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for accounting expense code textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: accounting expense code textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in accounting expense code textbox: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 9)
	public void testAvailableToWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "availableToWorkerToggle");
			logInfo("User has clicked the available to worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: available to worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: available to worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for available to worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: available to worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click available to worker toggle: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 10)
	public void testAmountToBeEnteredWithMspFeeToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "amountToBeEnteredWithMspFeeToggle");
			logInfo("User has clicked the amount to be entered with MSP fee toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: amount to be entered with MSP fee toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: amount to be entered with MSP fee toggle.",
					e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for amount to be entered with MSP fee toggle to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: amount to be entered with MSP fee toggle.", e);
		} catch (Exception e) {
			logError("Failed to click amount to be entered with MSP fee toggle: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 11)
	public void testNatureOfExpenseDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "natureOfExpenseDropdown", testData.get("natureOfExpenseValue"));
			logInfo("User has selected/entered value: " + testData.get("natureOfExpenseValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: nature of expense dropdown - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: nature of expense dropdown.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for nature of expense dropdown to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: nature of expense dropdown.", e);
		} catch (Exception e) {
			logError("Failed to enter text in nature of expense dropdown: " + e.getMessage());
			throw e;
		}
	}
	@Test(priority = 12)
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
	@Test(priority = 13)
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
