import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.P03_LoggedHomePage;
import utilities.Utility;

import java.io.IOException;
import java.text.ParseException;

@Epic("Regression test")
@Feature("place order")
public class TC13_RegisterBeforeCheckout extends TestBasic {
    String name = Utility.getTimeStamp();
    String email = Utility.getTimeStamp() + "@gmail.com";

    @Test(description = "Test Case 13: Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Place Order: Register before Checkout")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Signup / Login' button
            5. Fill all details in Signup and create account
            6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
            7. Verify ' Logged in as username' at top
            8. Add products to cart
            9. Click 'Cart' button
            10. Verify that cart page is displayed
            11. Click Proceed To Checkout
            12. Verify Address Details and Review Your Order
            13. Enter description in comment text area and click 'Place Order'
            14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            15. Click 'Pay and Confirm Order' button
            16. Verify success message 'Congratulations! Your order has been confirmed!'
            17. Click 'Delete Account' button
            18. Verify that 'ACCOUNT DELETED!' and click 'Continue' button""")
    public void placeOrderRegisterBeforeCheckout() throws IOException, ParseException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        TC12_RegisterWhileCheckout.verifyAccountCreatedAndClickContinueButton(name, email);
        TC12_RegisterWhileCheckout.verifyLoggedInAsUsernameAtTop(name);
        new P03_LoggedHomePage(getDriver()).navigateToProductPage().addFirstAndSecondProductToCart().clickOnCheckoutButton();
        TC12_RegisterWhileCheckout.verifyAddressDetailsAndReviewYourOrder();
        TC12_RegisterWhileCheckout.verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        TC01_RegisterUser.VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton();


    }
}
