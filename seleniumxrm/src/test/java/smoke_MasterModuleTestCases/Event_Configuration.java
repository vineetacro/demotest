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

public class Event_Configuration extends BaseTest {

	private static String moduleName = "EventConfiguration";

	@Override
	protected void initializeLocators() {
		locators.put("newEventConfigurationButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
		locators.put("businessUnitDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
		locators.put("eventNameTextBox",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Event Name Text Box"));
		locators.put("dateIncurredRadioButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Date Incurred Radio Button"));
		locators.put("fromAndThroughDatesRadioButton",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "From and Through Dates Radio Button"));
		locators.put("eventReasonRequiredToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Event Reason Required Toggle"));
		locators.put("commentsRequiredToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Comments Required Toggle"));
		locators.put("needBackfillToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Need Backfill Toggle"));
		locators.put("effectOnDailyScheduleDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Effect on Daily Schedule Dropdown"));
		locators.put("eventEnteredByDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Event Entered By Dropdown"));
		locators.put("notifyToDropdown",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Notify To Dropdown"));
		locators.put("delayEmailNotificationToJustBeforeEventDateToggle", ReadExcel.getValueByLabel(moduleName,
				"Common Elements", "Delay Email Notification to Just before Event Date Toggle"));
		locators.put("validateEventDateWithTimesheetToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Validate Event Date with Timesheet Toggle"));
		locators.put("professionalWorkerToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Professional Worker Toggle"));
		locators.put("lightIndustrialWorkerToggle",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Light Industrial Worker Toggle"));

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

		testData.put("businessUnitValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Business Unit"));
		testData.put("eventNameValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Event Name"));
		testData.put("effectOnDailyScheduleValue",
				ReadExcel.getValueByLabel(moduleName, "Test Data", "Effect on Daily Schedule"));
		testData.put("eventEnteredByValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Event Entered By"));
		testData.put("notifyToValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Notify To"));

	}

	@Override
	protected void initializeExpectedValues() {
		expectedValues.put("successToastMessageForNewRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values",
				"Success Toast Message For New Record Creation"));
	}

	@Test(priority = 1)
	public void testLogin() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			utils.login(driver, testData.get("userIdValue"), testData.get("passwordValue"), testData.get("loginURL"));
			logInfo("User has been logged in successfully.");
		} catch (Exception e) {
			logError("Failed to login: " + e.getMessage());
			throw e;
		}
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
			logInfo("User has been redirected to the home screen successfully.");
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
	}

	@Test(priority = 3)
	public void testRedirectionToListScreen() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);

		try {
			utils.navigateToURL(testData.get("listScreenURL"));

			String navigatedURL = driver.getCurrentUrl();

			Assert.assertEquals(navigatedURL, testData.get("listScreenURL"),
					"User has not been navigated to the desired list screen successfully.");
			logInfo("User has navigated to the list screen of " + moduleName + " module successfully.");
		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 4)
	public void testNewEventConfigurationButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "newEventConfigurationButton");
			logInfo("User has clicked the new event configuration button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: new event configuration button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: new event configuration button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for new event configuration button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: new event configuration button.", e);
		} catch (Exception e) {
			logError("Failed to click new event configuration button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 5)
	public void testSectorDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "businessUnitDropdown", testData.get("businessUnitValue"));
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
	public void testEventNameTextbox() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "eventNameTextbox", testData.get("eventNameValue"));
			logInfo("User has entered value: " + testData.get("eventNameValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: event name textbox - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: event name textbox.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for event name textbox to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: event name textbox.", e);
		} catch (Exception e) {
			logError("Failed to enter text in event name textbox: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 7)
	public void testDateIncurredRadioButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "dateIncurredRadioButton");
			logInfo("User has clicked the Date Incurred radio button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Date Incurred radio button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Date Incurred radio button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Date Incurred radio button to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Date Incurred radio button.", e);
		} catch (Exception e) {
			logError("Failed to click Date Incurred radio button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 8)
	public void testFromAndThroughDatesRadioButton() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "fromAndThroughDatesRadioButton");
			logInfo("User has clicked the From and Through Dates radio button.");
		} catch (NoSuchElementException e) {
			logError("Element not found: From and Through Dates radio button - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: From and Through Dates radio button.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for From and Through Dates radio button to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: From and Through Dates radio button.", e);
		} catch (Exception e) {
			logError("Failed to click From and Through Dates radio button: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 9)
	public void testEventReasonRequiredToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "eventReasonRequiredToggle");
			logInfo("User has clicked the Event Reason Required toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Event Reason Required toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Event Reason Required toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Event Reason Required toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Event Reason Required toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Event Reason Required toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 10)
	public void testCommentsRequiredToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "commentsRequiredToggle");
			logInfo("User has clicked the Comments Required toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Comments Required toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Comments Required toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Comments Required toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Comments Required toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Comments Required toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 11)
	public void testNeedBackfillToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "needBackfillToggle");
			logInfo("User has clicked the Need Backfill toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Need Backfill toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Need Backfill toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Need Backfill toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Need Backfill toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Need Backfill toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 12)
	public void testEffectOnDailyScheduleDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "effectOnDailyScheduleDropdown",
					testData.get("effectOnDailyScheduleValue"));
			logInfo("User has selected/entered value: " + testData.get("effectOnDailyScheduleValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: Effect on Daily Schedule dropdown - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Effect on Daily Schedule dropdown.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Effect on Daily Schedule dropdown to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Effect on Daily Schedule dropdown.", e);
		} catch (Exception e) {
			logError("Failed to enter text in Effect on Daily Schedule dropdown: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 13)
	public void testEventEnteredByDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "eventEnteredByDropdown", testData.get("eventEnteredByValue"));
			logInfo("User has selected/entered value: " + testData.get("eventEnteredByValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: Event Entered By dropdown - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Event Entered By dropdown.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Event Entered By dropdown to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Event Entered By dropdown.", e);
		} catch (Exception e) {
			logError("Failed to enter text in Event Entered By dropdown: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 14)
	public void testNotifyToDropdown() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.enterTextsWhenClickable(locators, "notifyToDropdown", testData.get("notifyToValue"));
			logInfo("User has selected/entered value: " + testData.get("notifyToValue"));
		} catch (NoSuchElementException e) {
			logError("Element not found: Notify To dropdown - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Notify To dropdown.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Notify To dropdown to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Notify To dropdown.", e);
		} catch (Exception e) {
			logError("Failed to enter text in Notify To dropdown: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 15)
	public void testDelayEmailNotificationToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "delayEmailNotificationToggle");
			logInfo("User has clicked the Delay Email Notification to Just before Event Date toggle.");
		} catch (NoSuchElementException e) {
			logError(
					"Element not found: Delay Email Notification to Just before Event Date toggle - " + e.getMessage());
			throw new AssertionError(
					"Test failed due to missing element: Delay Email Notification to Just before Event Date toggle.",
					e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Delay Email Notification toggle to become clickable - " + e.getMessage());
			throw new AssertionError(
					"Test failed due to timeout: Delay Email Notification to Just before Event Date toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Delay Email Notification to Just before Event Date toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 16)
	public void testValidateEventDateWithTimesheetToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "validateEventDateWithTimesheetToggle");
			logInfo("User has clicked the Validate Event Date with Timesheet toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Validate Event Date with Timesheet toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Validate Event Date with Timesheet toggle.",
					e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Validate Event Date with Timesheet toggle to become clickable - "
					+ e.getMessage());
			throw new AssertionError("Test failed due to timeout: Validate Event Date with Timesheet toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Validate Event Date with Timesheet toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 17)
	public void testProfessionalWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "professionalWorkerToggle");
			logInfo("User has clicked the Professional Worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Professional Worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Professional Worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Professional Worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Professional Worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Professional Worker toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 18)
	public void testLightIndustrialWorkerToggle() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			utils.clickElementWhenClickable(locators, "lightIndustrialWorkerToggle");
			logInfo("User has clicked the Light Industrial Worker toggle.");
		} catch (NoSuchElementException e) {
			logError("Element not found: Light Industrial Worker toggle - " + e.getMessage());
			throw new AssertionError("Test failed due to missing element: Light Industrial Worker toggle.", e);
		} catch (TimeoutException e) {
			logError("Timed out waiting for Light Industrial Worker toggle to become clickable - " + e.getMessage());
			throw new AssertionError("Test failed due to timeout: Light Industrial Worker toggle.", e);
		} catch (Exception e) {
			logError("Failed to click Light Industrial Worker toggle: " + e.getMessage());
			throw e;
		}
	}

	@Test(priority = 19)
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

	@Test(priority = 20)
	public void testToastMessageVerification() throws Exception {
		CommonUtils utils = new CommonUtils(driver, moduleName);
		try {
			// Retrieve the success message from the toast
			String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
			Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForNewRecord"),
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
