package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class P10_CartPage {

    private final WebDriver driver;

    private final By productsInCartNames = By.xpath("//td[contains(@class, 'cart_description')]//a");

    private final By productsPrice = By.xpath("//td[contains(@class, 'cart_price')]/p");

    private final By productsTotalPrice = By.xpath("//p[contains(@class, 'cart_total_price')]");

    private final By productsQuantity = By.xpath("//td[contains(@class, 'cart_quantity')]/button");

    private final By checkoutButton = By.cssSelector("div.col-sm-6>a");

    private final By register_LoginLink = By.cssSelector("p.text-center>a[href='/login']");

    private final By removeItemButton = By.cssSelector("a.cart_quantity_delete");

    private final By cartEmptyText = By.id("empty_cart");


    public P10_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductsInCartNames() {
        return Utility.byToWebElements(productsInCartNames, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductsPrice() {
        return Utility.byToWebElements(productsPrice, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductsQuantity() {
        return Utility.byToWebElements(productsQuantity, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getProductsTotalPrice() {
        return Utility.byToWebElements(productsTotalPrice, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public P11_CheckoutPage clickOnCheckoutButton() {
        Utility.clickElement(driver, checkoutButton);
        return new P11_CheckoutPage(driver);
    }

    public P01_LoginSignupPage clickOnRegisterLoginLink() {
        Utility.clickElement(driver, register_LoginLink);
        return new P01_LoginSignupPage(driver);
    }

    public P10_CartPage removeAllItemFromCart() throws InterruptedException {
        List<WebElement> removeItem = Utility.byToWebElements(removeItemButton, driver);
        for (WebElement element : removeItem) {
            Utility.generalWait(driver).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Utility.generalWait(driver).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        }
        return this;
    }

    public WebElement getCartEmptyText() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(cartEmptyText));
    }

}
