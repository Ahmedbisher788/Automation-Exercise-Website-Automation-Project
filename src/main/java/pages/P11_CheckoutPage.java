package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;
import java.util.stream.Collectors;

public class P11_CheckoutPage {


    private final By addressDelivery = By.cssSelector("ul[id=\"address_delivery\"]>li");
    private final By addressInvoice = By.cssSelector("ul[id=\"address_invoice\"]>li");
    private final By totalAmount = By.xpath("//section/div/div[5]/table/tbody/tr[3]/td[4]/p");
    private final By commentInput = By.className("form-control");
    private final By placeOrderButton = By.cssSelector("a[href='/payment']");

    private final By register_LoginLink = By.cssSelector("p.text-center>a[href='/login']");

    private final WebDriver driver;

    public P11_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    public List<String> getAddressDelivery() {
        return Utility.byToWebElements(addressDelivery, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAddressInvoice() {
        return Utility.byToWebElements(addressInvoice, driver)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getTotalAmount() {
        return Utility.byToWebElement(totalAmount, driver);
    }

    public P11_CheckoutPage fillCommentField(String comment) {
        Utility.scrolling(driver, placeOrderButton);
        Utility.sendKey(driver, commentInput, comment);
        return this;
    }

    public P12_PaymentPage clickOnPlaceOrderButton() {
        Utility.scrolling(driver, placeOrderButton);
        Utility.clickElement(driver, placeOrderButton);
        return new P12_PaymentPage(driver);
    }

    public P01_LoginSignupPage clickOnRegisterLoginLink() {
        Utility.clickElement(driver, register_LoginLink);
        return new P01_LoginSignupPage(driver);
    }
}
