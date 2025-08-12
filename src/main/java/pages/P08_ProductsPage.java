package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class P08_ProductsPage {
    private final WebDriver driver;

    private final By titleTextCenter = By.cssSelector("div.features_items>h2");

    private final By viewProductOfFirstProductButton = By.cssSelector("a[href='/product_details/1']");

    private final By searchProductInput = By.cssSelector("input#search_product");

    private final By searchProductButton = By.cssSelector("button#submit_search");

    private final By searchResultNames = By.cssSelector("div.single-products>div>p"); //product name that match with search text

    private final By viewProductButtonForProduct1 = By.cssSelector("a[href=\"/product_details/1\"]");

    private final By addToCartForProduct1 = By.cssSelector("div.single-products>div>a[data-product-id='1']");

    private final By addToCartForProduct2 = By.cssSelector("div.single-products>div>a[data-product-id='2']");
    private final By viewCartLink = By.cssSelector("p.text-center>a[href=\"/view_cart\"]");
    private final By continueShoppingButton = By.cssSelector("button[data-dismiss='modal']");


    private final By categoryHeader = By.cssSelector(".left-sidebar>h2");

    private final By menCategory = By.cssSelector("a[href='#Men']");

    private final By tshirtCategory = By.cssSelector("a[href='/category_products/3']");

    private final By brand = By.cssSelector("div[class='brands-name']");

    private final By poloBrand = By.cssSelector("a[href='/brand_products/Polo']");

    private final By madameBrand = By.cssSelector("a[href='/brand_products/Madame']");


    public P08_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement getTitleTextCenter() {
        return Utility.byToWebElement(titleTextCenter, driver);
    }

    public P09_ProductDetailsPage clickOnViewProductButton() {
        Utility.clickElement(driver, viewProductButtonForProduct1);
        return new P09_ProductDetailsPage(driver);
    }

    public P08_ProductsPage enterNameToSearchOnProduct(String text) {
        Utility.sendKey(driver, searchProductInput, text);
        Utility.clickElement(driver, searchProductButton);
        return new P08_ProductsPage(driver);
    }

    public List<String> getProductsSearchNames() {
        return Utility.byToWebElements(searchResultNames, driver)  //  List<WebElement>
                .stream()//to void do it by for loop
                .map(WebElement::getText) //turn web element to string by get text
                .collect(Collectors.toList()); //collect them in list again
    }

    public P10_CartPage addFirstAndSecondProductToCart() {
        Utility.clickElement(driver, addToCartForProduct1);
        Utility.clickElement(driver, continueShoppingButton);
        Utility.clickElement(driver, addToCartForProduct2);
        Utility.clickElement(driver, viewCartLink);
        return new P10_CartPage(driver);
    }

    public P08_ProductsPage clickOnMenCategory() {
        Utility.clickElement(driver, menCategory);
        return this;
    }

    public P08_ProductsPage clickOnTshirtCategory() {
        Utility.clickElement(driver, tshirtCategory);
        return this;
    }

    public WebElement getBrand() {
        return Utility.byToWebElement(brand, driver);
    }

    public P08_ProductsPage clickOnPoloBrand() {
        Utility.scrolling(driver, poloBrand);
        Utility.clickElement(driver, poloBrand);
        return this;
    }

    public P08_ProductsPage clickOnMadameBrand() {
        Utility.scrolling(driver, madameBrand);
        Utility.clickElement(driver, madameBrand);
        return this;
    }
}