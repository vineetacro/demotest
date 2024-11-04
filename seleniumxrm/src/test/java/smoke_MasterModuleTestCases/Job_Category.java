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

public class Job_Category extends BaseTest{

	private static String moduleName = "JobCategory";
	
	@Override
	protected void initializeLocators() {
	locators.put("newJobCategoryButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
	
	locators.put("businessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
	locators.put("laborCategoryDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Dropdown"));	
	locators.put("jobCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Job Category Text Box"));	
	locators.put("clientJobCodeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Client Job Code Text Box"));
	locators.put("allowWageRateAdjustmentsToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow Wage Rate Adjustments Toggle"));	
	locators.put("straightTimeExemptRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Straight Time Exempt Radio Button"));	
	locators.put("overTimeNonExemptRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Overtime Non Exempt Radio Button"));
			
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
	testData.put("laborCategoryValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Labor Category"));	
	testData.put("jobCategoryValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Job Category"));		
	testData.put("clientJobCodeValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Client Job Code"));			
	}

	@Override
	protected void initializeExpectedValues() {
	expectedValues.put("successToastMessageForNewRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For New Record Creation"));			
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
            Assert.assertEquals(navigatedURL, testData.get("homeScreenURL"), "User has not been navigated to the Home Screen successfully.");
            logInfo("User has been redirected to the home screen successfully.");
        }catch (TimeoutException e) {
            logError("Timeout while waiting for the home screen URL: " + e.getMessage());
            throw e;
        }catch (AssertionError e) {
            logError("Assertion failed: " + e.getMessage());
            throw e;
        }catch (Exception e) {
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
        	
        Assert.assertEquals(navigatedURL, testData.get("listScreenURL"), "User has not been navigated to the desired list screen successfully.");	
		logInfo("User has navigated to the list screen of " + moduleName + " module successfully.");
		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}
    }
    @Test(priority = 4)
    public void testNewJobCategoryButton() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.clickElementWhenClickable(locators, "newJobCategoryButton");
            logInfo("User has clicked the new job category button.");
        } catch (NoSuchElementException e) {
            logError("Element not found: new job category button - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: new job category button.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for new job category button to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: new job category button.", e);
        } catch (Exception e) {
            logError("Failed to click new job category button: " + e.getMessage());
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
    public void testLaborCategoryDropdown() throws Exception { 
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.enterTextsWhenClickable(locators, "laborCategoryDropdown", testData.get("laborCategoryValue")); 
            logInfo("User has selected/entered value: " + testData.get("laborCategoryValue"));
        } catch (NoSuchElementException e) {
            logError("Element not found: labor category dropdown - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: labor category dropdown.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for labor category dropdown to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: labor category dropdown.", e);
        } catch (Exception e) {
            logError("Failed to enter text in labor category dropdown: " + e.getMessage());
            throw e;
        }
    }
    @Test(priority = 7)
    public void testJobCategoryTextbox() throws Exception { 
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.enterTextsWhenClickable(locators, "jobCategoryTextbox", testData.get("jobCategoryValue")); 
            logInfo("User has entered value: " + testData.get("jobCategoryValue"));
        } catch (NoSuchElementException e) {
            logError("Element not found: job category textbox - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: job category textbox.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for job category textbox to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: job category textbox.", e);
        } catch (Exception e) {
            logError("Failed to enter text in job category textbox: " + e.getMessage());
            throw e;
        }
    }
    @Test(priority = 8)
    public void testClientJobCodeTextbox() throws Exception { 
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.enterTextsWhenClickable(locators, "clientJobCodeTextbox", testData.get("clientJobCodeValue")); 
            logInfo("User has entered value: " + testData.get("clientJobCodeValue"));
        } catch (NoSuchElementException e) {
            logError("Element not found: client job code textbox - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: client job code textbox.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for client job code textbox to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: client job code textbox.", e);
        } catch (Exception e) {
            logError("Failed to enter text in client job code textbox: " + e.getMessage());
            throw e;
        }
    }
    @Test(priority = 9)
    public void testAllowWageRateAdjustmentsToggle() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.clickElementWhenClickable(locators, "allowWageRateAdjustmentsToggle");
            logInfo("User has clicked the allow wage rate adjustments toggle.");
        } catch (NoSuchElementException e) {
            logError("Element not found: allow wage rate adjustments toggle - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: allow wage rate adjustments toggle.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for allow wage rate adjustments toggle to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: allow wage rate adjustments toggle.", e);
        } catch (Exception e) {
            logError("Failed to click allow wage rate adjustments toggle: " + e.getMessage());
            throw e;
        }
    }
    @Test(priority = 10)
    public void testStraightTimeRadioButton() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.clickElementWhenClickable(locators, "straightTimeRadioButton");
            logInfo("User has clicked the straight time radio button.");
        } catch (NoSuchElementException e) {
            logError("Element not found: straight time radio button - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: straight time radio button.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for straight time radio button to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: straight time radio button.", e);
        } catch (Exception e) {
            logError("Failed to click straight time radio button: " + e.getMessage());
            throw e;
        }
    }
    @Test(priority = 11)
    public void testOvertimeRadioButton() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.clickElementWhenClickable(locators, "overtimeRadioButton");
            logInfo("User has clicked the overtime radio button.");
        } catch (NoSuchElementException e) {
            logError("Element not found: overtime radio button - " + e.getMessage());
            throw new AssertionError("Test failed due to missing element: overtime radio button.", e);
        } catch (TimeoutException e) {
            logError("Timed out waiting for overtime radio button to become clickable - " + e.getMessage());
            throw new AssertionError("Test failed due to timeout: overtime radio button.", e);
        } catch (Exception e) {
            logError("Failed to click overtime radio button: " + e.getMessage());
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
            Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForNewRecord"), "The toast success message did not match the expected value.");
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

