package smoke_MasterModuleTestCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import projectUtilities.BaseTest;
import projectUtilities.CommonUtils;
import projectUtilities.ReadExcel;

public class Sector extends BaseTest{

private static String moduleName = "Sector";

@Override
protected void initializeLocators() {
locators.put("newSectorButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Button on List Screen"));
locators.put("showAllSectionsToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Show All Sections Toggle"));
locators.put("sectorNameTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sector Name TextBox"));
locators.put("addressLine1TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Address Line 1 TextBox"));
locators.put("addressLine2TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Address Line 2 TextBox"));
locators.put("countryDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Country Dropdown"));
locators.put("stateDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "State Dropdown"));
locators.put("cityTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "City TextBox"));
locators.put("timeZoneDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Time Zone Dropdown"));
locators.put("zipCodeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Zip Code TextBox"));
locators.put("homeLanguageDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Home Language Dropdown"));
locators.put("passwordExpiryPeriodDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Password Expiry Period Dropdown"));
locators.put("initialGoLiveDate", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Initial Go Live Date"));
locators.put("weekendingDayDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Weekending Day Dropdown"));
locators.put("limitAvailableWeekendingDatesInTimeandExpenseEntryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Limit Available Weekending Dates in Time and Expense Entry Toggle"));
locators.put("NumberOfPreviousWeekendingDatesAllowedTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "No. of Previous Weekending Dates Allowed TextBox"));

locators.put("organizationLevel1TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 1 TextBox"));
locators.put("organizationLevel1UtilizeToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 1 Utilize Toggle"));
locators.put("organizationLevel1MandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 1 Mandatory Toggle"));
locators.put("organizationLevel2TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 2 TextBox"));
locators.put("organizationLevel2UtilizeToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 2 Utilize Toggle"));
locators.put("organizationLevel2MandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 2 Mandatory Toggle"));
locators.put("organizationLevel3TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 3 TextBox"));
locators.put("organizationLevel3UtilizeToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 3 Utilize Toggle"));
locators.put("organizationLevel3MandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 3 Mandatory Toggle"));
locators.put("organizationLevel4TextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 4 TextBox"));
locators.put("organizationLevel4UtilizeToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 4 Utilize Toggle"));
locators.put("organizationLevel4MandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Organization Level 4 Mandatory Toggle"));

locators.put("adderRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Adder Radio Button"));
locators.put("multiplierRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Multiplier Radio Button"));

locators.put("periodOfPerformanceRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Period of Performance Radio Button"));
locators.put("budgetedHoursRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Budgeted Hours Radio Button"));
locators.put("mspFeeTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "MSP Fee Type Dropdown"));
locators.put("clientPaysStaffingAgencyDirectlyToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Client Pays Staffing Agency Directly Toggle"));
locators.put("billRateBasedRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Bill Rate Based Radio Button"));
locators.put("markupBasedRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Markup Based Radio Button"));
locators.put("nteRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "NTE Radio Button"));
locators.put("targetRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Target Radio Button"));
locators.put("otVisibleToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "OT Visible Toggle"));

locators.put("otDtRatesCalculatedBasedOnDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "OT/DT Rates Calculated Based On Dropdown"));
locators.put("otWageMultiplierTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "OT Wage Multiplier TextBox"));
locators.put("dtWageMultiplierTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "DT Wage Multiplier TextBox"));
locators.put("otBillMultiplierTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "OT Bill Multiplier TextBox"));
locators.put("dtBillMultiplierTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "DT Bill Multiplier TextBox"));
locators.put("recruitedLiMspFeeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Recruited/LI MSP Fee TextBox"));
locators.put("payrolledMspFeeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Payrolled MSP Fee TextBox"));
locators.put("vendorFeeMultiplierTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Vendor Fee Multiplier TextBox"));
locators.put("recruitedAdminFeeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Recruited Admin Fee TextBox"));
locators.put("payrollAdminFeeTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Payroll Admin Fee TextBox"));
locators.put("standardRecruitedMarkupTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Standard Recruited Markup TextBox"));
locators.put("maskOTFieldsInSystemToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Mask OT Fields In System Toggle"));

locators.put("workerJobRotationAllowedToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Worker Job Rotation Allowed Toggle"));
locators.put("allTimeAdjustmentsRequireApprovalToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "All Time Adjustments Require Approval Toggle"));
locators.put("allowTimeUploadWithStOtDtToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow Time Upload with ST/OT/DT Toggle"));
locators.put("validateApprovedAmountWithTimeAndExpenseRecordsToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Validate Approved Amount with Time and Expense Records Toggle"));
locators.put("displayStaffingAgencyNameInT&EApprovalToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Display Staffing Agency Name in T&E Approval Toggle"));
locators.put("timeUploadAsApprovedHoursToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Time Upload As Approved Hours Toggle"));
locators.put("adjustmentsToUploadedHoursAreAutoApprovedToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Adjustments To Uploaded Hours Are Auto Approved Toggle"));
locators.put("primaryManagerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Primary Manager Toggle"));
locators.put("poOwnerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "PO Owner Toggle"));
locators.put("defaultPOForRecruitmentTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Default PO# for Recruitment Textbox"));
locators.put("defaultPOForPayrolledTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Default PO# for Payrolled Textbox"));
locators.put("numberOfConsecutiveWeeksWithMissingTimesheetTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Number of Consecutive Weeks With Missing Timesheet Textbox"));

locators.put("allowProcessExtensionAdjustmentToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow Process Extension/Adjustment Toggle"));
locators.put("assignmentExtensionRateIncreaseAllowedToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Assignment Extension Rate Increase Allowed Toggle"));
locators.put("trainingModuleRequiredToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Training Module Required Toggle"));
locators.put("previousAssignmentWageRateSelectableInFillARequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Previous Assignment Wage Rate Selectable In Fill A Request Toggle"));
locators.put("changeRateWithoutEffectiveDateToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Change Rate without Effective Date Toggle"));
locators.put("allowMSPToAdjustStaffingAgencyMarkupPercentWhenProcessingPSRToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow MSP To Adjust Staffing Agency Markup Percent When Processing PSR Toggle"));
locators.put("offBoardIntervalTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Off Board Interval Textbox"));
locators.put("offBoardIntervalForLITextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Off Board Interval For LI Textbox"));

locators.put("tenurePolicyApplicableToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Tenure Policy Applicable Toggle"));
locators.put("WorkersAllowableTenureIsRenewedAfterResetPeriodToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Workers Allowable Tenure Is Renewed After Reset Period Toggle"));
locators.put("tenureLimitTypeHoursRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Tenure Limit Type Hours Radio Button"));
locators.put("tenureLimitTypeMonthsRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Tenure Limit Type Months Radio Button"));
locators.put("requisitionTenureLimitTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Requisition Tenure Limit Textbox"));
locators.put("extensionTenureLimitTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Extension Tenure Limit Textbox"));
locators.put("workerTenureLimitTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Worker Tenure Limit Textbox"));
locators.put("tenureResetPeriodTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Tenure Reset Period Textbox"));

locators.put("showExtendedWorkLocationAddressToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Show Extended Work Location Address Toggle"));
locators.put("displayCanStaffingAgencyContactQuestionInReqToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Display Can Staffing Agency Contact Question In Req Toggle"));
locators.put("restrictRequisitionsToOnePositionToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Restrict Requisitions to One Position Toggle"));
locators.put("maskSubmittedMarkUpAndWageRateToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Mask Submitted Mark Up% and Wage Rate Toggle"));
locators.put("securityClearanceItemSelectableToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Security Clearance Item Selectable Toggle"));
locators.put("questionBankFunctionalityRequiredToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Question Bank Functionality Required Toggle"));
locators.put("questionToBeAnsweredByDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Question To Be Answered By Dropdown"));
locators.put("questionBankLabelTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Question Bank Label Textbox"));
locators.put("broadcastIntervalDayTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Broadcast Interval Day Textbox"));
locators.put("noWeeksAheadForLIFillRatePlanEntryTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "# Weeks Ahead for LI Fill Rate Plan Entry Textbox"));
locators.put("positionDetailsEditableToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Position Details Editable Toggle"));
locators.put("rateExceptionAllowedToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Rate Exception Allowed Toggle"));

locators.put("includeSystemCandidateRankingFunctionalityToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Include System Candidate Ranking Functionality Toggle"));
locators.put("systemCandidateRankingMandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "System Candidate Ranking Mandatory Toggle"));
locators.put("enableManagerScoringToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Enable Manager Scoring Toggle"));
locators.put("managerScoringMandatoryToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Manager Scoring Mandatory Toggle"));

locators.put("evaluationTypeDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Evaluation Type Dropdown"));
locators.put("evaluationItemTitleTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Evaluation Item Title Textbox"));
locators.put("evaluationItemVisibleToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Evaluation Item Visible Toggle"));
locators.put("assignmentTypeItemTitleTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Assignment Type Item Title Textbox"));

locators.put("sendSubmittalReminderToManagerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Send Submittal Reminder to Manager Toggle"));
locators.put("submittalReminderIntervalTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Submittal Reminder Interval Textbox"));
locators.put("sendReminderToStaffingAgenciesForNotOfferAcceptingToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Send Reminder To Staffing Agencies For Not Offer Accepting Toggle"));
locators.put("submittalReminderIntervalToStaffingAgencyTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Submittal Reminder Interval To Staffing Agency Textbox"));

locators.put("requireBenefitAddersToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Require Benefit Adders Toggle"));
locators.put("requireBenefitAdderTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Require Benefit Adder Textbox"));

locators.put("skipProcessRequisitionByMspToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Skip Process Requisition By MSP Toggle"));
locators.put("skipProcessSubmittalByMspToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Skip Process Submittal By MSP Toggle"));
locators.put("hideNteFromRequisitionLibraryDropdownInRequisitionToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Hide NTE from Requisition Library Dropdown in Requisition Toggle"));
locators.put("skipLiRequestProcessByMspToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Skip LI Request Process by MSP Toggle"));
locators.put("autoBroadcastForLiRequestToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Auto Broadcast For LI Request Toggle"));

locators.put("rFxSowRequiredToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "RFx/SOW Required Toggle"));
locators.put("sowMSPFeeTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "SOW MSP Fee Textbox"));
locators.put("icMSPFeeTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "IC MSP Fee Textbox"));
locators.put("sowAdminFeeTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "SOW Admin Fee Textbox"));

locators.put("costAccountingCodeHaveSpecificApproversToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code Have Specific Approvers Toggle"));
locators.put("costAccountingCodetoBeEnteredManuallyToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code To Be Entered Manually Toggle"));
locators.put("costAccountingCodesAreOnlyValidForSpecificDateRangesToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code are Only Valid for Specific Date Ranges Toggle"));
locators.put("costAccountingCodeRequiredInRequisitionPSRToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code Required In Requisition/PSR Toggle"));
locators.put("numberOfSegmentsDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Number Of Segments Dropdown"));
locators.put("costAccountingCodeLabelNameTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code Label Name Textbox"));
locators.put("costAccountingCodeLabelNameMinLengthTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code Label Name Min Length Textbox"));
locators.put("costAccountingCodeLabelNameMaxLengthTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Cost Accounting Code Label Name Max Length Textbox"));

locators.put("drugResultVisibleToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Drug Result Visible Toggle"));
locators.put("drugResultEditableToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Drug Result Editable Toggle"));
locators.put("drugResultDefaultValueYesRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Drug Result Default Value Yes Radio Button"));
locators.put("drugResultDefaultValueNoRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Drug Result Default Value No Radio Button"));

locators.put("backgroundChecksVisibleToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Background Checks Visible Toggle"));
locators.put("backgroundChecksEditableToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Background Checks Editable Toggle"));
locators.put("backgroundChecksDefaultValueYesRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Background Checks Default Value Yes Radio Button"));
locators.put("backgroundChecksDefaultValueNoRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Background Checks Default Value No Radio Button"));

locators.put("pendingDrugAndBackgroundChecksAllowedForLIWorkerToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Pending Drug And Background Checks Allowed For LI Worker Toggle"));
locators.put("onboardingItemsLabelNameTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Onboarding Items Label Name Textbox"));
locators.put("onboardingItemsProfessionalToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Onboarding Items Professional Toggle"));
locators.put("onboardingItemsLIToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Onboarding Items LI Toggle"));
locators.put("onboardingItemsICSOWToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Onboarding Items IC SOW Toggle"));

locators.put("enableXrmTimeClockToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Enable Xrm Time Clock Toggle"));
locators.put("dailyPunchApprovalNeededToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Daily Punch Approval Needed Toggle"));
locators.put("allowManagerToAdjustPunchInOutToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow Manager to Adjust Punch In/Out Toggle"));
locators.put("accrueHoursFromActualPunchInToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Accrue Hours from Actual Punch In Toggle"));
locators.put("allowManualSelectionDuringPunchForCostAccountingCodeToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Allow Manual Selection During Punch For Cost Accounting Code"));
locators.put("xrmUseEmployeeIdRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "XRM Use Employee Id Radio Button"));
locators.put("xrmUseTimeClockIdRadioButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "XRM Use Time Clock Id Radio Button"));
locators.put("clockBufferToSetReportingDateTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Clock Buffer To Set Reporting Date Textbox"));
locators.put("clockBufferForShowingWorkerEarlyShiftStartTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Clock Buffer For Showing Worker Early Shift Start Textbox"));
locators.put("autoLunchDeductionToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Auto Lunch Deduction Toggle"));
locators.put("minimumHourToBeWorkedBeforeLunchDeductionTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Minimum Hour To Be Worked Before Lunch Deduction Textbox"));
locators.put("lunchTimeToBeDeductedTextbox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Lunch Time To Be Deducted Textbox"));
locators.put("punchRoundingNeededToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch Rounding Needed Toggle"));
locators.put("punchInTimeIncrementRoundingDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch In Time Increment Rounding Dropdown"));
locators.put("punchOutTimeIncrementRoundingDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch Out Time Increment Rounding Dropdown"));
locators.put("punchInTimeRoundingDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch In Time Rounding Dropdown"));
locators.put("punchOutTimeRoundingDropdown", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Punch Out Time Rounding Dropdown"));

locators.put("addQuickLinkToApprovalEmailsToggle", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Add Quick Link To Approval Emails Toggle"));
locators.put("saveButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
locators.put("cancelButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save Button"));
locators.put("saveAndContinueLaterButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Save And Continue Later Button"));

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
