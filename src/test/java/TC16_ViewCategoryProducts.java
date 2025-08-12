import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P08_ProductsPage;
import utilities.LogUtility;

@Epic("Regression Tests")
@Feature("Products")
public class TC16_ViewCategoryProducts extends TestBasic {

    @Test(description = "Test Case 17: View Category Products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View Category Products")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that categories are visible on left side bar
            4. Click on 'Women' category
            5. Click on any category link under 'Women' category, for example: Dress
            6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
            7. On left side bar, click on any sub-category link of 'Men' category
            8. Verify that user is navigated to that category page""")
    public void viewCategoryProducts() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyThatCategoriesAreVisibleOnLeftSideBar();
        verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts();
        verifyThatUserIsNavigatedToThatCategoryPage();
    }

    public void verifyThatCategoriesAreVisibleOnLeftSideBar() {
        boolean categoryAreVisible = new P00_HomePage(getDriver())
                .getCategories().isDisplayed();
        LogUtility.info("Verifying that categories are visible on left side bar | Expected: true | Actual: " + categoryAreVisible);

        Assert.assertTrue(categoryAreVisible, "\"Verify that categories are visible on left side bar\"");
    }

    public void verifyThatCategoryPageIsDisplayedAndConfirmTextWomenDressProducts() {
        String titleTextCenter = new P00_HomePage(getDriver())
                .openWomanCategory()
                .clickOnDressCategory()
                .getTitleTextCenter().getText();

        LogUtility.info("Verifying category page title for Women Dress | Expected: WOMEN - DRESS PRODUCTS | Actual: " + titleTextCenter);
        Assert.assertEquals(titleTextCenter, "WOMEN - DRESS PRODUCTS", "Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'");

    }

    public void verifyThatUserIsNavigatedToThatCategoryPage() {
        String titleTextCenter = new P08_ProductsPage(getDriver())
                .clickOnMenCategory().clickOnTshirtCategory()
                .getTitleTextCenter().getText();
        LogUtility.info("Verifying category page title for Men Tshirts | Expected: MEN - TSHIRTS PRODUCTS | Actual: " + titleTextCenter);
        Assert.assertEquals(titleTextCenter, "MEN - TSHIRTS PRODUCTS", "Verify that user is navigated to that category page");
    }
}