import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.LogUtility;
import utilities.Utility;

import java.io.FileNotFoundException;


@Epic("regression tests")
@Feature("User")
public class TC01_RegisterUser extends TestBasic {
    String name = Utility.getTimeStamp();
    String email = Utility.getTimeStamp() + "@gmail.com";


    @Test(description = "test case 1 : Signup user ")
    @Severity(SeverityLevel.CRITICAL)
    @Story(value = "Register user")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Signup / Login' button
            5. Verify 'New User Signup!' is visible
            6. Enter name and email address
            7. Click 'Signup' button
            8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
            9. Fill details: Title, Name, Email, Password, Date of birth
            10. Select checkbox 'Sign up for our newsletter!'
            11. Select checkbox 'Receive special offers from our partners!'
            12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
            13. Click 'Create Account button'
            14. Verify that 'ACCOUNT CREATED!' is visible
            15. Click 'Continue' button
            16. Verify that 'Logged in as username' is visible
            17. Click 'Delete Account' button
            18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button"""

    )
    public void registerUser() throws FileNotFoundException {
        // TODO: 7/22/2025 all cases
        verifyHomePageVisibleSuccessfully();
        navigateToSignupLoginPage();
        verifyHeaderToSignupIsVisible();
        enterValidNameAndEmailForSignup();
        enterAccountInfoIsVisible();
        validRegisterAndCheckMessageAccountCreatedIsVisible();
        VerifyThatLoggedInAsUserNameIsVisible();
        VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton();
    }

    //@Test
    @Step("Verify that home page is visible successfully")
    public static void verifyHomePageVisibleSuccessfully() {
        boolean homePageIsVisible = new P00_HomePage(getDriver()).homePageIsVisible(getDriver())
                .isDisplayed();
        Assert.assertTrue(homePageIsVisible, "Verify that home page is visible successfully");
        LogUtility.info(" home page is visible successfully");
    }

    //@Test//(dependsOnMethods = "verifyHomePageVisibleSuccessfully")
    @Step("Click on 'Signup / Login' button")
    public static void navigateToSignupLoginPage() {
        boolean signupPageVisible = new P00_HomePage(getDriver()).navigateToSignupLoginPage().getNewUserSignup().isDisplayed();
        Assert.assertTrue(signupPageVisible, "navigate to signup page successfully");

    }

    //@Test//(dependsOnMethods = "navigateToSignupLoginPage")
    @Step("Verify 'New User Signup!' is visible")
    public static void verifyHeaderToSignupIsVisible() {
        String signupHeaderText = new P01_LoginSignupPage(getDriver())
                .getNewUserSignup().getText();
        Assert.assertEquals(signupHeaderText, "New User Signup!", "Verify 'New User Signup!' is visible");
    }


    // @Test//(dependsOnMethods = "verifyHeadLineToSignupIsVisible")
    @Step("enter valid name and email for signup")
    public void enterValidNameAndEmailForSignup() {
        boolean accountInfoPageVisible = new P01_LoginSignupPage(getDriver())
                .enterNameForSignup(name)
                .enterEmailForSignup(email)
                .clickOnSignupButton().enterAccountInformationText().isDisplayed();
        Assert.assertTrue(accountInfoPageVisible, "verify name and email acceptable and navigate to enter account information page successfully ");

    }

    // @Test//(dependsOnMethods = "enterValidNameAndEmailForSignup")
    @Step("Verify that 'ENTER ACCOUNT INFORMATION' is visible")
    public void enterAccountInfoIsVisible() {
        String enterAccountInfoText = new P02_EnterAccountInformationPage(getDriver())
                .enterAccountInformationText().getText();
        Assert.assertEquals(enterAccountInfoText, "ENTER ACCOUNT INFORMATION", "Verify that 'ENTER ACCOUNT INFORMATION' is visible");

    }


    //@Test//(dependsOnMethods = "enterAccountInfoIsVisible")
    @Step("Verify that 'ACCOUNT CREATED!' is visible")
    public void validRegisterAndCheckMessageAccountCreatedIsVisible() throws FileNotFoundException {
        String accountCreatedTitle = new P02_EnterAccountInformationPage(getDriver()).fillAccountDetails()
                .getAccountCreatedHeader().getText();
        Assert.assertEquals(accountCreatedTitle, "ACCOUNT CREATED!", " Verify that 'ACCOUNT CREATED!' is visible");
        LogUtility.info("fill account info successfully");
    }

    @Step("Verify that 'Logged in as username' is visible")
    public void VerifyThatLoggedInAsUserNameIsVisible() {
        String username = new P04_AccountCreated(getDriver()).clickOnContinueButton()
                .getUserName().getText();

        Utility.takeScreenShotSaveToAllureReportDirect(getDriver(), "account_created");
        Assert.assertEquals(username, name, "Verify that 'Logged in as username' is visible");
    }

    @Step("Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button")
    public static void VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton() {
        String accountDeleted = new P03_LoggedHomePage(getDriver())
                .clickOnDeleteAccount().getAccountDeletedTitle().getText();

        Assert.assertEquals(accountDeleted, "ACCOUNT DELETED!", "Verify that 'ACCOUNT DELETED!' is visible");
        new P05_AccountDeleted(getDriver()).clickOnContinueButton();
        LogUtility.info(" account deleted successfully");
    }


}
