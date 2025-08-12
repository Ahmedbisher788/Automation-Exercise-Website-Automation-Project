import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P08_ProductsPage;
import pages.P10_CartPage;
import utilities.LogUtility;

import java.util.List;
import java.util.Objects;

@Epic("Feature")
@Feature("cart")
public class TC10_AddProductsInCart extends TestBasic {
    @Test(description = "Test Case 10: Add Products in Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add Products in Cart")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'Products' button
            5. Hover over first product and click 'Add to cart'
            6. Click 'Continue Shopping' button
            7. Hover over second product and click 'Add to cart'
            8. Click 'View Cart' button
            9. Verify both products are added to Cart
            10. Verify their prices, quantity and total price""")
    public void verifyAddProductsInCart() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifyBothProductsAreAddedToCart();
        verifyTheirPricesQuantityAndTotalPrice();

    }

    @Step("Verify both products are added to Cart")
    public static void verifyBothProductsAreAddedToCart() {
        List<String> products = new P08_ProductsPage(getDriver()).addFirstAndSecondProductToCart()
                .getProductsInCartNames();
        LogUtility.info("Verifying that exactly 2 products are in the cart");
        Assert.assertEquals(products.size(), 2, "Verify both products are added to Cart");

    }

    @Step("Verify their prices, quantity and total price")
    public void verifyTheirPricesQuantityAndTotalPrice() {
        List<String> prices = new P10_CartPage(getDriver()).getProductsPrice();
        List<String> totalPrices = new P10_CartPage(getDriver()).getProductsTotalPrice();
        List<String> quantity = new P10_CartPage(getDriver()).getProductsQuantity();
        for (int i = 0; i < 2; i++) {
            Assert.assertTrue(Objects.equals(prices.get(i), totalPrices.get(i)));
            Assert.assertTrue(Objects.equals(quantity.get(i), "1"));
            System.out.println(i + "prices = total prices" + prices.get(i) + "=" + totalPrices.get(i));
            System.out.println(i + "quantity = 1 " + quantity.get(i).equals("1"));
        }

    }

}
