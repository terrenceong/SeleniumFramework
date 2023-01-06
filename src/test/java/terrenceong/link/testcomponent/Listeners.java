package terrenceong.link.testcomponent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import terrenceong.link.reporters.ExtentReporter;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    private ExtentTest test;
    private ExtentReports extent =  ExtentReporter.getReportObject();
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); //thread. each object will have its own thread
    @Override
    public void onTestStart(ITestResult iTestResult) {
      test =  extent.createTest(iTestResult.getMethod().getMethodName());
      extentTest.set(test); // assign one unique e.g thread id(errorvalidationtest) -> test
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        this.test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());
        String filePath = null;
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver")
                    .get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //screenshot
        try {
             filePath = getScreenShot(iTestResult.getMethod().getMethodName(),driver);
             extentTest.get().addScreenCaptureFromPath(filePath,iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        this.extent.flush();
    }
}
