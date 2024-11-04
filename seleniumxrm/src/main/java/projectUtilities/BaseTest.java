package projectUtilities;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Map<String, String> locators = new HashMap<>();
    protected Map<String, String> testData = new HashMap<>();
    protected Map<String, String> expectedValues = new HashMap<>();
    
    public static ExtentReports extent;
    public static ExtentTest test;
    
	//object of Logger class
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);
	   
	static {
	        Configurator.initialize(null, "src/resources/log4j2.properties");
	    }
	
	@BeforeClass
    @Parameters("browser")
	public void setUp(String browser) {
	    if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }
	    
		wait = new WebDriverWait(driver,Duration.ofSeconds(30)); // Adjust the timeout as needed

        initializeLocators();
        initializeTestData();
        initializeExpectedValues();	
	}
	

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    

    // A method to access the WebDriver
    public WebDriver getDriver() {
        return driver;
    }
    
    protected void initializeLocators() {
    	
    };
    
    protected  void initializeTestData() {
    	
    }; 
    
    protected void initializeExpectedValues() {
    	
    };
    
    @BeforeSuite
    public void setupExtent() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Smoke Testing");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Your Host");
        extent.setSystemInfo("Environment", "Automation Testing");
        extent.setSystemInfo("User Name", "Your Name");
    }
    
    @AfterSuite
    public void teardownExtent() {
        extent.flush();
    }
    
    public void logInfo(String message) {
        test.info(message);
        logger.info(message);
    }

    public void logError(String message) {
        test.fail(message);
        logger.error(message);
    }

    public void logPass(String message) {
        test.pass(message);
        logger.info(message);
    }
    				
    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        } catch (Exception e) {
        	logger.error("Exception while taking screenshot: " + e.getMessage());
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }


}
