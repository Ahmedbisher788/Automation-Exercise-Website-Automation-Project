import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginSignupPage;
import utilities.DataUtils;
import utilities.LogUtility;

import java.io.FileNotFoundException;


@Epic(value = "regression Test")
@Feature("User")
public class TC02_ValidLogin extends TestBasic {
    @Story("login user with correct email and password")
    @Test(description = "test case 2 : valid login")
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
            """)
    public void loginTest() throws FileNotFoundException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        verifyHeaderToLoginIsVisible();
        loginWithValidEmailAndPassword();
    }

    @Step("Verify 'Login to your account' is visible")
    public static void verifyHeaderToLoginIsVisible() {
        String loginHeaderText = new P01_LoginSignupPage(getDriver())
                .getLoginToUrAccount(getDriver()).getText();
        Assert.assertEquals(loginHeaderText, "Login to your account", "Verify 'Login to your account' is visible");

    }

    @Step("Enter Valid email address and password")
    public static void loginWithValidEmailAndPassword() throws FileNotFoundException {
        String loginUsername = new P01_LoginSignupPage(getDriver()).fillCorrectAccount(DataUtils.getJsonData("existingUser", "email"),
                        DataUtils.getJsonData("existingUser", "password"))
                .getUserName()
                .getText();
        Assert.assertEquals(loginUsername, DataUtils.getJsonData("existingUser", "name"), "Verify that 'Logged in as username' is visible");
        LogUtility.info("Logged to your account successfully");
    }
}