import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginSignupPage;
import utilities.DataUtils;
import utilities.LogUtility;

import java.io.FileNotFoundException;

@Epic(value = "regression Test")
@Feature("User")
public class TC05_RegisterUserWithExistingEmail extends TestBasic {

    @Story("Register user with existing email")
    @Test(description = "test case 5 :  Register user with existing email")
    @Severity(SeverityLevel.CRITICAL)
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'New User Signup!' is visible
            6. Enter name and already registered email address
            7. Click 'Signup' button
            8. Verify error 'Email Address already exist!' is visible""")
    public void loginTest() throws FileNotFoundException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        TC01_RegisterUser.verifyHeaderToSignupIsVisible();
        VerifyRegisterErrorMessageIsVisible();
    }

    @Step("Verify error 'Email Address already exist!' is visible")
    public void VerifyRegisterErrorMessageIsVisible() throws FileNotFoundException {
        String signupError = new P01_LoginSignupPage(getDriver())
                .fillInCorrectSignup(DataUtils.getJsonData("existingUser", "name"),
                        DataUtils.getJsonData("existingUser", "email")).getSignupError().getText();
        LogUtility.info("Verifying register error message. Actual: '" + signupError + "', Expected: 'Email Address already exist!'");

        Assert.assertEquals(signupError, "Email Address already exist!", "Verify error 'Email Address already exist!' is visible");
        LogUtility.info("Assertion passed: Correct error message is displayed.");
    }

}
