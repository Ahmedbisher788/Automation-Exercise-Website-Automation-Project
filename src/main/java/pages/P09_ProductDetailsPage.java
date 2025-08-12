package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class P09_ProductDetailsPage {
    private final WebDriver driver;
    private final By ProductName = By.cssSelector("div.product-information>h2");
    private final By ProductCategory = By.cssSelector("div.product-information>p:first-of-type");
    private final By addProductButton = By.cssSelector("button[type=\"button\"]");
    private final By continueShoppingButton = By.cssSelector("button[data-dismiss=\"modal\"]");
    private final By viewCartLink = By.cssSelector("a[href=\"/view_cart\"]>u");
    private final By ProductAvailability = By.xpath("//div[@class=\"product-information\"]/p[2]");
    private final By ProductCondition = By.xpath("//div[@class=\"product-information\"]/p[3]");
    private final By ProductBrand = By.xpath("//div[@class=\"product-information\"]/p[4]");
    private final By ProductPrice = By.xpath("//div[@class=\"product-information\"]/span/span");
    private final By quantityInput = By.id("quantity");
    private final By getViewCartLink = By.cssSelector("p.text-center>a[href=\"/view_cart\"]");

    private final By nameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By reviewInput = By.cssSelector("textarea[name=\"review\"]");
    private final By submitButton = By.id("button-review");
    private final By thanksMessage = By.id("div[class='alert-success alert'] span");

    public P09_ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProductName() {
        return Utility.byToWebElement(ProductName, driver);
    }

    public WebElement getProductCategory() {
        return Utility.byToWebElement(ProductCategory, driver);
    }

    public WebElement getProductAvailability() {
        return Utility.byToWebElement(ProductAvailability, driver);
    }

    public WebElement getProductCondition() {
        return Utility.byToWebElement(ProductAvailability, driver);
    }

    public WebElement getProductBrand() {
        return Utility.byToWebElement(ProductBrand, driver);
    }

    public WebElement getProductPrice() {
        return Utility.byToWebElement(ProductPrice, driver);
    }

    public P09_ProductDetailsPage increaseQuantity(String value) {
        Utility.byToWebElement(quantityInput, driver).clear();
        Utility.sendKey(driver, quantityInput, value);
        return this;
    }

    public P09_ProductDetailsPage clickOnAddToCartButton() {
        Utility.clickElement(driver, addProductButton);
        return this;
    }

    public P09_ProductDetailsPage ClickOnContinueShopping() {
        Utility.clickElement(driver, continueShoppingButton);
        return this;
    }

    public P09_ProductDetailsPage increaseQuantityOfProduct(String input) {
        Utility.sendKey(driver, quantityInput, input);
        return this;
    }

    public P10_CartPage clickOnViewCart() {
        Utility.clickElement(driver, viewCartLink);
        return new P10_CartPage(driver);
    }


}
