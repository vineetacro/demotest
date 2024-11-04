package projectUtilities;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

public class CommonUtils extends BaseTest {

	static Map<String, String> locators = new HashMap<>();
	static WebDriver driver;
	static WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);

	public CommonUtils(WebDriver driver, String moduleName) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		initializeLocators(moduleName);
	}

	private static void initializeLocators(String moduleName) {
		// Initialize common locators
		locators.put("userIdTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "User ID Text Box"));
		locators.put("nextButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Next Button"));
		locators.put("passwordTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Password Text Box"));
		locators.put("loginButton", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Login Button"));
		locators.put("userProfileIcon",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Logged In User Profile Icon"));
		locators.put("signOutLink",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Sign Out Link Under Profile Icon"));

		locators.put("quickSearchTextBox", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Search Text Box"));
		locators.put("searchIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Search Icon"));
		locators.put("loader", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Loader"));
		locators.put("toast", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Toast"));
		locators.put("activateIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Activate Icon"));
		locators.put("deactivateIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Deactivate Icon"));
		locators.put("viewIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "View Icon"));
		locators.put("reviewIcon", ReadExcel.getValueByLabel(moduleName, "Common Elements", "Review Icon"));
		locators.put("editIconOnListScreen",
				ReadExcel.getValueByLabel(moduleName, "Common Elements", "Edit Icon On List Screen"));
		locators.put("gridData", ReadExcel.getValueByLabel(moduleName, "Common Elements", "gridData"));
	}

	// Generic method for login with UserId, Password, Login URL, and verify that
	// the user has landed on home page by verifying the Home URL.
	public void login(WebDriver driver, String userID, String password, String loginURL) {
		if (driver == null) {
			throw new IllegalArgumentException("WebDriver cannot be null.");
		}
		if (userID == null || userID.isEmpty()) {
			throw new IllegalArgumentException("UserID cannot be null or empty.");
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be null or empty.");
		}
		if (loginURL == null || loginURL.isEmpty()) {
			throw new IllegalArgumentException("Login URL cannot be null or empty.");
		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			// Go to login URL.
			driver.get(loginURL);
			logInfo(("Navigated to Login URL: " + loginURL));

			// Wait for loader to disappear
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locators.get("loader"))));
				logInfo("Loader disappeared successfully.");
			} catch (TimeoutException e) {
				logError("Timeout waiting for loader to disappear: " + e.getMessage());
				throw e; // Re-throw to let the test fail
			}

			try {
				// Wait for the User ID element to be clickable and then fill user id
				WebElement userIdTextBox = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("userIdTextBox"))));
				userIdTextBox.sendKeys(userID);
				logInfo("UserID entered: " + userID);

				// Wait for the Next button to be clickable and click Next button to proceed
				WebElement nextButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("nextButton"))));
				nextButton.click();
				logInfo("Next button clicked.");

				// Wait for password field and fill password
				WebElement passwordTextBox = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(locators.get("passwordTextBox"))));
				passwordTextBox.sendKeys(password);
				logInfo("Password entered: " + password);

				// Wait for the Login button to be clickable and then click Login button
				WebElement loginButton = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("loginButton"))));
				loginButton.click();
				logInfo("Login button clicked.");

			} catch (TimeoutException e) {
				logError("login Method Exception : Timeout occurred while waiting for an element: " + e.getMessage());
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				logError("login Method Exception : Element not found: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logError("login Method Exception : An unexpected error occurred: " + e.getMessage());
				e.printStackTrace();
			}
			// Wait for loader to disappear
			logInfo("Waiting for loader to get disappear.");
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locators.get("loader"))));
				logInfo("Loader disappeared successfully.");
			} catch (TimeoutException e) {
				logError("Timeout waiting for loader to disappear: " + e.getMessage());
				throw e; // Re-throw to let the test fail
			}
		} catch (Exception e) {
			logError("Error in login method: " + e.getMessage());
		}
	}

	// Generic method to wait for an element to be clickable and click it.[It will
	// click element with the help of Xpath.]
	/*
	 * public void clickElementWhenClickable(Map<String, String> locators, String
	 * locatorKey) {
	 * logInfo("clickElementWhenClickable locatorKey : "+locators.get(locatorKey));
	 * try { WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	 * try {
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(
	 * locators.get("loader")))); logInfo("Loader disappeared successfully."); }
	 * catch (TimeoutException e) {
	 * logError("Timeout waiting for loader to disappear: " + e.getMessage()); throw
	 * e; // Re-throw to let the test fail } WebElement element = wait
	 * .until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get(
	 * locatorKey)))); element.click();
	 * logInfo("User clicked the expected element : " + locatorKey); } catch
	 * (TimeoutException e) { logError(
	 * "clickElementWhenClickable Method Exception : Timeout while waiting for element to be clickable: "
	 * + locatorKey + ". Error: " + e.getMessage()); e.printStackTrace(); } catch
	 * (NoSuchElementException e) {
	 * logError("clickElementWhenClickable Method Exception : Element not found: " +
	 * locatorKey + ". Error: " + e.getMessage()); e.printStackTrace(); } catch
	 * (ElementClickInterceptedException e) {
	 * logError("clickElementWhenClickable Method Exception : Element was not clickable (intercepted): "
	 * + locatorKey + ". Error: " + e.getMessage()); e.printStackTrace(); } catch
	 * (Exception e) { logError(
	 * "clickElementWhenClickable Method Exception : Unexpected error in clickElementWhenClickable method: "
	 * + locatorKey + ". Error: " + e.getMessage()); e.printStackTrace(); } }
	 */

	// Generic method to wait for an element to be clickable and click it.[It will
	// click element with the help of Xpath.]
	public void clickElementWhenClickable(Map<String, String> locators, String locatorKey) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get(locatorKey))));
			element.click();
			Thread.sleep(1000);
			logger.info("User clicked the expected element : " + locatorKey);
		} catch (Exception e) {
			logger.error("Error in clickElementWhenClickable method: " + locatorKey + e.getMessage());
		}
	}

	// Generic method to wait for an element to be clickable and send keys to it
	public void enterTextsWhenClickable(Map<String, String> locators, String locatorKey, String text) {
		try {
			logInfo("Text entered in the expected element : " + locators.get(locatorKey) + ": " + text);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get(locatorKey))));
			element.clear();
			element.sendKeys(text);
			Thread.sleep(1000);
			element.sendKeys(Keys.ENTER);
		} catch (TimeoutException e) {
			logError("enterTextsWhenClickable Method Exception : Timeout while waiting for element to be clickable: "
					+ locatorKey + ". Error: " + e.getMessage());
		} catch (NoSuchElementException e) {
			logError("enterTextsWhenClickable Method Exception : Element not found for locator key: " + locatorKey
					+ ". Error: " + e.getMessage());
		} catch (ElementClickInterceptedException e) {
			logError("enterTextsWhenClickable Method Exception : Element was not clickable (intercepted): " + locatorKey
					+ ". Error: " + e.getMessage());
		} catch (StaleElementReferenceException e) {
			logError("enterTextsWhenClickable Method Exception : Stale element reference for locator key: " + locatorKey
					+ ". Error: " + e.getMessage());
		} catch (Exception e) {
			logError("enterTextsWhenClickable Method Exception : Unexpected error while sending keys to element: "
					+ locatorKey + ". Error: " + e.getMessage());
		}
	}

	// Method to verify that an element is displayed
	public boolean getIsFieldDisplayed(WebDriver driver, Map<String, String> locators, String locatorKey)
			throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			// Check if locators map or locatorKey is null
			if (locators == null || locatorKey == null) {
				logError("Locators map or locator key is null." + locatorKey);
				return false;
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			By locator = By.xpath(locators.get(locatorKey));
			// Check if the element is present in the DOM
			boolean isElementPresent = driver.findElements(locator).size() > 0;
			if (isElementPresent) {
				// Wait for the element to be visible
				WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				logInfo("Is displayed:" + locatorKey + element.isDisplayed());
				return element.isDisplayed();
			} else if (!isElementPresent) {
				logError("Element not found in DOM: " + locatorKey);
				return false;
			}
		} catch (TimeoutException e) {
			logError("getIsFieldDisplayed Method Exception : Timeout while waiting for visibility of element: "
					+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			logError("getIsFieldDisplayed Method Exception : No such element for locator key: " + locatorKey
					+ ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			logError("getIsFieldDisplayed Method Exception : Stale element reference for locator key: " + locatorKey
					+ ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logError("getIsFieldDisplayed Method Exception : Unexpected error while verifying element visibility: "
					+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	// Generic method to wait for an element to be visible and get its text
	public String getMessageFromToastWhenVisible() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locators.get("Toast"))));
			logInfo("Text displayed on the toast:" + locators.get("Toast") + element.getText());
			return element.getText();
		} catch (TimeoutException e) {
			logError(
					"getMessageFromToastWhenVisible Method Exception : Timeout while waiting for toast element to be visible: "
							+ locators.get("Toast") + ". Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (NoSuchElementException e) {
			logError("getMessageFromToastWhenVisible Method Exception : Toast element not found: "
					+ locators.get("Toast") + ". Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (StaleElementReferenceException e) {
			logError(
					"getMessageFromToastWhenVisible Method Exception : Stale element reference while trying to get text from toast: "
							+ locators.get("Toast") + ". Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			logError(
					"getMessageFromToastWhenVisible Method Exception : Unexpected error while getting text from the toast element: "
							+ locators.get("Toast") + ". Error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// Generic method to perform logout by clicking on user profile icon and sign
	// out link
	public void performLogout(String loginURL) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			WebElement userProfileIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("userProfileIcon"))));
			userProfileIcon.click();
			logInfo("User clicked the profile icon.");

			WebElement signOutLink = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("signOutLink"))));
			signOutLink.click();
			logInfo("User clicked the sign out link.");

			wait.until(ExpectedConditions.urlToBe(loginURL));
			logInfo("User has been navigated to login page successfully.");
		} catch (TimeoutException e) {
			logError("performLogout Method Exception : Timeout while performing logout operation. Error: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			logError("performLogout Method Exception : Element not found during logout operation. Error: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ElementClickInterceptedException e) {
			logError("performLogout Method Exception : Element was not clickable during logout operation. Error: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			logError(
					"performLogout Method Exception : Stale element reference encountered during logout operation. Error: "
							+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logError("performLogout Method Exception : Failed to perform logout with locator keys." + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to click element[With XPath] with Javascript when the
	// clickable element is not in view port.
	public void clickElementWithJS(WebDriver driver, Map<String, String> locators, String locatorKey) {
		try {
			WebElement element = driver.findElement(By.xpath(locators.get(locatorKey)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			logInfo("User clicked the expected element." + element);
		} catch (NoSuchElementException e) {
			logError("clickElementWithJS Method Exception : Element not found using locator key: " + locatorKey
					+ ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			logError(
					"clickElementWithJS Method Exception : Stale element reference while trying to click the element with locator key: "
							+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (WebDriverException e) {
			logError(
					"clickElementWithJS Method Exception : WebDriver error occurred while trying to click the element with locator key: "
							+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logError("clickElementWithJS Method Exception : Failed to click the element with JS using locator key: "
					+ locatorKey + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to click radio button[With ID] with Javascript when the
	// clickable element is not in view port.
	public void clickRadioButtonWithJS(WebDriver driver, Map<String, String> locators, String locatorKey) {
		try {
			WebElement element = driver.findElement(By.id(locators.get(locatorKey)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			logInfo("User clicked the expected element." + element);
		} catch (NoSuchElementException e) {
			logError("clickRadioButtonWithJS Method clickRadioButtonWithJS : Element not found using locator key: "
					+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (StaleElementReferenceException e) {
			logError(
					"clickRadioButtonWithJS Method clickRadioButtonWithJS : Stale element reference while trying to click the radio button with locator key: "
							+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (WebDriverException e) {
			logError(
					"clickRadioButtonWithJS Method clickRadioButtonWithJS : WebDriver error occurred while trying to click the radio button with locator key: "
							+ locatorKey + ". Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logError(
					"clickRadioButtonWithJS Method clickRadioButtonWithJS : Failed to click the radio button with JS using locator key: "
							+ locatorKey + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to search text in Quick Search box and verify with the
	// searched record.
	public void searchAndFindRecord(String searchText) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			// Search for the record
			WebElement quickSearchElement = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("quickSearchTextBox"))));
			quickSearchElement.clear();
			logInfo("User cleared all the prefilled text if available.");
			quickSearchElement.sendKeys(searchText);
			logInfo("User entered the expected text : " + searchText);
			quickSearchElement.sendKeys(Keys.ENTER);
			logInfo("User pressed the Enter key :");
			// Wait for loader to disappear
			logInfo("Waiting for the loader to be disappear.");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locators.get("loader"))));

			// Get the data from the grid view
			List<WebElement> gridViewData = driver.findElements(By.xpath(locators.get("gridData")));
			boolean recordFound = false;

			// Loop through the data to find the matching record
			for (WebElement rowData : gridViewData) {
				String rowDataValue = rowData.getText();

				// Check if the rowDataValue matches the expected value
				if (rowDataValue.contains(searchText)) {
					recordFound = true;
					logInfo("Searched record found in the grid :" + searchText);
					break;
				}
			}

			// If the record is not found, throw an error
			if (!recordFound) {
				logError("Record with text " + searchText + " not found.");
				throw new Exception("Record with text " + searchText + " not found.");
			}
		} catch (NoSuchElementException e) {
			logError("searchAndFindRecord Method Exception : No such element exception occurred: " + e.getMessage());
			throw e; // Rethrow if you want to handle it upstream
		} catch (TimeoutException e) {
			logError("searchAndFindRecord Method Exception : Timeout occurred while waiting for elements: "
					+ e.getMessage());
			throw e; // Rethrow if you want to handle it upstream
		} catch (StaleElementReferenceException e) {
			logError("searchAndFindRecord Method Exception : Stale element reference exception: " + e.getMessage());
			throw e; // Rethrow if you want to handle it upstream
		} catch (Exception e) {
			logError("searchAndFindRecord Method Exception : Error in searchAndFindRecord method: " + e.getMessage());
			throw e;
		}
	}

	// Generic method to check the field label name or value
	public String getFieldLabelNameOrValue(Map<String, String> locators, String elementKey) {
		try {
			if (locators == null || elementKey == null) {
				logError("Locators map or element key is null: " + elementKey);
				return null;
			}

//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.get(elementKey))));

			String labelText = element.getText();
			logInfo("Label name or value present for the expected element: " + labelText);
			return labelText;

		} catch (NoSuchElementException e) {
			logError("Element not found in DOM: " + elementKey);
			return null;
		} catch (TimeoutException e) {
			logError("Timeout while waiting for element: " + elementKey);
			return null;
		} catch (Exception e) {
			logError("Error in getFieldLabelNameOrValue method for element " + elementKey + ": " + e.getMessage());
			return null;
		}
	}

	// Generic method to verify the value of an editable field with the expected
	// one.
	public String getValueOfEditableField(WebDriver driver, Map<String, String> locators, String locatorKey) {
		try {
			// Validate input parameters
			if (driver == null || locators == null || locatorKey == null || locatorKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure driver, locators, and locatorKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			// Retrieve locator from the map
			String locator = locators.get(locatorKey);
			if (locator == null || locator.isBlank()) {
				String errormessage = "Locator not found in the map or is blank for key: " + locatorKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			// Wait for the element to be clickable
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			if (element == null) {
				String errormessage = "Element not found for locator: " + locator;
				logError(errormessage);
				throw new NoSuchElementException(errormessage);
			}

			// Get the value of the element
			String elementValue = element.getAttribute("value");
			if (elementValue == null) {
				logError("Value for the element is null: " + element + locator);
				elementValue = ""; // Treat null as an empty string for comparison
			}

			logInfo("Extracted value for the element: " + elementValue);
			return elementValue;

		} catch (IllegalArgumentException | NoSuchElementException e) {
			logError("getValueOfEditableField Method Exception : Error in getValueOfEditableField method: "
					+ e.getMessage());
			throw e; // Re-throw specific exceptions after logging
		} catch (TimeoutException e) {
			logError("getValueOfEditableField Method Exception : Timeout while waiting for element: " + e.getMessage());
			throw e; // Re-throw the timeout exception after logging
		} catch (Exception e) {
			logError("getValueOfEditableField Method Exception : Unexpected error in getValueOfEditableField method: "
					+ e.getMessage());
			throw e; // Re-throw other exceptions after logging
		}
	}

	// Generic method to verify if the toggle is set to the expected value
	public String getToggleValue(WebDriver driver, Map<String, String> locators, String locatorKey) {
		try {
			// Validate input parameters
			if (driver == null || locators == null || locatorKey == null || locatorKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure driver, locators, and locatorKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			// Retrieve locator from the map
			String locator = locators.get(locatorKey);
			if (locator == null || locator.isBlank()) {
				String errormessage = "Locator not found in the map or is blank for key: " + locatorKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			// Wait for the element to be clickable
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			if (element == null) {
				logError("Element not found for locator: " + locator);
				throw new NoSuchElementException("Element not found for locator: " + locator);
			}

			// Get the value of the element
			String elementValue = element.getAttribute("aria-checked");
			if (elementValue == null) {
				logError("Value for the element is null: " + element + locator);
				elementValue = ""; // Treat null as an empty string for comparison
			}
			logInfo("Extracted value for the element " + element + locator + ": " + elementValue);

			return elementValue;
		} catch (IllegalArgumentException | NoSuchElementException e) {
			logError("getToggleValue Method Exception : Error in getToggleValue method: " + e.getMessage());
			throw e; // Re-throw specific exceptions after logging
		} catch (TimeoutException e) {
			logError("getToggleValue Method Exception : Timeout while waiting for element: " + e.getMessage());
			throw e; // Re-throw the timeout exception after logging
		} catch (Exception e) {
			System.err.println("Error in verifyIsToggleSetToExpectedValue method: " + e.getMessage());
			logError("Error in verifyIsToggleSetToExpectedValue method: " + e.getMessage());
			throw e; // Re-throw the exception after logging
		}
	}

	// Generic method to check validation message on toast and field level and
	// verify it with expected value.
	public String getValidationMessageFromFieldOrToast(WebDriver driver, Map<String, String> locators,
			String elementKey) {
		try {
			// Validate input parameters
			if (driver == null || locators == null || elementKey == null || elementKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure driver, locators, and elementKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			// Retrieve locator from the map
			String locator = locators.get(elementKey);
			if (locator == null || locator.isBlank()) {
				String errormessage = "Locator not found in the map or is blank for key: " + elementKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			// Wait for the element to be visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			if (element == null) {
				logError("Element not found for locator: " + locator);
				throw new NoSuchElementException("Element not found for locator: " + locator);
			}

			// Get the validation message
			String validationMessage = element.getText();
			if (validationMessage == null) {
				logError("Validation message for the element is null: " + element + locator);
				validationMessage = ""; // Treat null as an empty string for comparison
			}

			logInfo(elementKey + ": " + validationMessage);
			return validationMessage;
		} catch (IllegalArgumentException e) {
			logError(
					"getValidationMessageFromFieldOrToast Method Exception : Invalid argument in getValidationMessageFromFieldOrToast method for element: "
							+ elementKey + " - " + e.getMessage());
			throw e; // Re-throw the specific exception after logging
		} catch (NoSuchElementException e) {
			logError(
					"getValidationMessageFromFieldOrToast Method Exception : No such element exception in getValidationMessageFromFieldOrToast method for element: "
							+ elementKey + " - " + e.getMessage());
			throw e; // Re-throw the specific exception after logging
		} catch (TimeoutException e) {
			logError(
					"getValidationMessageFromFieldOrToast Method Exception : Timeout while waiting for element visibility in getValidationMessageFromFieldOrToast method for element: "
							+ elementKey + " - " + e.getMessage());
			throw e; // Re-throw the specific exception after logging
		} catch (Exception e) {
			logError(
					"getValidationMessageFromFieldOrToast Method Exception : Error in getValidationMessageFromFieldOrToast method for element: "
							+ elementKey + " - " + e.getMessage());
			e.printStackTrace();
			throw e; // Re-throw the exception after logging
		}
	}

	// Generic method to check if a given text is available in the dropdown.
	public boolean getTextPresenceInDropdown(WebDriver driver, Map<String, String> locators, String elementKey,
			String text) {
		try {
			// Validate input parameters
			if (driver == null || locators == null || elementKey == null || text == null || elementKey.isBlank()
					|| text.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure driver, locators, elementKey, and text are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			// Retrieve the XPath for the dropdown and dropdown values
			String dropdownXPath = locators.get(elementKey);
			String dropdownValuesXPath = locators.get("dropdownValues");

			// Check if the XPath values are null or empty
			if (dropdownXPath == null || dropdownXPath.isEmpty()) {
				logError("XPath for the dropdown element is null or empty: " + elementKey);
				return false;
			}
			if (dropdownValuesXPath == null || dropdownValuesXPath.isEmpty()) {
				logError("XPath for the dropdown values is null or empty.");
				return false;
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			// Click to open the dropdown
			WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath)));
			dropdown.click();
			logInfo("User has clicked the dropdown icon: " + elementKey);

			// Check if the dropdown values are present in the DOM
			if (driver.findElements(By.xpath(dropdownValuesXPath)).isEmpty()) {
				logger.warn("Dropdown is blank for: " + dropdownXPath);
				return false;
			}

			// Capture all dropdown values
			List<WebElement> dropDownValues = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(dropdownValuesXPath)));

			// Iterate through the list and check if the text is present
			for (WebElement value : dropDownValues) {
				if (value.getText().trim().equals(text.trim())) {
					logInfo("Text '" + text + "' is present in the dropdown.");
					return true;
				}
			}

			logInfo("Text '" + text + "' is not present in the dropdown.");
			return false;
		} catch (IllegalArgumentException e) {
			logError(
					"getTextPresenceInDropdown Method Exception : Invalid argument in getTextPresenceInDropdown method: "
							+ e.getMessage());
			return false; // Handle invalid argument specifically
		} catch (NoSuchElementException e) {
			logError(
					"getTextPresenceInDropdown Method Exception : No such element exception in getTextPresenceInDropdown method: "
							+ e.getMessage());
			return false; // Handle no such element specifically
		} catch (TimeoutException e) {
			logError(
					"getTextPresenceInDropdown Method Exception : Timeout while waiting for elements in getTextPresenceInDropdown method: "
							+ e.getMessage());
			return false; // Handle timeout specifically
		} catch (Exception e) {
			logError("getTextPresenceInDropdown Method Exception : Error in getTextPresenceInDropdown method: "
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// Generic method to check that any dropdown list is blank or not.
	public boolean checkDropdownListIsBlank(Map<String, String> locators, String elementKey) {
		try {
			// Validate input parameters
			if (locators == null || elementKey == null || elementKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure locators and elementKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			String elementXPath = locators.get(elementKey);
			if (elementXPath == null || elementXPath.isBlank()) {
				String errormessage = "XPath for the dropdown element is null or blank: " + elementKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			// Click to open the dropdown
			WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
			dropdown.click();

			boolean isDropdownBlank = driver.findElements(By.xpath(locators.get("dropdownValues"))).size() == 0;

			if (isDropdownBlank) {
				logInfo("Dropdown is blank for the element: " + elementKey);
			} else {
				logInfo("Dropdown is not blank for the element: " + elementKey);
			}

			return isDropdownBlank;
		} catch (IllegalArgumentException e) {
			logError("Invalid argument in checkDropdownListIsBlank method: " + e.getMessage());
			return false; // Handle invalid arguments specifically
		} catch (NoSuchElementException e) {
			logError("No such element exception in checkDropdownListIsBlank method: " + e.getMessage());
			return false; // Handle element not found specifically
		} catch (TimeoutException e) {
			logError("Timeout while waiting for elements in checkDropdownListIsBlank method: " + e.getMessage());
			return false; // Handle timeout specifically
		} catch (Exception e) {
			logError("Unexpected error in checkDropdownListIsBlank method: " + e.getMessage());
			e.printStackTrace(); // Print stack trace for unexpected exceptions
			return false; // Handle general exceptions
		}
	}

	// Generic method to print all dropdown values.
	public void printDropdownValueText(Map<String, String> locators, String elementKey) {
		try {
			// Validate input parameters
			if (locators == null || elementKey == null || elementKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure locators and elementKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			String elementXPath = locators.get(elementKey);
			if (elementXPath == null || elementXPath.isBlank()) {
				String errormessage = "XPath for the dropdown element is null or blank: " + elementKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
			dropdown.click();
			logInfo("User has clicked the dropdown icon: " + elementKey);

			// Capture all dropdown values
			List<WebElement> dropDownValues = driver.findElements(By.xpath(locators.get("dropdownValues")));
			if (dropDownValues.isEmpty()) {
				logger.warn("No values found in the dropdown for element: " + elementKey);
			}

			// Iterate through the list and print the text of each value
			for (WebElement value : dropDownValues) {
				String valueText = value.getText();
				System.out.println(valueText);
				logInfo("Value present in the dropdown: " + valueText);
			}
		} catch (IllegalArgumentException e) {
			logError("Invalid argument in printDropdownValueText method: " + e.getMessage());
		} catch (NoSuchElementException e) {
			logError("No such element found in printDropdownValueText method: " + e.getMessage());
		} catch (TimeoutException e) {
			logError("Timeout while waiting for elements in printDropdownValueText method: " + e.getMessage());
		} catch (Exception e) {
			logError("Error in printDropdownValueText method: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to get/return the first value of a dropdown.
	public String getFirstValueOfADropdown(Map<String, String> locators, String elementKey) {
		try {
			// Validate input parameters
			if (locators == null || elementKey == null || elementKey.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure locators and elementKey are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			String elementXPath = locators.get(elementKey);
			if (elementXPath == null || elementXPath.isBlank()) {
				String errormessage = "XPath for the dropdown element is null or blank: " + elementKey;
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
			dropdown.click();
			logInfo("User has clicked the dropdown icon: " + elementKey);

			// Capture all dropdown values
			List<WebElement> dropDownValues = wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locators.get("dropdownValues"))));

			// Return the first index value if available
			if (!dropDownValues.isEmpty()) {
				String firstValue = dropDownValues.get(0).getText();
				logInfo("First value in the dropdown: " + firstValue);
				return firstValue;
			} else {
				logger.warn("Dropdown is empty: " + elementKey);
				return null;
			}
		} catch (IllegalArgumentException e) {
			logError("Invalid argument in getFirstValueOfADropdown method: " + e.getMessage());
			return null; // Handle invalid arguments specifically
		} catch (NoSuchElementException e) {
			logError("No such element exception in getFirstValueOfADropdown method: " + e.getMessage());
			return null; // Handle element not found specifically
		} catch (TimeoutException e) {
			logError("Timeout while waiting for elements in getFirstValueOfADropdown method: " + e.getMessage());
			return null; // Handle timeout specifically
		} catch (Exception e) {
			logError("Error in getFirstValueOfADropdown method: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	// Generic method to select value from a dropdown
	public void selectADropdownValue(Map<String, String> locators, String dropdownElementKey, String dropdownValue) {
		try {
			// Retrieve the locator for the dropdown element using the provided key
			String dropdownLocator = locators.get(dropdownElementKey);
			if (dropdownLocator == null) {
				throw new NoSuchElementException("Locator not found for key: " + dropdownElementKey);
			}

			// Wait for the dropdown element to be present and clickable
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownLocator)));

			// Create a Select object to interact with the dropdown
			Select dropdown = new Select(dropdownElement);

			// Select the option that matches the provided dropdown value
			dropdown.selectByVisibleText(dropdownValue);

			// Log a success message
			logInfo("Selected value '" + dropdownValue + "' from dropdown '" + dropdownElementKey + "'.");

		} catch (NoSuchElementException e) {
			// Handle the case where the locator is not found in the locators map
			logError("Dropdown element not found for key: " + dropdownElementKey + ". Error: " + e.getMessage());
			throw e;
		} catch (ElementNotInteractableException e) {
			// Handle the case where the dropdown is not interactable
			logError("Dropdown element '" + dropdownElementKey + "' is not interactable. Error: " + e.getMessage());
			throw e;
		} catch (TimeoutException e) {
			// Handle the case where the dropdown did not become clickable in time
			logError("Timed out waiting for dropdown '" + dropdownElementKey + "' to become clickable. Error: "
					+ e.getMessage());
			throw e;
		} catch (Exception e) {
			// Handle any other exceptions that may occur
			logError("Failed to select value '" + dropdownValue + "' from dropdown '" + dropdownElementKey
					+ "'. Error: " + e.getMessage());
			throw e;
		}
	}

	// Generic method to navigate and validate the URL.
	public void navigateToURL(String navigatedURL) {
		try {
			// Validate input parameters
			if (navigatedURL == null || navigatedURL.isBlank()) {
				String errormessage = "Invalid input parameters. Ensure navigatedURL and expectedURL are not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			// Navigate to the specified URL
			driver.navigate().to(navigatedURL);

		} catch (IllegalArgumentException e) {
			logError("Invalid argument in navigation method: " + e.getMessage());
		} catch (WebDriverException e) {
			logError("WebDriver exception during navigation: " + e.getMessage());
		} catch (Exception e) {
			logError("Unexpected error in navigation method: " + e.getMessage());
			e.printStackTrace(); // Print stack trace for unexpected exceptions
		}
		logInfo("Navigating to URL: " + navigatedURL);
	}

	// Generic method to verify paging functionality - Need to be rewritten with
	// extra null or blank check.
	public void verifyTotalRecordsOnTopOfGridOnListScreen() {
		try {
			WebElement pagerOnTop = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("pagerOnTop"))));
			// Check if the pager is displayed and log the result
			boolean isDisplayed = pagerOnTop.isDisplayed();
			logInfo("Pager on top is displayed: " + isDisplayed);
			if (isDisplayed) {
				String pagerText = pagerOnTop.getText();
				logInfo("Total records displayed in the pager: " + pagerText);
				System.out.println(pagerText); // Keep this if you need it for debugging; otherwise, consider removing
			} else {
				logger.warn("Pager on top is not displayed.");
			}
		} catch (NoSuchElementException e) {
			logError("Pager element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			logError("Timeout while waiting for pager element: " + e.getMessage());
		} catch (Exception e) {
			logError("Unexpected error in verifyTotalRecordsOnTopOfGridOnListScreen: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to verify paging functionality - Need to be rewritten with
	// extra null or blank check.
	public void verifyPaginationFunctionalityOnListScreen() {
		try {
			WebElement pagerOnBottom = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("pagerOnBottom"))));
			// Check if the pager is displayed
			boolean isDisplayed = pagerOnBottom.isDisplayed();
			logInfo("Pager on bottom is displayed: " + isDisplayed);
			if (isDisplayed) {
				String pagerText = pagerOnBottom.getText();
				logInfo("Pagination text displayed: " + pagerText);
			} else {
				logger.warn("Pager on bottom is not displayed.");
			}
		} catch (NoSuchElementException e) {
			logError("Pager element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			logError("Timeout while waiting for pager element: " + e.getMessage());
		} catch (Exception e) {
			logError("Unexpected error in verifyPaginationFunctionalityOnListScreen: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to verify successful navigation to list screen by pressing
	// browser back button.
	public boolean verifyBrowserBackButtonNavigation(String expectedURL) {
		try {
			// Validate input parameters
			if (expectedURL == null || expectedURL.isBlank()) {
				String errormessage = "Invalid input parameter. Ensure expectedURL is not null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			// Navigate back in the browser
			driver.navigate().back();
			logInfo("User has clicked the browser back button.");
			// Wait until the current URL matches the expected URL
			boolean isUrlMatched = wait.until(ExpectedConditions.urlToBe(expectedURL));
			// Log the result
			if (isUrlMatched) {
				logInfo("Successfully navigated to the expected URL: " + expectedURL);
			} else {
				String navigatedURL = driver.getCurrentUrl();
				logger.warn("Navigated to URL: " + navigatedURL + ", but it does not match the expected URL: "
						+ expectedURL);
			}
			return isUrlMatched;
		} catch (IllegalArgumentException e) {
			logError("Invalid argument in verifyBrowserBackButtonNavigation method: " + e.getMessage());
			return false; // Return false for invalid arguments
		} catch (TimeoutException e) {
			logError("Timeout while waiting for the URL to match in verifyBrowserBackButtonNavigation method: "
					+ e.getMessage());
			return false; // Return false for timeouts
		} catch (WebDriverException e) {
			logError("WebDriver exception occurred in verifyBrowserBackButtonNavigation method: " + e.getMessage());
			return false; // Return false for WebDriver exceptions
		} catch (Exception e) {
			logError("Error in verifyBrowserBackButtonNavigation method: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// Generic method to search and find a record, then activate or deactivate it
	// based on the action parameter.
	public void searchAndTakeActionOnRecord(String searchText, String action) throws Exception {
		try {
			// Validate input parameters
			if (searchText == null || searchText.isBlank()) {
				String errormessage = "Invalid input: searchText is null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			if (action == null || action.isBlank()) {
				String errormessage = "Invalid input: action is null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			// Search for the record
			searchAndFindRecord(searchText);

			// After finding the record, perform the activate or deactivate action
			List<WebElement> gridViewData = driver.findElements(By.xpath(locators.get("gridData")));
			boolean recordFound = false;

			for (WebElement rowData : gridViewData) {
				String rowDataValue = rowData.getText();

				// Check if the rowDataValue matches the expected value
				if (rowDataValue.contains(searchText)) {
					logInfo("Searched text found in the grid.");
					// Click on the record to open action options
					rowData.click();
					recordFound = true;

					// Perform the specified action (activate, deactivate, edit, or view)
					WebElement actionIcon;
					switch (action.toLowerCase()) {
					case "activate":
						actionIcon = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("activateIcon"))));
						actionIcon.click();
						logInfo("User has clicked the activate icon.");
						break;
					case "deactivate":
						actionIcon = wait.until(
								ExpectedConditions.elementToBeClickable(By.xpath(locators.get("deactivateIcon"))));
						actionIcon.click();
						logInfo("User has clicked the deactivate icon.");
						break;
					case "edit":
						actionIcon = wait.until(ExpectedConditions
								.elementToBeClickable(By.xpath(locators.get("editIconOnListScreen"))));
						actionIcon.click();
						logInfo("User has clicked the edit icon.");
						break;
					case "view":
						actionIcon = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("viewIcon"))));
						actionIcon.click();
						logInfo("User has clicked the view icon.");
						break;
					case "review":
						actionIcon = wait
								.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("reviewIcon"))));
						actionIcon.click();
						logInfo("User has clicked the review icon.");
						break;
					default:
						String errormessage = "Invalid action specified: " + action;
						logError(errormessage);
						throw new IllegalArgumentException(errormessage);
					}
					break;
				}
			}

			if (!recordFound) {
				String errormessage = "Record with the search text '" + searchText + "' not found in the grid.";
				logger.warn(errormessage);
				throw new Exception(errormessage);
			}
		} catch (IllegalArgumentException e) {
			logError("Invalid argument in searchAndTakeActionOnRecord method: " + e.getMessage());
			throw e; // Rethrow to indicate failure
		} catch (NoSuchElementException e) {
			logError("No such element found in searchAndTakeActionOnRecord method: " + e.getMessage());
			throw e; // Rethrow to indicate failure
		} catch (TimeoutException e) {
			logError("Timeout waiting for an element in searchAndTakeActionOnRecord method: " + e.getMessage());
			throw e; // Rethrow to indicate failure
		} catch (Exception e) {
			logError("Error in searchAndTakeActionOnRecord method: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// Generic method to search text in Quick Search box and verify with the
	// searched record.
	public boolean searchAndCheckIsRecordAvailableInGrid(String searchText) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

			// Search for the record
			WebElement quickSearchElement = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.get("quickSearchTextBox"))));
			quickSearchElement.clear();
			quickSearchElement.sendKeys(searchText);
			quickSearchElement.sendKeys(Keys.ENTER);

			// Wait for loader to disappear
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locators.get("loader"))));

			// Get the data from the grid view
			List<WebElement> gridViewData = driver.findElements(By.xpath(locators.get("gridData")));

			// Loop through the data to find the matching record
			for (WebElement rowData : gridViewData) {
				String rowDataValue = rowData.getText();

				// Check if the rowDataValue matches the expected value
				if (rowDataValue.contains(searchText)) {
					logInfo("Record found in grid."); // Print if record found in grid.
					return true; // Record found
				}
			}
			// If the record is not found, return false
			logInfo("Record not found in grid.");
			return false;
		} catch (NoSuchElementException e) {
			logError("No such element found in searchAndCheckIsRecordAvailableInGrid method: " + e.getMessage());
			return false; // Return false if element is not found
		} catch (TimeoutException e) {
			logError("Timeout waiting for elements in searchAndCheckIsRecordAvailableInGrid method: " + e.getMessage());
			return false; // Return false if a timeout occurs
		} catch (WebDriverException e) {
			logError("WebDriver exception occurred in searchAndCheckIsRecordAvailableInGrid method: " + e.getMessage());
			return false; // Return false for WebDriver related issues
		} catch (Exception e) {
			logError("Error in searchAndCheckIsRecordAvailableInGrid method: " + e.getMessage());
			return false; // Return false if there's an exception
		}
	}

	// Generic method to ensure whether any field is editable or read-only.
	public boolean isFieldEditable(Map<String, String> locators, String fieldLocator) {
		try {
			// Validate input parameters
			if (locators == null || locators.isEmpty()) {
				String errormessage = "Invalid input: locators map is null or empty.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			if (fieldLocator == null || fieldLocator.isBlank()) {
				String errormessage = "Invalid input: fieldLocator is null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}

			// XPath to check for the ancestor element
			String editableXPath = locators.get(fieldLocator) + "/ancestor::kendo-formfield";

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			// Check if the editable ancestor element is present
			boolean isEditable = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(editableXPath)))
					.size() > 0;
			logInfo("Is field editable for the element: " + editableXPath + " Result: " + isEditable);
			return isEditable;
		} catch (NoSuchElementException e) {
			logError("No such element found in isFieldEditable method: " + e.getMessage());
			return false; // Return false if element is not found
		} catch (TimeoutException e) {
			logError("Timeout waiting for element in isFieldEditable method: " + e.getMessage());
			return false; // Return false if a timeout occurs
		} catch (WebDriverException e) {
			logError("WebDriver exception occurred in isFieldEditable method: " + e.getMessage());
			return false; // Return false for WebDriver related issues
		} catch (Exception e) {
			logError("Error in isFieldEditable method: " + e.getMessage());
			return false;
		}
	}

	// Generic method to check button enablement.
	public boolean isButtonEnabled(Map<String, String> locators, String elementKey) {
		try {
			// Check if locators map or elementKey is null
			if (locators == null || elementKey == null) {
				logError("Locators map or element key is null");
				return false;
			}
			// Get the locator for the element key
			String locator = locators.get(elementKey);
			// Check if the locator is null or empty
			if (locator == null || locator.isEmpty()) {
				logError("Locator for element key is null or empty");
				return false;
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			boolean buttonState = element.isEnabled();
			logInfo("Is button enable : " + elementKey + " " + buttonState);
			// Add an assertion to check if the button is enabled
			Assert.assertTrue(buttonState, "The button with key " + elementKey + " is not enabled");
			return buttonState;
		} catch (NoSuchElementException e) {
			logError("No such element found for key: " + elementKey + " - " + e.getMessage());
			return false; // Return false if the element is not found
		} catch (TimeoutException e) {
			logError("Timeout while waiting for element: " + elementKey + " - " + e.getMessage());
			return false; // Return false if a timeout occurs
		} catch (WebDriverException e) {
			logError("WebDriver exception occurred: " + e.getMessage());
			return false; // Return false for WebDriver related issues
		} catch (Exception e) {
			logError("Unexpected error in isButtonEnabled method: " + e.getMessage());
			e.printStackTrace(); // Print stack trace for unexpected exceptions
			return false; // Return false for any other unexpected exceptions
		}
	}

	// Generic method to check button disablement
	public boolean isButtonDisabled(Map<String, String> locators, String elementKey) {
		try {
			// Check if locators map or elementKey is null
			if (locators == null || elementKey == null) {
				logError("Locators map or element key is null");
				return false;
			}
			// Get the locator for the element key
			String locator = locators.get(elementKey);
			// Check if the locator is null or empty
			if (locator == null || locator.isEmpty()) {
				logError("Locator for element key is null or empty");
				return false;
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			boolean buttonState = element.isEnabled();
			logInfo("Is button enable for the element:" + elementKey + buttonState);
			// Add an assertion to check if the button is enabled
			Assert.assertFalse(buttonState, "The button with key " + elementKey + " is not enabled");
			logInfo("Button is disabled." + elementKey);
			return buttonState;
		} catch (NoSuchElementException e) {
			logError("No such element found for key: " + elementKey + " - " + e.getMessage());
			return false; // Return false if the element is not found
		} catch (TimeoutException e) {
			logError("Timeout while waiting for element: " + elementKey + " - " + e.getMessage());
			return false; // Return false if a timeout occurs
		} catch (WebDriverException e) {
			logError("WebDriver exception occurred: " + e.getMessage());
			return false; // Return false for WebDriver related issues
		} catch (Exception e) {
			logError("Unexpected error in isButtonEnabled method: " + e.getMessage());
			e.printStackTrace(); // Print stack trace for unexpected exceptions
			return false; // Return false for any other unexpected exceptions
		}
	}

	// Generic method to get the count of data available in the grid and verify it
	// with the page grid on top element.
	public void verifyGridDataCountWithPagerTopElement() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			// Get the data count from the grid
			List<WebElement> gridDataElements = wait
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locators.get("gridData"))));
			int gridDataCount = gridDataElements.size();
			logInfo("Grid data count: " + gridDataCount);
			// Get the count from the pager element on top
			WebElement pagerElement = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.get("pagerOnTop"))));
			String pagerText = pagerElement.getText();
			logInfo(pagerText);
			logInfo("Pager text: " + pagerText);
			// Extract the number from the pager text, assuming it follows a specific format
			// like "1-10 of 50"
			int pagerCount = extractCountFromPagerText(pagerText);
			int recordRange = extractRecordRangeFromPagerTextOnASpecificPage(pagerText);
			logInfo("Pager count: " + pagerCount);
			logInfo("Record range from pager: " + recordRange);
			// Verify that the counts match
			// Assert.assertEquals(gridDataCount, pagerCount, "The count of grid data does
			// not match the count from the pager on top.");
			logInfo("Grid data count: " + gridDataCount + ", Pager count: " + pagerCount);
		} catch (NoSuchElementException e) {
			logError("Element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			logError("Timeout while waiting for an element: " + e.getMessage());
		} catch (Exception e) {
			logError("Error in verifyGridDataCountWithPagerTopElement method: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Helper method to extract count from pager text
	private int extractCountFromPagerText(String pagerText) {
		// Assuming the pager text format is "1-10 of 50", extract the last number
		if (pagerText == null || pagerText.isBlank()) {
			logError("Pager text is null or blank.");
			return 0; // Or throw an exception, depending on your needs
		}
		// Split the text and check for expected format
		String[] parts = pagerText.split(" ");
		logInfo("PagerText Length :" + parts.length);
		try {
			return Integer.parseInt(parts[parts.length - 2]); // Extract the second last part, which should be the count
		} catch (NumberFormatException e) {
			logError("Error parsing count from pager text: " + pagerText + " - " + e.getMessage());
			e.printStackTrace();
			return 0; // Or throw an exception
		}
	}

	// Helper method to extract count from pager text
	private int extractRecordRangeFromPagerTextOnASpecificPage(String pagerText) {
		if (pagerText == null || pagerText.isBlank()) {
			logError("Pager text is null or blank.");
			return 0; // Or throw an exception, depending on your needs
		}
		String[] parts = pagerText.split("1 - 50");
		logInfo("PagerText Length :" + parts.length);
		try {
			// Return the total records in the range
			return Integer.parseInt(parts[parts.length - 2]); // Assuming you want the upper limit of the range
		} catch (NumberFormatException e) {
			logError("Error parsing record range from pager text: " + pagerText + " - " + e.getMessage());
			e.printStackTrace();
			return 0; // Or throw an exception

		}
	}

	// Generic method to find element by class name and wait for its invisibility if
	// present.
	public void waitForLoaderInvisibility(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			By locator = By.className(locators.get("loader"));

			// Check if the element is present
			if (!driver.findElements(locator).isEmpty()) {
				// If the element is visible, wait for it to become invisible
				if (driver.findElement(locator).isDisplayed()) {
					wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
				}
			} else {
				// Element is not present
				logError("Loader element is not present.");
			}
		} catch (NoSuchElementException e) {
			// Element is not found, no action needed
			logError("Loader element not found: " + e.getMessage());
		} catch (TimeoutException e) {
			// Timeout while waiting for invisibility
			logError("Timed out waiting for loader to become invisible: " + e.getMessage());
		} catch (Exception e) {
			// Other exceptions
			e.printStackTrace();
			logError("Failed to wait for invisibility of element with class name 'loader': " + e.getMessage());
		}
	}

	// Generic method to find elements by locator key and log their count
	public void countAndPrintStatusesPresentInProgressBar(Map<String, String> locators, String locatorKey) {
		if (locators == null || locatorKey == null || !locators.containsKey(locatorKey)) {
			logError("Invalid input: locators map is null or locator key not found.");
			return;
		}
		try {
			List<WebElement> elements = driver.findElements(By.xpath(locators.get(locatorKey)));
			int statusCount = elements.size();
			logInfo("Number of statuses present in progress bar: " + statusCount);
		} catch (Exception e) {
			logError("Failed to find elements with locator key: " + locatorKey + " - " + e.getMessage());
		}
	}

	// Generic function to extract ID from LI Request toast messages.
	public String extractIDFromToast(String toastMessage) {
		try {
			if (toastMessage == null || toastMessage.isBlank()) {
				logError("Toast message is null or empty.");
				return "Toast message is null or empty.";
			}
			// Verify if the toast message contains the expected success message
			if (!toastMessage.contains("successfully")) {
				// If the success message is not found, return an error message
				logError("Success message on toast is not as expected: " + toastMessage);
				return "Success message on toast is not as expected: " + toastMessage;
			}

			// Split the toast message by spaces
			String[] parts = toastMessage.split(" ");

			// Iterate through the parts to find the ID
			for (String part : parts) {
				// Check if the part starts with "LIRQ" and contains only digits afterward
				if (part.startsWith("LIRQ") && part.substring(4).chars().allMatch(Character::isDigit)) {
					// Return the part as the ID
					return part;
				}
			}

			// If no ID is found, return a message
			logError("No ID found in toast message.");
			return "No ID found in toast message";
		} catch (Exception e) {
			// Catch any exception and return a message
			logError("Error extracting ID from toast message:" + e.getMessage());
			return "Error extracting ID from toast message: " + e.getMessage();
		}
	}

	// Generic method to check the presence of an asterisk mark
	public boolean getAsteriskPresence(Map<String, String> locators, String elementKey) {
		try {
			if (locators == null || elementKey == null) {
				logError("Locators map or element key is null: " + elementKey);
				return false;
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.get(elementKey))));

			boolean isDisplayed = element.isDisplayed();
			logInfo("Is Asterisk present for the element " + elementKey + ": " + isDisplayed);
			return isDisplayed;

		} catch (NoSuchElementException e) {
			logError("Element not found in DOM: " + elementKey);
			return false;
		} catch (TimeoutException e) {
			logError("Timeout while waiting for element: " + elementKey);
			return false;
		} catch (Exception e) {
			logError("Error in verifyAsteriskPresence method for element " + elementKey + ": " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// Generic method to check the status value
	public String verifyStatusOnStatusBar(Map<String, String> locators, String elementKey, String expectedText) {
		String actualStatus = null;
		try {
			// Validate input parameters
			if (locators == null || locators.isEmpty()) {
				String errormessage = "Invalid input: locators map is null or empty.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			if (elementKey == null || elementKey.isBlank()) {
				String errormessage = "Invalid input: elementKey is null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			if (expectedText == null || expectedText.isBlank()) {
				String errormessage = "Invalid input: expectedText is null or blank.";
				logError(errormessage);
				throw new IllegalArgumentException(errormessage);
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			WebElement element = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locators.get(elementKey))));
			actualStatus = element.getText();
			logInfo("Status present on status bar: " + actualStatus + " for the element: " + elementKey);
		} catch (TimeoutException e) {
			logError("Timeout waiting for element: " + elementKey + ". Error: " + e.getMessage());
		} catch (NoSuchElementException e) {
			logError("Element not found: " + elementKey + ". Error: " + e.getMessage());
		} catch (Exception e) {
			logError("Error in verifyStatusOnStatusBar method for element: " + elementKey + ". Error: "
					+ e.getMessage());
			e.printStackTrace();
		}
		return actualStatus;
	}

	// Generic method to check breadcrumb path
	public boolean isBreadcrumbPathRight(Map<String, String> locators, String Part1, String Part2, String Part3,
			String Part4, String Part5, String Part6, String Part7) {
		try {
			if (locators == null || locators.isEmpty()) {
				logError("Locators map is null or empty.");
				return false;
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			// Store all parts in an array for easier iteration
			String[] parts = { Part1, Part2, Part3, Part4, Part5, Part6, Part7 };

			// Iterate through each part and check its presence
			for (String part : parts) {
				if (part != null && !part.isEmpty()) {
					String locator = locators.get(part);
					if (locator != null && !locator.isEmpty()) {
						// Construct the XPath for the current part
						String xpath = locator;
						// System.out.println("Checking XPath: " + xpath);

						// Check for the presence of the current part
						boolean isElementPresent = wait
								.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))).size() > 0;

						if (!isElementPresent) {
							logError("Breadcrumb part not found :" + part);
							return false;
						}
					} else {
						logError("Locator not found for part: " + part);
						return false;
					}
				} else {
					logError("Part is null or empty: " + part);
					return false;
				}
			}
			// Return true if all parts are present
			logInfo("Breadcrumb matched.");
			return true;
		} catch (Exception e) {
			System.err.println("Error in isBreadcrumbPathRight method: " + e.getMessage());
			logError("Error in isBreadcrumbPathRight method: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Checks if the specified element is expanded.
	 * 
	 * @param locators   the locators to find the element
	 * @param elementKey the key for the specific element to check
	 * @return true if the element is expanded, false otherwise
	 */
	public boolean isElementExpanded(Map<String, String> locators, String elementKey) {
		try {
			WebElement element = driver.findElement(By.xpath(locators.get(elementKey)));
			String ariaExpanded = element.getAttribute("aria-expanded");
			return "true".equals(ariaExpanded);
		} catch (NoSuchElementException e) {
			logError("Error: The element with key '" + elementKey + "' was not found - " + e.getMessage());
			return false; // Assume not expanded if the element is not found
		} catch (Exception e) {
			logError("An unexpected error occurred while checking if the element is expanded - " + e.getMessage());
			return false; // Handle other exceptions and assume not expanded
		}
	}

	public boolean isRadioButtonSelected(Map<String, String> locators, String radioButtonKey) throws Exception {
		try {
			WebElement radioButton = driver.findElement(By.xpath(locators.get(radioButtonKey)));
			return radioButton.isSelected();
		} catch (NoSuchElementException e) {
			logError("Radio button element not found for key: " + radioButtonKey + " - " + e.getMessage());
			throw new NoSuchElementException("Radio button element not found for key: " + radioButtonKey, e);
		} catch (Exception e) {
			logError("Error while checking if radio button is selected for key: " + radioButtonKey + " - "
					+ e.getMessage());
			throw e;
		}
	}

}
