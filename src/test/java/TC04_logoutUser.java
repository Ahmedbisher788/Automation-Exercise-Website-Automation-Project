import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P03_LoggedHomePage;
import utilities.LogUtility;

import java.io.FileNotFoundException;

@Epic(value = "regression Test")
@Feature("User")
public class TC04_logoutUser extends TestBasic {

    @Story("LOGOUT USER")
    @Test(description = "test case 4 :  logout user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter correct email address and password
            7. Click 'login' button
            8. Verify that 'Logged in as username' is visible
            9. Click 'Logout' button
            10. Verify that user is navigated to login page""")
    public void loginTest() throws FileNotFoundException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        TC02_ValidLogin.verifyHeaderToLoginIsVisible();
        TC02_ValidLogin.loginWithValidEmailAndPassword();
    }

    public void VerifyThatUserIsNavigatedToLoginPage() {
        new P03_LoggedHomePage(getDriver()).clickOnLogout();
        LogUtility.info("verify user logout successfully and navigated to login page");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationexercise.com/login", "Verify that user is navigated to login page");
    }
}

