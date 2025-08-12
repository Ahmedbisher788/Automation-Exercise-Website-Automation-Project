import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P10_CartPage;

@Epic("Regression Tests")
@Feature("Cart")
public class TC15_RemoveProductsFromCart extends TestBasic {

    @Test(description = "Test Case 15: Remove Products From Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove Products From Cart")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Add products to cart
            5. Click 'Cart' button
            6. Verify that cart page is displayed
            7. Click 'X' button corresponding to particular product
            8. Verify that product is removed from the cart""")
    public void removeProductsFromCart() throws InterruptedException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        TC10_AddProductsInCart.verifyBothProductsAreAddedToCart();
        verifyThatProductIsRemovedFromTheCart();
    }

    public void verifyThatProductIsRemovedFromTheCart() throws InterruptedException {
        String cartEmptyText = new P10_CartPage(getDriver()).removeAllItemFromCart()
                .getCartEmptyText().getText();
        Assert.assertEquals(cartEmptyText, "Cart is empty! Click here to buy products.", "Verify that product is removed from the cart and text 'cart empty displayed successfully'");
    }
}