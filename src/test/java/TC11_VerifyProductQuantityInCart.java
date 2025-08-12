import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P08_ProductsPage;
import pages.P09_ProductDetailsPage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Cart")
public class TC11_VerifyProductQuantityInCart extends TestBasic {

    @Test(description = "Test Case 11: Verify Product quantity in Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify Product quantity in Cart")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click 'View Product' for any product on home page
            5. Verify product detail is opened
            6. Increase quantity to 4
            7. Click 'Add to cart' button
            8. Click 'View Cart' button
            9. Verify that product is displayed in cart page with exact quantity""")
    public void verifyProductQuantityInCart() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        VerifyProductDetailIsOpened();
        VerifyThatProductIsDisplayedInCartPageWithExactQuantity();

    }

    public void VerifyProductDetailIsOpened() {
        new P08_ProductsPage(getDriver())
                .clickOnViewProductButton();
        LogUtility.info("Page title is: " + getDriver().getTitle());
        LogUtility.info("Verifying product detail page is opened");

        Assert.assertEquals(getDriver().getTitle(), "Automation Exercise - Product Details", "Verify product detail is opened");

    }

    @Step("Verify that product is displayed in cart page with exact quantity")
    public void VerifyThatProductIsDisplayedInCartPageWithExactQuantity() {
        String quantity = new P09_ProductDetailsPage(getDriver())
                .increaseQuantity("4").clickOnAddToCartButton().clickOnViewCart()
                .getProductsQuantity().get(0);

        LogUtility.info("Quantity in cart: " + quantity);
        LogUtility.info("Verifying that quantity in cart matches expected value 4");

        Assert.assertEquals(quantity, "4", "Verify that product is displayed in cart page with exact quantity");
    }

}