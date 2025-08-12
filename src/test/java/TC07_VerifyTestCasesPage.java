import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Verify")
public class TC07_VerifyTestCasesPage extends TestBasic {


    @Test(description = "Test Case 7: Verify Test Cases Page")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Verify Test Cases Page")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Test Cases' button
            5. Verify user is navigated to test cases page successfully""")

    public void verifyTestCasesPage() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyUserIsNavigatedToTestCasesPageSuccessfully();
    }

    @Step("Verify user is navigated to test cases page successfully")
    private void verifyUserIsNavigatedToTestCasesPageSuccessfully() {
        String testCasesText = new P00_HomePage(getDriver())
                .navigateToTestCasePage()
                .getTestCases()
                .getText();
        LogUtility.info("Verifying navigated to test cases page successfully");

        Assert.assertEquals(testCasesText, "TEST CASES", "Verify user is navigated to test cases page successfully");
    }
}
