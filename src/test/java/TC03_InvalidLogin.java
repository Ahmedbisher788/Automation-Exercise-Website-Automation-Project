import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginSignupPage;
import utilities.LogUtility;
import utilities.Utility;

import java.io.FileNotFoundException;


@Epic(value = "regression Test")
@Feature("User")

public class TC03_InvalidLogin extends TestBasic {

    @Story("login user with incorrect email and password")
    @Test(description = "test case 3 : Invalid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'Login to your account' is visible
            6. Enter incorrect email address and password
            7. Click 'login' button
            8. Verify error 'Your email or password is incorrect!' is visible
            """)
    public void loginTest() throws FileNotFoundException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        TC02_ValidLogin.verifyHeaderToLoginIsVisible();
        VerifyLoginErrorMessageIsVisible();
    }

    @Step("Verify error 'Your email or password is incorrect!' is visible")
    public void VerifyLoginErrorMessageIsVisible() {
        new P01_LoginSignupPage(getDriver()).fillIncorrectAccount
                (Utility.getTimeStamp() + "@gmail.com"
                        , Utility.getTimeStamp());
        LogUtility.info("verify login with unregistered email");
        String errorLoginMessage = new P01_LoginSignupPage(getDriver()).getLoginError().getText();
        Assert.assertEquals(errorLoginMessage, "Your email or password is incorrect!", "Verify error 'Your email or password is incorrect!' is visible");
    }

}
