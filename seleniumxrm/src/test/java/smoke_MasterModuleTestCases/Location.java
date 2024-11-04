package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

public class Location extends BaseTest{

private static String moduleName = "Location";

@Override
protected void initializeLocators() {
locators.put("newLocationButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
locators.put("sectorDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Dropdown"));
locators.put("locationNameTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Location Name TextBox"));

locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
}

@Override
protected void initializeTestData() {
testData.put("sectorValue", ReadExcel.getValueByLabel(moduleName, "Test Data", "Sector Value"));
testData.put("orgLevel1Value", ReadExcel.getValueByLabel(moduleName, "Test Data", "Organization Level 1 Value"));
}

@Override
protected void initializeExpectedValues() {
expectedValues.put("successMessageForRecordCreation", ReadExcel.getValueByLabel(moduleName, "Expected Values", "Success Message On Record Creation")); 
}

@Test
public void TC_001() {

CommonUtils utils = new CommonUtils(driver, moduleName);
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

}

}
