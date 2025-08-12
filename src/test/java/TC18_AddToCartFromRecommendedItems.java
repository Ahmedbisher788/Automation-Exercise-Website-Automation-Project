import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P10_CartPage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Cart")
public class TC18_AddToCartFromRecommendedItems extends TestBasic {
    @Test(description = "Test Case 22: Add to cart from Recommended items")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add to cart from Recommended items")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Scroll to bottom of page
            4. Verify 'RECOMMENDED ITEMS' are visible
            5. Click on 'Add To Cart' on Recommended product
            6. Click on 'View Cart' button
            7. Verify that product is displayed in cart page""")

    public void AddToCartFromRecommendedItems() {
        LogUtility.info("Starting test: Add To Cart From Recommended Items");
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyRecommendedItemsAreVisible();
        verifyThatProductIsDisplayedInCartPage();
    }

    public void verifyRecommendedItemsAreVisible() {
        LogUtility.info("Checking if 'RECOMMENDED ITEMS' section is visible");
        boolean recommendedText = new P00_HomePage(getDriver())
                .getRecommendedTitleTextCenter().isDisplayed();
        LogUtility.info("Expected section visibility: true, Actual: " + recommendedText);
        Assert.assertTrue(recommendedText, "Verify 'RECOMMENDED ITEMS' are visible");
    }

    public void verifyThatProductIsDisplayedInCartPage() {
        LogUtility.info("Clicking on Blue Top from Recommended Items");
        new P00_HomePage(getDriver())
                .clickOnBlueTopFromRecommended()
                .clickOnViewCartPage();
        String itemName = new P10_CartPage(getDriver()).getProductsInCartNames().get(0);
        LogUtility.info("Expected product name: Blue Top, Actual: " + itemName);
        Assert.assertEquals(itemName, "Blue Top", "Verify that product is displayed in cart page");
    }


}
