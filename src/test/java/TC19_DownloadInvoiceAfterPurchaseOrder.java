import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P03_LoggedHomePage;
import pages.P12_PaymentPage;
import utilities.LogUtility;
import utilities.Utility;
import utilities.VerifyDownload;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

@Epic("Regression Tests")
@Feature("Invoice")
public class TC19_DownloadInvoiceAfterPurchaseOrder extends TestBasic {


    String name = "name" + Utility.getTimeStamp();
    String email = "email" + Utility.getTimeStamp() + "@gmail.com";

    @Test(description = "Test Case 19: Download Invoice after purchase order")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Download Invoice after purchase order")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click Proceed To Checkout
            8. Click 'Register / Login' button
            9. Fill all details in Signup and create account
            10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
            11. Verify ' Logged in as username' at top
            12. Click 'Cart' button
            13. Click 'Proceed To Checkout' button
            14. Verify Address Details and Review Your Order
            15. Enter description in comment text area and click 'Place Order'
            16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            17. Click 'Pay and Confirm Order' button
            18. Verify success message 'Congratulations! Your order has been confirmed!'
            19. Click 'Download Invoice' button and verify invoice is downloaded successfully
            20. Click 'Continue' button
            21. Click 'Delete Account' button
            22. Verify 'ACCOUNT DELETED!' and click 'Continue' button""")
    public void downloadInvoiceAfterPurchaseOrder() throws IOException, ParseException, AWTException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        TC12_RegisterWhileCheckout.addItemTOCartBeforeSignupAndVerifyNavigateToSignup();
        TC12_RegisterWhileCheckout.verifyAccountCreatedAndClickContinueButton(name, email);
        TC12_RegisterWhileCheckout.verifyLoggedInAsUsernameAtTop(name);
        new P03_LoggedHomePage(getDriver()).navigateToCartPage().clickOnCheckoutButton();
        TC12_RegisterWhileCheckout.verifyAddressDetailsAndReviewYourOrder();
        TC12_RegisterWhileCheckout.verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully();
        TC01_RegisterUser.VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton();
    }


    @Step("Click 'Download Invoice' button and verify invoice is downloaded successfully")
    private void clickDownloadInvoiceButtonAndVerifyInvoiceIsDownloadedSuccessfully() throws IOException, AWTException {
        LogUtility.info("Clicking on 'Download Invoice' button...");
        new P12_PaymentPage(getDriver()).clickOnDownloadButton();

        LogUtility.info("Verifying if the invoice file 'invoice.txt' is downloaded...");
        boolean isFileDownloaded = VerifyDownload.isFileDownloaded("invoice", "txt", 5000);

        LogUtility.info("Expected file: invoice.txt | Actual result: " + isFileDownloaded);
        Assert.assertTrue(isFileDownloaded, "Verify invoice is downloaded successfully.");
        LogUtility.info("Invoice download verification passed successfully.");
    }

}

