import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Verify")
public class TC20_VerifyScrollingUsingArrowButton extends TestBasic {

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Scroll down page to bottom
            5. Verify 'SUBSCRIPTION' is visible
            6. Click on arrow at bottom right side to move upward
            7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen""")
    public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality() throws InterruptedException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifySubscriptionIsVisible();
        verifyThatPageIsScrolledUpAndImageAutomationExerciseIsVisibleOnScreen();

    }

    public void verifySubscriptionIsVisible() {
        boolean subscriptionIsDisplayed = new P00_HomePage(getDriver())
                .getSubscriptionTitle().isDisplayed();

        LogUtility.info("'SUBSCRIPTION' section is visible .");
        Assert.assertTrue(subscriptionIsDisplayed, "Verify 'SUBSCRIPTION' is visible");

    }

    public void verifyThatPageIsScrolledUpAndImageAutomationExerciseIsVisibleOnScreen() {
        boolean ImageAutomationExercise = new P00_HomePage(getDriver()).cickOnScrollUpButton()
                .getAutomationExerciseImg().isDisplayed();

        LogUtility.info("Image Automation Exercise visibility ");
        Assert.assertTrue(ImageAutomationExercise,
                "Verify tha tImage Automation Exercise IsVisible On Screen");
    }

//    public void verifyPageIsAtTop() {
//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        long yOffset = (long) js.executeScript("return window.pageYOffset;");
//        System.out.println("Scroll Y: " + yOffset);

//        Assert.assertEquals(yOffset, 0L, "Page is not at the top");
//    }
}