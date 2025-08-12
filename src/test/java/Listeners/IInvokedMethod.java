package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import utilities.LogUtility;
import utilities.Utility;

import java.io.IOException;


public class IInvokedMethod implements IInvokedMethodListener {
    private WebDriver driver;


    public void beforeInvocation(org.testng.IInvokedMethod method, ITestResult testResult) {
        System.out.println(method.getTestMethod().getMethodName());
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogUtility.info("Test Case " + testResult.getName() + " Failed");

            Utility.takeScreenShot(driver, testResult.getName());
        }
    }
}