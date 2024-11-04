//import java.time.Duration;
//
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import projectUtilities.BaseTest;
//import projectUtilities.CommonUtils;
//import projectUtilities.ReadExcel;
//
//public class Labor_Category extends BaseTest{
//
//	private static String moduleName = "LaborCategory";
//	
//	@Override
//	protected void initializeLocators() {
//	locators.put("newLaborCategoryButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
//	
//	locators.put("businessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Dropdown"));
//	locators.put("fieldValidationMessageForBusinessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Business Unit Field Validation Message"));
//	locators.put("businessUnitFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Business Unit Field Value"));
//	
//	locators.put("laborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Text Box"));
//	locators.put("fieldValidationMessageForLaborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Field Validation Message"));
//	locators.put("laborCategoryFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Labor Category Field Value"));
//	
//	locators.put("mspProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "MSP Program Manager Dropdown"));
//	locators.put("fieldValidationMessageForMspProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "MSP Program Manager Field Validation Message"));
//	locators.put("mspProgramManagerFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "MSP Program Manager Field Value"));
//	
//	locators.put("laborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Dropdown"));
//	locators.put("laborCategoryTypeDropdownIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Dropdown Icon"));
//	locators.put("fieldValidationMessageForLaborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Labor Category Type Field Validation Message"));
//	locators.put("laborCategoryTypeFieldValue", ReadExcel.getValueByLabel(moduleName, "View Screen Elements", "Labor Category Type Field Value"));
//	
//	locators.put("alternatePricingModelConfigurationToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Alternate Pricing Model Configuration Toggle Field"));
//		
//	locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
//	locators.put("cancelButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cancel Button"));
//	locators.put("backButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Back Button"));
//	locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
//	locators.put("dropdownValues", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Dropdown values popup"));
//	locators.put("inactiveTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Inactive Tab"));
//	locators.put("activeTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Active Tab"));
//	locators.put("allTab", ReadExcel.getValueByLabel(moduleName, "Common Elements", "All Tab"));
//	locators.put("cancelIconOnToast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cancel icon on toast"));
//	}
//
//	@Override
//	protected void initializeTestData() {
//	testData.put("loginURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Login"));
//	testData.put("listScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "List Screen"));
//	testData.put("homeScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Home Screen"));
//	testData.put("addScreenURL", ReadExcel.getValueByLabel(moduleName, "RndURLs", "Add Screen"));
//	testData.put("userIdValue", ReadExcel.getValueByLabel(moduleName, "RndLoginData", "User Id"));
//	testData.put("passwordValue", ReadExcel.getValueByLabel(moduleName, "RndLoginData", "Password"));
//	
//	testData.put("businessUnitValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Business Unit"));
//	testData.put("businessUnitValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Business Unit"));
//	
//	testData.put("laborCategoryValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Labor Category"));
//	testData.put("laborCategoryValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Labor Category"));
//	
//	testData.put("mspProgramManagerValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "MSP Program Manager"));
//	
//	testData.put("laborCategoryTypeValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Labor Category Type"));
//	testData.put("laborCategoryTypeValue_IC/SOW", ReadExcel.getValueByLabel(moduleName, "Data_TC_02", "Labor Category Type"));
//	
//	testData.put("alternatePricingModelConfigurationToggleValue", ReadExcel.getValueByLabel(moduleName, "Data_TC_01", "Alternate Pricing Model Toggle"));
//	}
//
//	@Override
//	protected void initializeExpectedValues() {
//	expectedValues.put("successToastMessageForNewRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For New Record Creation"));			
//	expectedValues.put("successToastMessageForRecordActivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Activation"));
//	expectedValues.put("successToastMessageForRecordDeactivation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Record Deactivation"));
//	expectedValues.put("errorToastMessageForDuplicateRecord", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Toast Message For Duplicate Record Creation"));
//			
//	expectedValues.put("fieldValidationMessageForBusinessUnitDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Business Unit Field Validation Message"));
//	expectedValues.put("fieldValidationMessageForLaborCategoryTextBox", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Labor Category Field Validation Message"));
//	expectedValues.put("fieldValidationMessageForLaborCategoryTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Labor Category Type Field Validation Message"));
//	expectedValues.put("fieldValidationMessageForMSPProgramManagerDropdown", ReadExcel.getValueByLabel(moduleName, "Expected Values", "MSP Program Manager Field Validation Message"));
//	
//	expectedValues.put("businessUnitFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Business Unit Field Label"));
//	expectedValues.put("laborCategoryFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Labor Category Field Label"));
//	expectedValues.put("mspProgramManagerFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "MSP Program Manager Field Label"));
//	expectedValues.put("laborCategoryTypeFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Labor Category Type Field Label"));
//	expectedValues.put("alternatePricingModelConfigurationToggleFieldLabel", ReadExcel.getValueByLabel(moduleName, "Expected Field Label", "Alternate Pricing Model Configuration Field Label"));
//	}
//	
//    @Test(priority = 1)
//    public void testLogin() throws Exception {
//        CommonUtils utils = new CommonUtils(driver, moduleName);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        try {
//        	utils.login(driver, testData.get("userIdValue"), testData.get("passwordValue"), testData.get("loginURL"));
//		} catch (Exception e) {
//			logError("Failed to login: " + e.getMessage());
//			throw e;
//		} logInfo("User has been logged in successfully.");
//        
//    }
//    
//    @Test(priority = 2, dependsOnMethods = "testLogin")
//    public void testNavigationToHomeScreen() throws Exception {
//        try {
//            // Wait for the home screen URL
//            logger.info("Waiting for the login URL to be the home page URL.");
//            wait.until(ExpectedConditions.urlToBe(testData.get("homeScreenURL")));
//            String navigatedURL = driver.getCurrentUrl();
//            Assert.assertEquals(navigatedURL, testData.get("homeScreenURL"), "User has not been navigated to the Home Screen successfully.");
//        }catch (TimeoutException e) {
//            logError("Timeout while waiting for the home screen URL: " + e.getMessage());
//            throw e;
//        }catch (AssertionError e) {
//            logError("Assertion failed: " + e.getMessage());
//            throw e;
//        }catch (Exception e) {
//            logError("Failed to redirect to home screen: " + e.getMessage());
//            throw e;
//        }logInfo("User has been redirected to the home screen successfully.");
//    }
//    
//    
//    @Test(priority = 3)
//    public void testRedirectionToListScreen() throws Exception {
//        CommonUtils utils = new CommonUtils(driver, moduleName);
//        
//		try {
//    	utils.navigateToURL(testData.get("listScreenURL"));	
//        	
//    	String navigatedURL = driver.getCurrentUrl();
//        	
//        Assert.assertEquals(navigatedURL, testData.get("listScreenURL"), "User has not been navigated to the desired list screen successfully.");	
//        	
//		} catch (Exception e) {
//			logError("Failed to navigate to list screen: " + e.getMessage());
//			throw e;
//		}logInfo("User has navigated to the list screen of Labor Category module successfully.");
//    }
//
//}