import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P06_ContactUsPage;
import utilities.LogUtility;

import java.io.FileNotFoundException;

@Epic(value = "regression Test")
@Feature("Contact us form")

public class TC06_ContactUsForm extends TestBasic {

    @Story("Contact us form")
    @Test(description = "test case 6 :  Contact us form")
    @Severity(SeverityLevel.MINOR)
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Contact Us' button
            5. Verify 'GET IN TOUCH' is visible
            6. Enter name, email, subject and message
            7. Upload file
            8. Click 'Submit' button
            9. Click OK button
            10. Verify success message 'Success! Your details have been submitted successfully.' is visible
            11. Click 'Home' button and verify that landed to home page successfully""")
    public void contactUsForm() throws FileNotFoundException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyNavigateTOContentUs();
        checkVerifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisibleUpload();
        clickHomeButtonAndVerifyThatLandedToHomePageSuccessfully();


    }

    public void verifyNavigateTOContentUs() {
        String getInTouch =
                new P00_HomePage(getDriver()).navigateToContactUsPage()
                        .getGetInTouch().getText();

        LogUtility.info("Navigating to Contact Us page and verifying 'GET IN TOUCH' is visible");

        Assert.assertEquals(getInTouch, "GET IN TOUCH", "Verify 'GET IN TOUCH' is visible");

    }

    public void checkVerifySuccessMessageSuccessYourDetailsHaveBeenSubmittedSuccessfullyIsVisibleUpload() {
        String alertSuccess = new P06_ContactUsPage(getDriver())
                .fillContactUsFrom().clickOnSubmitButton()
                .acceptAlert().
                getAlertSuccess().getText();

        LogUtility.info("Verifying success message after form submission");

        Assert.assertEquals(alertSuccess, "Success! Your details have been submitted successfully.",
                "Verify success message 'Success! Your details have been submitted successfully.' is visible");
    }

    public void clickHomeButtonAndVerifyThatLandedToHomePageSuccessfully() {
        boolean homePageIsDisplay = new P06_ContactUsPage(getDriver())
                .clickOnHomeButton()
                .homePageIsVisible(getDriver())
                .isDisplayed();

        LogUtility.info("Verifying landing to Home page successfully");
        Assert.assertTrue(homePageIsDisplay,
                "Click 'Home' button and verify that landed to home page successfully");
    }
}