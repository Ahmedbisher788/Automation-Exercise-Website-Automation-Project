import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P00_HomePage;
import pages.P08_ProductsPage;
import utilities.DataUtils;
import utilities.LogUtility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Epic("Regression Tests")
@Feature("Products")
public class TC17_ViewCartBrandProducts extends TestBasic {

    @Test(description = "Test Case 17: View & Cart Brand Products")
    @Severity(SeverityLevel.CRITICAL)
    @Story("View & Cart Brand Products")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Click on 'Products' button
            4. Verify that Brands are visible on left side bar
            5. Click on any brand name
            6. Verify that user is navigated to brand page and brand products are displayed
            7. On left side bar, click on any other brand link
            8. Verify that user is navigated to that brand page and can see products""")
    public void viewCartBrandProducts() throws IOException, ParseException {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        verifyThatBrandsAreVisibleOnLeftSideBar();
        verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed();
        verifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts();

    }

    public void verifyThatBrandsAreVisibleOnLeftSideBar() {
        boolean brand = new P00_HomePage(getDriver()).navigateToProductPage().getBrand()
                .isDisplayed();
        LogUtility.info("Verifying that Brands are visible on left side bar | Expected: true | Actual: " + brand);
        Assert.assertTrue(brand, " Verify that Brands are visible on left side bar");
    }

    public void verifyThatUserIsNavigatedToBrandPageAndBrandProductsAreDisplayed() throws FileNotFoundException {
        String titleText = new P08_ProductsPage(getDriver()).clickOnPoloBrand()
                .getTitleTextCenter().getText();

        LogUtility.info("Verifying brand page title for Polo | Expected: BRAND - POLO PRODUCTS | Actual: " + titleText);
        Assert.assertEquals(titleText, "BRAND - POLO PRODUCTS", "Verify that user is navigated to brand page");
        List<String> productsName = new P08_ProductsPage(getDriver()).getProductsSearchNames();
        for (int i = 0; i < 6; i++) {
            Assert.assertEquals(productsName.get(i), DataUtils.getJsonData("poloBrandItem", String.valueOf(i))
                    , "Verify that user is navigated to brand page and brand products are displayed");
            // System.out.println("product visible" + productsName.get(i));
        }


    }

    public void verifyThatUserIsNavigatedToThatBrandPageAndCanSeeProducts() throws FileNotFoundException {
        String titleText = new P08_ProductsPage(getDriver()).clickOnMadameBrand()
                .getTitleTextCenter().getText();

        LogUtility.info("Verifying brand page title for Madame | Expected: BRAND - MADAME PRODUCTS | Actual: " + titleText);
        Assert.assertEquals(titleText, "BRAND - MADAME PRODUCTS", "Verify that user is navigated to that brand page");
        List<String> productsName = new P08_ProductsPage(getDriver()).getProductsSearchNames();
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(productsName.get(i), DataUtils.getJsonData("MadameBrandProducts", String.valueOf(i))
                    , "Verify that user is navigated to brand page and brand products are displayed");
            // System.out.println("product visible" + productsName.get(i));
        }
    }
}