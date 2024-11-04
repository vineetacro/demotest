package smoke_MasterModuleTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import projectUtilities.BaseTest;
import projectUtilities.ReadExcel;
import projectUtilities.CommonUtils;

public class Requisition_Library extends BaseTest{

	private static String moduleName = "RequisitionLibrary";
	
	@Override
	protected void initializeLocators() {
	locators.put("newRequisitionLibraryButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
	
	locators.put("businessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));	
	locators.put("laborCategoryDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Dropdown"));
	locators.put("jobCategoryDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Job Category Dropdown"));	
	locators.put("workLocationDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Work Location Dropdown"));
	
	locators.put("wageRateTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Wage Rate Text Box"));
	locators.put("billRateTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Bill Rate Text Box"));
	locators.put("preLaunchRateTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Pre Launch Rate Text Box"));
	locators.put("rateUnitFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Rate Unit Field Value"));
	locators.put("rateUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Rate Unit Dropdown"));
	
	locators.put("positionDescriptionTextArea", ReadExcel.getValueByLabel(moduleName, "Common Screen Elements", "Position Description Text Area"));
	locators.put("skillRequiredTextArea", ReadExcel.getValueByLabel(moduleName, "Common Screen Elements", "Skill Required Text Area"));
	locators.put("educationRequiredTextArea", ReadExcel.getValueByLabel(moduleName, "Common Screen Elements", "Education Required Text Area"));
	locators.put("experienceRequiredTextArea", ReadExcel.getValueByLabel(moduleName, "Common Screen Elements", "Experience Required Text Area"));

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
	
	testData.put("businessUnitValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Business Unit"));
	
	testData.put("laborCategoryValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Labor Category"));
	
	testData.put("jobCategoryValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Job Category"));
		
	testData.put("workLocationValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Work Location"));
	
	testData.put("wageRateValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Wage Rate"));
	
	testData.put("positionDescriptionValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Position Description"));
	
	testData.put("skillRequiredValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Skill Required"));
	
	testData.put("educationRequiredValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Education Required"));
	
	testData.put("experienceRequiredValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Experience Required"));
	}

	@Override
	protected void initializeExpectedValues() {
	expectedValues.put("successToastMessageForNewRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For New Record Creation"));			
	expectedValues.put("successToastMessageForRecordActivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Activation"));
	expectedValues.put("successToastMessageForRecordDeactivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Deactivation"));
	expectedValues.put("errorToastMessageForDuplicateRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Duplicate Record Creation"));
			
	expectedValues.put("fieldValidationMessageForBusinessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Business Unit Field Validation Message"));
	expectedValues.put("fieldValidationMessageForLaborCategoryDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Labor Category Field Validation Message"));
	expectedValues.put("fieldValidationMessageForJobCategoryDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Job Category Field Validation Message"));
	expectedValues.put("fieldValidationMessageForWorkLocationDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Work Location Field Validation Message"));
	expectedValues.put("fieldValidationMessageForWageRateTextBox", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Wage Rate Field Validation Message"));
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
        logInfo("User has navigated to the list screen of Job Category module successfully.");	
		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}
    }
    
   
    @Test(priority = 4)
    public void testNewJobCategoryButton() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
        	utils.clickElementWhenClickable(locators, "newJobCategoryButton");	
        	logInfo("User has clicked the New Labor Category button.");
		} catch (Exception e) {
			logError("Failed to click newJobCategoryButton: " + e.getMessage());
			throw e;
		}
        }
    
    @Test(priority = 5)
    public void testRedirectionToAddScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
		try {	
    	String navigatedURL = driver.getCurrentUrl();
        	
        Assert.assertEquals(navigatedURL, testData.get("addScreenURL"), "User has not been navigated to the desired add screen successfully.");	
        logInfo("User has navigated to the add screen of Job Category module successfully.");	
		} catch (Exception e) {
			logError("Failed to navigate to add screen: " + e.getMessage());
			throw e;
		}
		}
    
    @Test(priority = 6)
    public void testSaveButtonEnablement() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
        	utils.isButtonEnabled(locators, "saveButton");	
        	logInfo("Save button enablement has been verified successfully.");
		} catch (Exception e) {
			logError("Failed to check Save buttton enablement: " + e.getMessage());
			throw e;
		}
    }
    
    @Test(priority = 7)
    public void testFieldValidationForBusinessUnit() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
        	utils.clickElementWhenClickable(locators, "saveButton");	
        	logInfo("User has clicked the save button.");
		} catch (Exception e) {
			logError("Failed to click Save button: " + e.getMessage());
		}

        String fieldValidationMessageForBusinessUnit = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForBusinessUnitDropdown");
        try {
            Assert.assertEquals(fieldValidationMessageForBusinessUnit, expectedValues.get("fieldValidationMessageForBusinessUnitDropdown"));
            logInfo("Field validation message for the Business Unit dropdown field has been verified successfully.");    
        } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Business Unit dropdown field did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 8, enabled = false)
    public void testFieldValidationForLaborCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        String fieldValidationMessageForLaborCategory = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForLaborCategoryDropdown");
        try {
            Assert.assertEquals(fieldValidationMessageForLaborCategory, expectedValues.get("fieldValidationMessageForLaborCategoryDropdown"));
            logInfo("Field validation message for the Labor Category dropdown has been verified successfully.");    
        } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Labor Category dropdown did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        }
    
    @Test(priority = 9, enabled = false)
    public void testFieldValidationForJobCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        String fieldValidationMessageForJobCategory = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForJobCategoryTextBox");
        try {
            Assert.assertEquals(fieldValidationMessageForJobCategory, expectedValues.get("fieldValidationMessageForJobCategoryTextBox"));
            logInfo("Field validation message for the Job Category textbox field has been verified successfully.");    
        } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Job Category textbox field did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        }
    
    @Test(priority = 10)
    public void testSelectBusinessUnitAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Select Business Unit Value where 'RFx/SOW' was turned 'No'
        try {
        	utils.enterTextsWhenClickable(locators, "businessUnitDropdown", testData.get("businessUnitValue"));	
        	logInfo("User has selected a business Unit : " + testData.get("businessUnitValue"));
        } catch (Exception e) {
			logError("Failed to enter text in business unit dropdown: " + e.getMessage());
			throw e;
		}
        
        // Retrieve the selected value from the dropdown
        String selectedBusinessUnitValue = utils.getValueOfEditableField(driver, locators, "businessUnitDropdown");
        
        try {
            Assert.assertEquals(selectedBusinessUnitValue, testData.get("businessUnitValue"), "The selected Business Unit value is incorrect.");
            logInfo("The selected Business Unit value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected Business Unit value did not match the expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 11)
    public void testSelectLaborCategoryAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        try {
        	// Enter the value into the Labor Category text box
            utils.enterTextsWhenClickable(locators, "laborCategoryDropdown", testData.get("laborCategoryValue"));
            logInfo("User has selected the labor category value : " + testData.get("laborCategoryValue"));	
		} catch (Exception e) {
			logError("Error in selecting the labor category value in dropdown." + e.getMessage());
			throw e;
		}
        
        // Retrieve the entered value from the Labor Category text box
        String enteredLaborCategoryValue = utils.getValueOfEditableField(driver, locators, "laborCategoryDropdown");

        try {
            Assert.assertEquals(enteredLaborCategoryValue, testData.get("laborCategoryValue"), "The selected Labor Category value is incorrect.");
            logInfo("The selected Labor Category value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected Labor Category value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 12)
    public void testEnterJobCategoryAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
        	// Enter the value in the Job Category textbox.
            utils.enterTextsWhenClickable(locators, "jobCategoryTextBox", testData.get("jobCategoryValue"));
            logInfo("User has entered the Job Category value : " + testData.get("jobCategoryValue"));	
		} catch (Exception e) {
			logError("Error in entering the job category value in textbox." + e.getMessage());
			throw e;
		}

        // Retrieve the entered value from the Job Category textbox.
        String enteredJobCategoryValue = utils.getValueOfEditableField(driver, locators, "jobCategoryTextBox");

        try {
            Assert.assertEquals(enteredJobCategoryValue, testData.get("jobCategoryValue"), "The entered Job Category value is incorrect.");
            logInfo("The selected MSP Program Manager value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The entered Job Category value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 13)
    public void testEnterClientJobCodeAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
        	// Enter the value in the Client Job Code textbox
            utils.enterTextsWhenClickable(locators, "clientJobCodeTextBox", testData.get("clientJobCodeValue"));
            logInfo("User has entered the Client Job Code value : " + testData.get("clientJobCodeValue"));	
		} catch (Exception e) {
			logError("Error in entering the client job code value in textbox." + e.getMessage());
			throw e;
		}
        
        // Retrieve the entered value from the Client Job Code textbox
        String enteredClientJobCodeValue = utils.getValueOfEditableField(driver, locators, "clientJobCodeTextBox");

        try {
            Assert.assertEquals(enteredClientJobCodeValue, testData.get("clientJobCodeValue"), "The entered Client Job Code value is incorrect.");
            logInfo("The entered Client Job Code value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The entered Client Job Code value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 14)
    public void testToastMessageForNewRecordCreation() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            utils.clickElementWhenClickable(locators, "saveButton");
            logInfo("User has clicked the save button.");
		} catch (Exception e) {
			logError("Error in clicking the save button." + e.getMessage());
			throw e;
		}
        String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
        try {
            Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForNewRecord"));
            logInfo("Toast message for new record creation has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: Toast message for new record creation did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    
    @Test(priority = 15)
    public void testBusinessUnitFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
        	// Search for the added job category and click the view icon
            utils.searchAndTakeActionOnRecord(testData.get("jobCategoryValue"), "view");
            logInfo("User has searched for the newly created job category and clicked the view icon.");	
		} catch (Exception e) {
			logError("Error in searching the record and clicking the View icon : " + e.getMessage());
			throw e;
		}
        
        // Verify the value of the Business Unit field on the view screen
        String businessUnitValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "businessUnitFieldValue");
        try {
            Assert.assertEquals(businessUnitValueOnViewScreen, testData.get("businessUnitValue"), "Value of Business Unit has not been verified on the view screen.");
            logInfo("Business Unit value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Business Unit value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    
    @Test(priority = 16)
    public void testLaborCategoryFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category field on the view screen
        String laborCategoryValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "laborCategoryFieldValue");
        try {
            Assert.assertEquals(laborCategoryValueOnViewScreen, testData.get("laborCategoryValue"), "Value of Labor Category has not been verified on the view screen.");
            logInfo("Labor Category value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Labor Category value on the view screen did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    
    @Test(priority = 17)
    public void testJobCategoryFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Job Category field on the view screen
        String jobCategoryValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "jobCategoryFieldValue");
        try {
            Assert.assertEquals(jobCategoryValueOnViewScreen.trim().toLowerCase(), testData.get("jobCategoryValue").trim().toLowerCase(), "Value of Job Category has not been verified on the view screen.");
            logInfo("Job Category value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Job Category value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    
    @Test(priority = 18)
    public void testClientJobCodeFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category Type field on the view screen
        String clientJobCodeValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "clientJobCodeFieldValue");
        try {
            Assert.assertEquals(clientJobCodeValueOnViewScreen, testData.get("clientJobCodeValue"), "Value of Client Job Code has not been verified on the view screen.");
            logInfo("Client Job Code value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Client Job Code value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 19)
    public void testAllowWageRateAdjustmentsFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category Type field on the view screen
        String allowWageRateAdjustmentsValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "allowWageRateAdjustmentsFieldValue");
        try {
            Assert.assertEquals(allowWageRateAdjustmentsValueOnViewScreen, testData.get("allowWageRateAdjustmentsValue"), "Value of Allow Wage Rate Adjustments has not been verified on the view screen.");
            logInfo("Allow Wage Rate Adjustments field value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Allow Wage Rate Adjustments value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 20)
    public void testOvertimeHoursBilledAtFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Overtime Hours Billed At field on the view screen
        String overTimeHoursBilledAtFieldValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "overTimeHoursBilledAtFieldValue");
        try {
            Assert.assertEquals(overTimeHoursBilledAtFieldValueOnViewScreen, testData.get("overTimeHoursBilledAtValue"), "Value of Overtime Hours Billed At has not been verified on the view screen.");
            logInfo("Overtime Hours Billed At value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Overtime Hours Billed At value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 21)
    public void testBackButtonFunctionality() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        // Wait for navigation to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        try {
        	// Click the back button
            utils.clickElementWhenClickable(locators, "backButton");
            logInfo("User has clicked the back button.");	
		} catch (Exception e) {
		logError("Error in clicking the Back button : " + e.getMessage());
		throw e;
		}
        
        try {
            // Verify navigation back to the list screen
            wait.until(ExpectedConditions.urlToBe(testData.get("listScreenURL")));
            logInfo("User has been navigated back to the list screen successfully.");
        } catch (Exception e) {
            logError("Navigation to the list screen failed: " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
   
    @Test(priority = 22)
    public void testBusinessUnitFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        // Search for the added job category and click the edit icon
        utils.searchAndTakeActionOnRecord(testData.get("jobCategoryValue"), "edit");
        logInfo("User has searched for the newly created job category and clicked the edit icon.");

        // Verify the value of the Business Unit field value on the edit screen
        String businessUnitValueOnEditScreen = utils.getFieldLabelNameOrValue(locators, "businessUnitFieldValue");
        try {
            Assert.assertEquals(businessUnitValueOnEditScreen, testData.get("businessUnitValue"), "Value of Business Unit has not been verified on the edit screen.");
            logInfo("Business Unit value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Business Unit value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 23)
    public void testLaborCategoryFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category field value on the edit screen
        String laborCategoryValueOnEditScreen = utils.getFieldLabelNameOrValue(locators, "laborCategoryFieldValue");
        try {
            Assert.assertEquals(laborCategoryValueOnEditScreen, testData.get("laborCategoryValue"), "Value of Labor Category has not been verified on the edit screen.");
            logInfo("Labor Category value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Labor Category value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
        
    @Test(priority = 24)
    public void testJobCategoryFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the MSP Program Manager field value on the edit screen
        String jobCategoryValueOnEditScreen = utils.getValueOfEditableField(driver, locators, "jobCategoryTextBox");
        try {
            Assert.assertEquals(jobCategoryValueOnEditScreen, testData.get("jobCategoryValue"), "Value of Job Category has not been verified on the edit screen.");
            logInfo("Job Category value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Job Category value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
        
    @Test(priority = 25)
    public void testClientJobCodeFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category Type field on the edit screen
        String clientJobCodeValueOnEditScreen = utils.getValueOfEditableField(driver,locators, "clientJobCodeTextBox");
        try {
            Assert.assertEquals(clientJobCodeValueOnEditScreen, testData.get("clientJobCodeValue"), "Value of Client Job Code has not been verified on the edit screen.");
            logInfo("Client Job Code value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Client Job Code value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 26)
    public void testAllowWageRateAdjustmentsFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Overtime Hours Billed At field on the view screen
        String allowWageRateAdjustmentsFieldValueOnEditScreen = utils.getFieldLabelNameOrValue(locators, "allowWageRateAdjustmentsFieldValue");
        try {
            Assert.assertEquals(allowWageRateAdjustmentsFieldValueOnEditScreen, testData.get("allowWageRateAdjustmentsValue"), "Value of Allow Wage Rate Adjustments has not been verified on the edit screen.");
            logInfo("Allow Wage Rate Adjustments value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Allow Wage Rate Adjustments value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 27)
    public void testOvertimeHoursBilledAtFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        WebElement straightTimeExemptField = driver.findElement(By.xpath(locators.get("straightTimeExemptRadioButton")));
        WebElement overTimeNonExemptField = driver.findElement(By.xpath(locators.get("overTimeNonExemptRadioButton")));
        
        // Verify the value of the Overtime Hours Billed At field on the view screen
        boolean straightTimeFieldValueOnEditScreen = straightTimeExemptField.isSelected();
        boolean overTimeFieldValueOnEditScreen = overTimeNonExemptField.isSelected();
        //System.out.println(straightTimeFieldValueOnEditScreen);
        //System.out.println(overTimeFieldValueOnEditScreen);
        
        try {
            Assert.assertFalse(straightTimeFieldValueOnEditScreen, "Straight Time (Exempt) radio button value is not matched.");
        	logInfo("Straight Time (Exempt) radio button is not selected.");
        	
        	Assert.assertTrue(overTimeFieldValueOnEditScreen, "Over Time (Non-Exempt) radio button value is not matched.");
        	logInfo("Over Time (Non-Exempt) radio button is selected.");
        } catch (AssertionError e) {
            logError("Assertion failed: Overtime Hours Billed At value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 28)
    public void testCancelButtonFunctionality() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        // Click the back button
        utils.clickElementWhenClickable(locators, "cancelButton");
        logInfo("User has clicked the cancel button.");

        // Wait for navigation to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Verify navigation back to the list screen
            wait.until(ExpectedConditions.urlToBe(testData.get("listScreenURL")));
        } catch (Exception e) {
            logError("Navigation to the list screen failed: " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }logInfo("User has been navigated back to the list screen successfully.");
    }
    
    @Test(priority = 29)
    public void testRedirectionOfNewRequisitionLibraryButtonOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Search for the added job category and click the edit icon
        utils.searchAndTakeActionOnRecord(testData.get("jobCategoryValue"), "edit");
        logInfo("User has searched for the newly created job category and clicked the edit icon.");
        
        // Click the new requisition library button
        utils.clickElementWhenClickable(locators, "newRequisitionLibraryButton");
        logInfo("User has clicked the new requisition library button.");
        
        String expectedURLPart = "requisition-library";
        
        wait.until(ExpectedConditions.urlContains(expectedURLPart));

        // Verify the value of the Business Unit field value on the edit screen
        String actualTitle = utils.getFieldLabelNameOrValue(locators, "title");
        
        try {
            Assert.assertEquals(actualTitle, expectedValues.get("titleOnRequisitionLibraryScreen"), "Value of title has not been verified on the requisition library add screen.");
            logInfo("Title value has been verified successfully on the add screen of requisition library.");
        } catch (AssertionError e) {
            logError("Assertion failed: Title value on the add screen of requisition library did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 30)
    public void testBusinessUnitIsSameAsSelectedInJobCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        //Retrieve the value of Business Unit field from Requisition Library.
        String actualBusinessUnitValue = utils.getValueOfEditableField(driver, locators, "businessUnitDropdown");
        
        // Verify that it is same value as selected in Job Category.
        try {
        	Assert.assertEquals(actualBusinessUnitValue, testData.get("businessUnitValue"), "Value of business unit on requisition library has not been matched with the expected one.");
		} catch (AssertionError e) {
			logError("Assertion failed: Business Unit value on the add screen of requisition library did not match expected value. " + e.getMessage());
            throw e;
		}logInfo("Value of Business Unit has been verified successfully.");
 	}
    
    @Test(priority = 31)
    public void testLaborCategoryIsSameAsSelectedInJobCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
      //Retrieve the value of Labor Category field from Requisition Library.
        String actualLaborCategoryValue = utils.getValueOfEditableField(driver, locators, "laborCategoryDropdown");
        
        // Verify that it is same value as selected in Job Category.
        try {
        	Assert.assertEquals(actualLaborCategoryValue, testData.get("laborCategoryValue"), "Value of labor category on requisition library has not been matched with the expected one.");
		} catch (AssertionError e) {
			logError("Assertion failed: Labor Category value on the add screen of requisition library did not match expected value. " + e.getMessage());
            throw e;
		}logInfo("Value of Labor Category has been verified successfully.");
    }
    
    @Test(priority = 32)
    public void testJobCategoryIsSameAsSelectedInJobCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
      //Retrieve the value of Job Category field from Requisition Library.
        String actualJobCategoryValue = utils.getValueOfEditableField(driver, locators, "jobCategoryDropdown");
        
        // Verify that it is same value as selected in Job Category.
        try {
        	Assert.assertEquals(actualJobCategoryValue, testData.get("jobCategoryValue"), "Value of job category on requisition library has not been matched with the expected one.");
		} catch (AssertionError e) {
			logError("Assertion failed: Job Category value on the add screen of requisition library did not match expected value. " + e.getMessage());
            throw e;
		}logInfo("Value of Job Category has been verified successfully.");
    }

    @Test(priority = 33)
    public void testSelectWorkLocationAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Select Work Location Value on Requisition Library screen.
        try {
        	utils.enterTextsWhenClickable(locators, "workLocationDropdown", testData.get("workLocationValue"));	
		} catch (Exception e) {
			logError("Failed to enter text in work location dropdown: " + e.getMessage());
			throw e;
		}logInfo("User has selected a work location : " + testData.get("workLocationValue"));

        // Retrieve the selected value from the dropdown
        String selectedWorkLocationValue = utils.getValueOfEditableField(driver, locators, "workLocationDropdown");
        
        try {
            Assert.assertEquals(selectedWorkLocationValue, testData.get("workLocationValue"), "The selected Work Location value is incorrect.");
            logInfo("The selected Work Location value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected Work Location value did not match the expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 34)
    public void testEnterWageRateAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Select Work Location Value on Requisition Library screen.
        try {
        	utils.enterTextsWhenClickable(locators, "wageRateTextBox", testData.get("wageRateValue"));	
		} catch (Exception e) {
			logError("Failed to enter text in wage rate textbox: " + e.getMessage());
			throw e;
		}logInfo("User has entered wage rate : " + testData.get("wageRateValue"));

        // Retrieve the selected value from the dropdown
        String enteredWageRateValue = utils.getValueOfEditableField(driver, locators, "wageRateTextBox");
        
        try {
            Assert.assertEquals(enteredWageRateValue, testData.get("wageRateValue"), "The selected Wage Rate value is incorrect.");
            logInfo("The entered Wage Rate value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected Wage Rate value did not match the expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 35)
    public void testToastMessageForNewRequisitionLibraryRecordCreation() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
            //utils.clickElementWhenClickable(locators, "saveButton");
            utils.clickElementWithJS(driver, locators, "saveButton");
        	logInfo("User has clicked the save button.");
		} catch (Exception e) {
			logError("Failed in clicking the save button." + e.getMessage());
			throw e;
		}

        String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
        try {
            Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForNewRequisitionLibraryRecord"));
            logInfo("Toast message for new requisition library record creation has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: Toast message for new requisition library record creation did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
}

