import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P08_ProductsPage;
import utilities.DataUtils;
import utilities.LogUtility;

import java.util.List;

@Epic("Regression Tests")
@Feature("Search")

public class TC09_SearchProduct extends TestBasic {

    static String search;

    static { //to initialize data only one in the class
        try {
            search = DataUtils.getJsonData("searchKeyWord", "search1");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load search keyword", e);
        }
    }

    @Test(description = "Test Case 9: Search Product")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search Product")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Click on 'Products' button
            5. Verify user is navigated to ALL PRODUCTS page successfully
            6. Enter product name in search input and click search button
            7. Verify 'SEARCHED PRODUCTS' is visible
            8. Verify all the products related to search are visible""")
    public void testSearchProduct() {
        TC01_RegisterUser.verifyHomePageVisibleSuccessfully();
        TC08_VerifyAllProductsAndProductDetailPage.verifyUserIsNavigatedToAllProductsPageSuccessfully();
        enterProductNameInSearchInputAndClickOnSearchButton();
        verifyAllTheProductsRelatedToSearchAreVisible();
    }


    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public void enterProductNameInSearchInputAndClickOnSearchButton() {
        String title = new P08_ProductsPage(getDriver())
                .enterNameToSearchOnProduct(search).getTitleTextCenter().getText();

        LogUtility.info("Verifying 'SEARCHED PRODUCTS' title is visible after search");
        Assert.assertEquals(title, "SEARCHED PRODUCTS", "Verify 'SEARCHED PRODUCTS' is visible");
    }

    @Step("Verify all the products related to search are visible") //make sure search not null or not found!!
    public static List<String> verifyAllTheProductsRelatedToSearchAreVisible() {

        List<String> names = new P08_ProductsPage(getDriver()).getProductsSearchNames();

        LogUtility.info("Retrieved " + names.size() + " products from search results");

        names.forEach(name -> {

            Assert.assertTrue(name.toLowerCase().contains(search.toLowerCase()),
                    "names don't contain search name " + name);
            System.out.println(name + "-contain :" + search);
        });
        return names;
    }

}

