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

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import projectUtilities.BaseTest;
import projectUtilities.ReadExcel;
import projectUtilities.CommonUtils;

public class Labor_Category extends BaseTest{

	private static String moduleName = "LaborCategory";
	
	@Override
	protected void initializeLocators() {
	locators.put("newLaborCategoryButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
	
	locators.put("businessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
	locators.put("fieldValidationMessageForBusinessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Field Validation Message"));
	locators.put("businessUnitFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Business Unit Field Value"));
	
	locators.put("laborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Text Box"));
	locators.put("fieldValidationMessageForLaborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Field Validation Message"));
	locators.put("laborCategoryFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Labor Category Field Value"));
	
	locators.put("mspProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "MSP Program Manager Dropdown"));
	locators.put("fieldValidationMessageForMspProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "MSP Program Manager Field Validation Message"));
	locators.put("mspProgramManagerFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "MSP Program Manager Field Value"));
	
	locators.put("laborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Dropdown"));
	locators.put("laborCategoryTypeDropdownIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Dropdown Icon"));
	locators.put("fieldValidationMessageForLaborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Field Validation Message"));
	locators.put("laborCategoryTypeFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Labor Category Type Field Value"));
	
	locators.put("alternatePricingModelConfigurationToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Alternate Pricing Model Configuration Toggle Field"));
		
	locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
	locators.put("cancelButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cancel Button"));
	locators.put("backButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Back Button"));
	locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
	locators.put("dropdownValues", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Dropdown values popup"));
	locators.put("inactiveTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Inactive Tab"));
	locators.put("activeTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Active Tab"));
	locators.put("allTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "All Tab"));
	locators.put("cancelIconOnToast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cancel icon on toast"));
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
	testData.put("businessUnitValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Business Unit"));
	
	testData.put("laborCategoryValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Labor Category"));
	testData.put("laborCategoryValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Labor Category"));
	
	testData.put("mspProgramManagerValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "MSP Program Manager"));
	
	testData.put("laborCategoryTypeValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Labor Category Type"));
	testData.put("laborCategoryTypeValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Labor Category Type"));
	
	testData.put("alternatePricingModelConfigurationToggleValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Alternate Pricing Model Toggle"));
	}

	@Override
	protected void initializeExpectedValues() {
	expectedValues.put("successToastMessageForNewRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For New Record Creation"));			
	expectedValues.put("successToastMessageForRecordActivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Activation"));
	expectedValues.put("successToastMessageForRecordDeactivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Deactivation"));
	expectedValues.put("errorToastMessageForDuplicateRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Duplicate Record Creation"));
			
	expectedValues.put("fieldValidationMessageForBusinessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Business Unit Field Validation Message"));
	expectedValues.put("fieldValidationMessageForLaborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Labor Category Field Validation Message"));
	expectedValues.put("fieldValidationMessageForLaborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Labor Category Type Field Validation Message"));
	expectedValues.put("fieldValidationMessageForMSPProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "MSP Program Manager Field Validation Message"));
	
	expectedValues.put("businessUnitFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Business Unit Field Label"));
	expectedValues.put("laborCategoryFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Labor Category Field Label"));
	expectedValues.put("mspProgramManagerFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "MSP Program Manager Field Label"));
	expectedValues.put("laborCategoryTypeFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Labor Category Type Field Label"));
	expectedValues.put("alternatePricingModelConfigurationToggleFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Alternate Pricing Model Configuration Field Label"));
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
		} logInfo("User has been logged in successfully.");
        
    }
    
    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testNavigationToHomeScreen() throws Exception {
        try {
            // Wait for the home screen URL
            logger.info("Waiting for the login URL to be the home page URL.");
            wait.until(ExpectedConditions.urlToBe(testData.get("homeScreenURL")));
            String navigatedURL = driver.getCurrentUrl();
            Assert.assertEquals(navigatedURL, testData.get("homeScreenURL"), "User has not been navigated to the Home Screen successfully.");
        }catch (TimeoutException e) {
            logError("Timeout while waiting for the home screen URL: " + e.getMessage());
            throw e;
        }catch (AssertionError e) {
            logError("Assertion failed: " + e.getMessage());
            throw e;
        }catch (Exception e) {
            logError("Failed to redirect to home screen: " + e.getMessage());
            throw e;
        }logInfo("User has been redirected to the home screen successfully.");
    }
    
    
    @Test(priority = 3)
    public void testRedirectionToListScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
		try {
    	utils.navigateToURL(testData.get("listScreenURL"));	
        	
    	String navigatedURL = driver.getCurrentUrl();
        	
        Assert.assertEquals(navigatedURL, testData.get("listScreenURL"), "User has not been navigated to the desired list screen successfully.");	
        	
		} catch (Exception e) {
			logError("Failed to navigate to list screen: " + e.getMessage());
			throw e;
		}logInfo("User has navigated to the list screen of Labor Category module successfully.");
    }
    
    @Test(priority = 4)
    public void testNewLaborCategoryButton() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        try {
        	utils.clickElementWhenClickable(locators, "newLaborCategoryButton");	
        	logInfo("User has clicked the New Labor Category button.");
		} catch (Exception e) {
			logError("Failed to click newLaborCategoryButton: " + e.getMessage());
			throw e;
		}
        }
    
    @Test(priority = 5)
    public void testRedirectionToAddScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
		try {	
    	String navigatedURL = driver.getCurrentUrl();
        	
        Assert.assertEquals(navigatedURL, testData.get("addScreenURL"), "User has not been navigated to the desired add screen successfully.");	
        logInfo("User has navigated to the add screen of Labor Category module successfully.");
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
		} catch (Exception e) {
			logError("Failed to click Save button: " + e.getMessage());
		}
        logInfo("User has clicked the save button.");

        String fieldValidationMessageForBusinessUnit = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForBusinessUnitDropdown");
        try {
            Assert.assertEquals(fieldValidationMessageForBusinessUnit, expectedValues.get("fieldValidationMessageForBusinessUnitDropdown"), "Field validation for the Business Unit has not been verified successfully.");
            } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Business Unit dropdown field did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        logInfo("Field validation message for the Business Unit dropdown field has been verified successfully.");
    }
    
    @Test(priority = 8)
    public void testFieldValidationForLaborCategory() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        String fieldValidationMessageForLaborCategory = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForLaborCategoryTextBox");
        try {
            Assert.assertEquals(fieldValidationMessageForLaborCategory, expectedValues.get("fieldValidationMessageForLaborCategoryTextBox"));
            } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Labor Category text box did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        logInfo("Field validation message for the Labor Category text box has been verified successfully.");
    }
    
    @Test(priority = 9)
    public void testFieldValidationForMSPProgramManager() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        String fieldValidationMessageForMSPProgramManager = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForMspProgramManagerDropdown");
        try {
            Assert.assertEquals(fieldValidationMessageForMSPProgramManager, expectedValues.get("fieldValidationMessageForMSPProgramManagerDropdown"));
            } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the MSP Program Manager dropdown field did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        logInfo("Field validation message for the MSP Program Manager dropdown field has been verified successfully.");
    }

    @Test(priority = 10)
    public void testFieldValidationForLaborCategoryType() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        String fieldValidationMessageForLaborCategoryType = utils.getValidationMessageFromFieldOrToast(driver, locators, "fieldValidationMessageForLaborCategoryTypeDropdown");
        try {
            Assert.assertEquals(fieldValidationMessageForLaborCategoryType, expectedValues.get("fieldValidationMessageForLaborCategoryTypeDropdown"));
            } catch (AssertionError e) {
            logError("Assertion failed: Field validation message for the Labor Category Type dropdown field did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
        logInfo("Field validation message for the Labor Category Type dropdown field has been verified successfully.");
    }
    
    @Test(priority = 11)
    public void testSelectBusinessUnitAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Select Business Unit Value where 'RFx/SOW' was turned 'No'
        try {
        	utils.enterTextsWhenClickable(locators, "businessUnitDropdown", testData.get("businessUnitValue"));	
		} catch (Exception e) {
			logError("Failed to enter text in business unit dropdown: " + e.getMessage());
			throw e;
		}
        logInfo("User has selected a non-RFx business Unit : " + testData.get("businessUnitValue"));

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
   
    @Test(priority = 12)
    public void testEnterLaborCategoryAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Enter the value into the Labor Category text box
        utils.enterTextsWhenClickable(locators, "laborCategoryTextBox", testData.get("laborCategoryValue"));
        logInfo("User has entered the labor category value : " + testData.get("laborCategoryValue"));

        // Retrieve the entered value from the Labor Category text box
        String enteredLaborCategoryValue = utils.getValueOfEditableField(driver, locators, "laborCategoryTextBox");

        try {
            Assert.assertEquals(enteredLaborCategoryValue, testData.get("laborCategoryValue"), "The entered Labor Category value is incorrect.");
            logInfo("The entered Labor Category value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The entered Labor Category value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 13)
    public void testSelectMspProgramManagerAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Select the value in the MSP Program Manager dropdown
        utils.enterTextsWhenClickable(locators, "mspProgramManagerDropdown", testData.get("mspProgramManagerValue"));
        logInfo("User has selected the MSP Program Manager value : " + testData.get("mspProgramManagerValue"));

        // Retrieve the selected value from the MSP Program Manager dropdown
        String selectedMspProgramManagerValue = utils.getValueOfEditableField(driver, locators, "mspProgramManagerDropdown");

        try {
            Assert.assertEquals(selectedMspProgramManagerValue, testData.get("mspProgramManagerValue"), "The selected MSP Program Manager value is incorrect.");
            logInfo("The selected MSP Program Manager value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected MSP Program Manager value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 14)
    public void testSelectLaborCategoryTypeAndValidate() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Select the value in the Labor Category Type dropdown
        utils.enterTextsWhenClickable(locators, "laborCategoryTypeDropdown", testData.get("laborCategoryTypeValue"));
        logInfo("User has selected the Labor Category Type value : " + testData.get("laborCategoryTypeValue"));

        // Retrieve the selected value from the Labor Category Type dropdown
        String selectedLaborCategoryTypeValue = utils.getValueOfEditableField(driver, locators, "laborCategoryTypeDropdown");

        try {
            Assert.assertEquals(selectedLaborCategoryTypeValue, testData.get("laborCategoryTypeValue"), "The selected Labor Category Type value is incorrect.");
            logInfo("The selected Labor Category Type value has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: The selected Labor Category Type value did not match the expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 15)
    public void testToastMessageForNewRecordCreation() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        utils.clickElementWhenClickable(locators, "saveButton");
        logInfo("User has clicked the save button.");

        String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
        try {
            Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForNewRecord"));
            logInfo("Toast message for new record creation has been verified successfully.");
        } catch (AssertionError e) {
            logError("Assertion failed: Toast message for new record creation did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    
    @Test(priority = 16)
    public void testBusinessUnitFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        // Search for the added labor category and click the view icon
        utils.searchAndTakeActionOnRecord(testData.get("laborCategoryValue"), "view");
        logInfo("User has searched for the newly created labor category and clicked the view icon.");

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
    
    /*
    @Test(priority = 17)
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
    
    @Test(priority = 18)
    public void testViewMSPProgramManagerFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the MSP Program Manager field on the view screen
        String mspProgramManagerValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "mspProgramManagerFieldValue");
        try {
            Assert.assertEquals(mspProgramManagerValueOnViewScreen.trim().toLowerCase(), testData.get("mspProgramManagerValue").trim().toLowerCase(), "Value of MSP Program Manager has not been verified on the view screen.");
            logInfo("MSP Program Manager value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: MSP Program Manager value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 19)
    public void testViewLaborCategoryTypeFieldValueOnViewScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category Type field on the view screen
        String laborCategoryTypeValueOnViewScreen = utils.getFieldLabelNameOrValue(locators, "laborCategoryTypeFieldValue");
        try {
            Assert.assertEquals(laborCategoryTypeValueOnViewScreen, testData.get("laborCategoryTypeValue"), "Value of Labor Category Type has not been verified on the view screen.");
            logInfo("Labor Category Type value has been verified successfully on the view screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Labor Category Type value on the view screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 20)
    public void testBackButtonFunctionality() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        // Click the back button
        utils.clickElementWhenClickable(locators, "backButton");
        logInfo("User has clicked the back button.");

        // Wait for navigation to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Verify navigation back to the list screen
            wait.until(ExpectedConditions.urlToBe(testData.get("listScreenURL")));
            logInfo("User has been navigated back to the list screen successfully.");
        } catch (Exception e) {
            logError("Navigation to the list screen failed: " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }

    @Test(priority = 21)
    public void testBusinessUnitFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        // Search for the added labor category and click the view icon
        utils.searchAndTakeActionOnRecord(testData.get("laborCategoryValue"), "edit");
        logInfo("User has searched for the newly created labor category and clicked the edit icon.");

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
    
    @Test(priority = 22)
    public void testLaborCategoryFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category field value on the edit screen
        String businessUnitValueOnEditScreen = utils.getValueOfEditableField(driver, locators, "laborCategoryTextBox");
        try {
            Assert.assertEquals(businessUnitValueOnEditScreen, testData.get("laborCategoryValue"), "Value of Labor Category has not been verified on the edit screen.");
            logInfo("Labor Category value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Labor Category value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 23)
    public void testMSPProgramManagerValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the MSP Program Manager field value on the edit screen
        String mspProgramManagerValueOnEditScreen = utils.getValueOfEditableField(driver, locators, "mspProgramManagerDropdown");
        try {
            Assert.assertEquals(mspProgramManagerValueOnEditScreen, testData.get("mspProgramManagerValue"), "Value of MSP Program Manager has not been verified on the edit screen.");
            logInfo("MSP Program Manager value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: MSP Program Manager value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 24)
    public void testLaborCategoryTypeFieldValueOnEditScreen() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);

        // Verify the value of the Labor Category Type field on the edit screen
        String laborCategoryTypeValueOnEditScreen = utils.getValueOfEditableField(driver,locators, "laborCategoryTypeDropdown");
        try {
            Assert.assertEquals(laborCategoryTypeValueOnEditScreen, testData.get("laborCategoryTypeValue"), "Value of Labor Category Type has not been verified on the edit screen.");
            logInfo("Labor Category Type value has been verified successfully on the edit screen.");
        } catch (AssertionError e) {
            logError("Assertion failed: Labor Category Type value on the edit screen did not match expected value. " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 25)
    public void testCancelButtonFunctionality() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        utils.clickElementWhenClickable(locators, "cancelButton");
        logInfo("User has clicked the cancel button.");


        // Wait for navigation to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Verify navigation back to the list screen
            wait.until(ExpectedConditions.urlToBe(testData.get("listScreenURL")));
            logInfo("User has been navigated back to the list screen successfully.");
        } catch (Exception e) {
            logError("Navigation to the list screen failed: " + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    */
    @Test(priority = 26)
    public void testDeactivationFunctionality() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
        	utils.searchAndTakeActionOnRecord(testData.get("laborCategoryValue"), "deactivate");
            logInfo("User has clicked the deactivate icon.");

            String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
            Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForRecordDeactivation"));
            logInfo("Toast message for record deactivation has been verified successfully.");
            utils.clickElementWhenClickable(locators, "cancelIconOnToast");
        } catch (AssertionError e) {
            logError("Assertion failed: Toast message for record deactivation did not match expected value." + e.getMessage());
            throw e; // rethrow the exception to fail the test
        }
    }
    
    @Test(priority = 27)
    public void testInactiveRecordIsNotUnderActiveTab() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        //Click Inactive tab.
		//utils.clickElementWhenClickable(locators, "inactiveTab");
		//logger.info("User clicked the Inactive Tab successfully.");
        try {
        	//Search record and check it is not available under Active tab.
    		boolean isRecordAvailableUnderActiveTab = utils.searchAndCheckIsRecordAvailableInGrid(testData.get("laborCategoryValue"));
    		Assert.assertFalse(isRecordAvailableUnderActiveTab, "Inactive record found under Active Tab.");	
    		logInfo("The deactivated record is not available under Active tab.");
		} catch (AssertionError e) {
			logError("Assertion failed: Inactive record is available under Active tab." + e.getMessage());
			throw e;
        } catch (Exception e) {
			logError("Exception : Failed to check the inactive record under Active tab." + e.getMessage());
			throw e;
		}
    }
    
    @Test(priority = 28)
    public void testInactiveRecordIsUnderInactiveTab() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
            //Click Inactive tab.
    		utils.clickElementWhenClickable(locators, "inactiveTab");
    		logInfo("User clicked the Inactive Tab successfully.");
    		
        	//Search record and check it is not available under Active tab.
    		boolean isRecordAvailableUnderInactiveTab = utils.searchAndCheckIsRecordAvailableInGrid(testData.get("laborCategoryValue"));
    		Assert.assertTrue(isRecordAvailableUnderInactiveTab, "Inactive record not found under Inactive Tab.");	
    		logInfo("The deactivated record is available under Inactive tab.");
		} catch (AssertionError e) {
			logError("Assertion failed: Inactive record is not available under Inactive tab." + e.getMessage());
			throw e;
        } catch (Exception e) {
			logError("Exception : Failed to check the inactive record under Inactive tab." + e.getMessage());
			throw e;
		}
    }
    
    @Test(priority = 29)
    public void testInactiveRecordIsUnderAllTab() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
            //Click Inactive tab.
    		utils.clickElementWhenClickable(locators, "allTab");
    		logInfo("User clicked the All Tab successfully.");
    		
        	//Search record and check it is not available under Active tab.
    		boolean isRecordAvailableUnderAllTab = utils.searchAndCheckIsRecordAvailableInGrid(testData.get("laborCategoryValue"));
    		Assert.assertTrue(isRecordAvailableUnderAllTab, "Inactive record not found under All Tab.");	
    		logInfo("The inactive record is available under All tab.");
		} catch (AssertionError e) {
			logError("Assertion failed: Inactive record is not available under All tab." + e.getMessage());
			throw e;
        } catch (Exception e) {
			logError("Exception : Failed to check the inactive record under All tab." + e.getMessage());
			throw e;
		}
    }
    
    @Test(priority = 30)
    public void testActivationFunctionalityUnderAllTab() throws Exception {
        CommonUtils utils = new CommonUtils(driver, moduleName);
        
        try {
        	utils.searchAndTakeActionOnRecord(testData.get("laborCategoryValue"), "activate");
        	logInfo("User clicked the activate icon successfully.");
        	
        	String toastMessage = utils.getValidationMessageFromFieldOrToast(driver, locators, "toast");
            try {
                Assert.assertEquals(toastMessage, expectedValues.get("successToastMessageForRecordActivation"));
                logInfo("Toast message for record activation has been verified successfully.");
            } catch (AssertionError e) {
                logError("Assertion failed: Toast message for record activation did not match expected value." + e.getMessage());
                throw e; // rethrow the exception to fail the test
            }
        	
        	//Search record and check it is not available under Active tab.
    		boolean isRecordAvailableUnderInactiveTab = utils.searchAndCheckIsRecordAvailableInGrid(testData.get("laborCategoryValue"));
    		Assert.assertTrue(isRecordAvailableUnderInactiveTab, "Inactive record not found under All Tab.");	
    		logInfo("The deactivated record is available under All tab.");
		} catch (AssertionError e) {
			logError("Assertion failed: Inactive record is not available under All tab." + e.getMessage());
			throw e;
        } catch (Exception e) {
			logError("Exception : Failed to check the inactive record under All tab." + e.getMessage());
			throw e;
		}
    }
}

