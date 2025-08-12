import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P08_ProductsPage;
import pages.P09_ProductDetailsPage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Verify")
public class TC08_VerifyAllProductsAndProductDetailPage extends TestBasic {

    public static void verifyUserIsNavigatedToAllProductsPageSuccessfully() {
        String title = new P00_HomePage(getDriver()).navigateToProductPage()
                .getTitleTextCenter().getText();
        Assert.assertEquals(title, "ALL PRODUCTS", "Verify user is navigated to ALL PRODUCTS page successfully");
    }

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify All Products and product detail page")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Products' button
            5. Verify user is navigated to ALL PRODUCTS page successfully
            6. The products list is visible
            7. Click on 'View Product' of first product
            8. User is landed to product detail page
            9. Verify that detail detail is visible: product name, category, price, availability, condition, brand""")

    public void verifyAllProductsAndProductDetailPage() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyUserIsNavigatedToAllProductsPageSuccessfully();
        verifyThatDetailProductIsVisible();
    }

    public void verifyThatDetailProductIsVisible() {
        new P08_ProductsPage(getDriver())
                .clickOnViewProductButton();
        boolean name = new P09_ProductDetailsPage(getDriver()).getProductName().isDisplayed();
        boolean category = new P09_ProductDetailsPage(getDriver()).getProductCategory().isDisplayed();
        boolean price = new P09_ProductDetailsPage(getDriver()).getProductPrice().isDisplayed();
        boolean availability = new P09_ProductDetailsPage(getDriver()).getProductAvailability().isDisplayed();
        boolean condition = new P09_ProductDetailsPage(getDriver()).getProductCondition().isDisplayed();
        boolean brand = new P09_ProductDetailsPage(getDriver()).getProductBrand().isDisplayed();

        LogUtility.info("Verifying that products details is visible successfully");

        Assert.assertTrue(name, "Verify that detail detail is visible: name");
        Assert.assertTrue(category, "Verify that detail detail is visible: category");
        Assert.assertTrue(price, "Verify that detail detail is visible: price");
        Assert.assertTrue(availability, "Verify that detail detail is visible: availability");
        Assert.assertTrue(condition, "Verify that detail detail is visible: condition");
        Assert.assertTrue(brand, "Verify that detail detail is visible: brand");

    }
}
