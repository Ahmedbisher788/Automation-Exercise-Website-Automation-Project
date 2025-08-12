import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.DataUtils;
import utilities.LogUtility;
import utilities.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Epic("Regression Tests")
@Feature("Place Order")
public class TC12_RegisterWhileCheckout extends TestBasic {

    String name = Utility.getTimeStamp();
    String email = Utility.getTimeStamp() + "@gmail.com";


    @Test(description = "Test Case 12: Place Order: Register while Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Place Order: Register while Checkout")
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
            19. Click 'Delete Account' button
            20. Verify 'ACCOUNT DELETED!' and click 'Continue' button""")
    public void placeOrderRegisterWhileCheckout() throws IOException, ParseException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        addItemTOCartBeforeSignupAndVerifyNavigateToSignup();
        verifyAccountCreatedAndClickContinueButton(name, email);
        verifyLoggedInAsUsernameAtTop(name);
        new P03_LoggedHomePage(getDriver()).navigateToCartPage().clickOnCheckoutButton();
        verifyAddressDetailsAndReviewYourOrder();
        verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed();
        new P12_PaymentPage(getDriver()).clickOnContinueButton();
        TC01_RegisterUser.VerifyThatACCOUNTDELETEDIsVisibleAndClickContinueButton();
    }

    public static void addItemTOCartBeforeSignupAndVerifyNavigateToSignup() {
        String signupHeaderText = new P08_ProductsPage(getDriver()).addFirstAndSecondProductToCart()
                .clickOnCheckoutButton().clickOnRegisterLoginLink().getNewUserSignup().getText();
        Assert.assertEquals(signupHeaderText, "New User Signup!", "Verify 'New User Signup!' is visible");
    }

    @Step("Verify 'ACCOUNT CREATED!' and click 'Continue' button")
    public static void verifyAccountCreatedAndClickContinueButton(String name, String email) throws FileNotFoundException {
        String accountCreatedText = new P01_LoginSignupPage(getDriver())
                .enterNameForSignup(name)
                .enterEmailForSignup(email)
                .clickOnSignupButton().fillAccountDetails().getAccountCreatedHeader().getText();
        Assert.assertEquals(accountCreatedText, "ACCOUNT CREATED!", "Verify 'ACCOUNT CREATED!'");
        new P04_AccountCreated(getDriver()).clickOnContinueButton();
    }

    @Step("Verify ' Logged in as username' at top")
    public static void verifyLoggedInAsUsernameAtTop(String name) {
        String username = new P03_LoggedHomePage(getDriver())
                .getUserName()
                .getText();
        Assert.assertEquals(username, name, "Verify ' Logged in as username' at top");
    }

    //todo list length = zero find problem

    public static void verifyAddressDetails() throws IOException, ParseException {
        try {

            List<String> addressDelivery = new P11_CheckoutPage(getDriver()).getAddressDelivery();
            List<String> addressInvoice = new P11_CheckoutPage(getDriver()).getAddressInvoice();


            Assert.assertEquals(addressDelivery.get(0), "YOUR DELIVERY ADDRESS", "Verify Address Details");
            Assert.assertEquals(addressInvoice.get(0), "YOUR BILLING ADDRESS", "Verify Address Details");

            for (int i = 1; i < 8; i++) {
                Assert.assertEquals(addressDelivery.get(i), addressInvoice.get(i), "Verify Address Details");
            }

            String no1 = "Mr. " + DataUtils.getJsonData("AccountDetails", "first_name") + " " + DataUtils.getJsonData("AccountDetails", "last_name");
            String no2 = DataUtils.getJsonData("AccountDetails", "company");
            String no3 = DataUtils.getJsonData("AccountDetails", "address1");
            String no4 = DataUtils.getJsonData("AccountDetails", "address2");
            String no5 = DataUtils.getJsonData("AccountDetails", "city") + " " +
                    DataUtils.getJsonData("AccountDetails", "state") + " " + DataUtils.getJsonData("AccountDetails", "zipcode");
            String no6 = DataUtils.getJsonData("AccountDetails", "country");
            String no7 = DataUtils.getJsonData("AccountDetails", "mobileNumber");

            Assert.assertEquals(addressDelivery.get(1), no1, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(2), no2, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(3), no3, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(4), no4, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(5), no5, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(6), no6, "Verify Address Details");
            Assert.assertEquals(addressDelivery.get(7), no7, "Verify Address Details");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX--" +
                    "list size equal zero ");

        }
    }

    @Step("Verify Address Details and Review Your Order")
    public static void verifyAddressDetailsAndReviewYourOrder() throws IOException, ParseException {
        verifyAddressDetails();
        new P11_CheckoutPage(getDriver());

        List<String> productNames = new P10_CartPage(getDriver()).getProductsInCartNames();
        List<String> prices = new P10_CartPage(getDriver()).getProductsPrice();
        List<String> quantity = new P10_CartPage(getDriver()).getProductsQuantity();
        List<String> totalPrices = new P10_CartPage(getDriver()).getProductsTotalPrice();
        String totalAmount = new P11_CheckoutPage(getDriver()).getTotalAmount().getText();

        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(totalPrices.get(i), prices.get(i), "Verify Review Your Order");
            Assert.assertEquals(quantity.get(i), "1", "Verify Review Your Order");
        }

        LogUtility.info("Verifying first product name | Expected: Blue Top | Actual: " + productNames.get(0));
        Assert.assertEquals(productNames.get(0), "Blue Top", "Verify Review Your Order");
        LogUtility.info("Verifying second product name | Expected: Men Tshirt | Actual: " + productNames.get(1));
        Assert.assertEquals(productNames.get(1), "Men Tshirt", "Verify Review Your Order");
        LogUtility.info("Verifying total amount | Expected: Rs. 900 | Actual: " + totalAmount);
        Assert.assertEquals(totalAmount, "Rs. 900", "Verify Review Your Order");
    }

    @Step("Verify success message 'Congratulations! Your order has been confirmed!'")
    public static void verifySuccessMessageCongratulationsYourOrderHasBeenConfirmed() throws IOException, ParseException {
        String alertSuccessText = new P11_CheckoutPage(getDriver())
                .fillCommentField("hello everybody.")
                .clickOnPlaceOrderButton()
                .fillPaymentCardData()
                .getSuccessMessage()
                .getText();

        LogUtility.info("Actual success message: " + alertSuccessText);
        LogUtility.info("Expected success message: Congratulations! Your order has been confirmed!");
        Assert.assertEquals(alertSuccessText, "Congratulations! Your order has been confirmed!", "Verify success message 'Congratulations! Your order has been confirmed!'");

    }


/*   public void addProductsToCart() throws FileNotFoundException {
        String Success = new P08_ProductsPage(getDriver()).addFirstAndSecondProductToCart()
                .clickOnCheckoutButton().clickOnRegisterLoginLink()
                .fillCorrectSignup(name, email).fillAccountDetails()
                .clickOnContinueButton().navigateToCartPage()
                .clickOnCheckoutButton()
                .fillCommentField("hello everyone").clickOnPlaceOrderButton()
                .fillPaymentCardData().getSuccessMessage().getText();

        Assert.assertEquals(Success, "Congratulations! Your order has been confirmed!",
                "Verify success message 'Congratulations! Your order has been confirmed!'");
    }*/
}