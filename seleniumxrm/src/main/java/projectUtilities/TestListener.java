package projectUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
    	WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String screenshotPath = ((BaseTest) result.getInstance()).captureScreenshot(driver, result.getName());
        
        try {
            // Logging the failure status and screenshot to the Extent report
            BaseTest.test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            BaseTest.test.log(Status.FAIL, "Screenshot below: " + BaseTest.test.addScreenCaptureFromPath(screenshotPath));
        } catch (Exception e) {
            BaseTest.test.log(Status.FAIL, "Failed to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.test.log(Status.PASS, "Test Case Passed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        BaseTest.test = BaseTest.extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Logging the skipped status to the Extent report
        BaseTest.test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Finalizing the Extent report
        BaseTest.extent.flush();
    }
    
}	
