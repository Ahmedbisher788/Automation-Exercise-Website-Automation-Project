import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P01_LoginSignupPage;
import pages.P03_LoggedHomePage;
import utilities.DataUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

@Epic("Regression test")
@Feature(" Place Order: Login before Checkout")
public class TC14_LoginBeforeCheckout extends TestBasic {

    @Test(description = "Test Case 14: Place Order: Login before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Place Order: Login before Checkout")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Signup / Login' button
            5. Fill email, password and click 'Login' button
            6. Verify 'Logged in as username' at top
            7. Add products to cart
            8. Click 'Cart' button
            9. Verify that cart page is displayed
            10. Click Proceed To Checkout
            11. Verify Address Details and Review Your Order
            12. Enter description in comment text area and click 'Place Order'
            13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
            14. Click 'Pay and Confirm Order' button
            15. Verify success message 'Congratulations! Your order has been confirmed!'""")
    public void placeOrderRegisterBeforeCheckout() throws IOException, ParseException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC01_RegisterUser.navigateToSignupLoginPage();
        TC02_ValidLogin.loginWithValidEmailAndPassword();
        TC12_RegisterWhileCheckout.verifyLoggedInAsUsernameAtTop(DataUtils.getJsonData("existingUser", "name"));
        new P03_LoggedHomePage(getDriver()).navigateToProductPage().addFirstAndSecondProductToCart().clickOnCheckoutButton();
        TC12_RegisterWhileCheckout.verifyAddressDetailsAndReviewYourOrder();
        TC12_RegisterWhileCheckout.verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        TC01_RegisterUser.VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton();
        registerWithExistingAccountForNextTest();

    }


    @Description("if page database deleted the account ")
    public void registerWithExistingAccountForNextTest() throws FileNotFoundException {
        new P00_HomePage(getDriver()).navigateToSignupLoginPage();
        new P01_LoginSignupPage(getDriver()).registerForExistingAccount(); //because account deleted
        new P03_LoggedHomePage(getDriver()).clickOnLogout();
    }
}


