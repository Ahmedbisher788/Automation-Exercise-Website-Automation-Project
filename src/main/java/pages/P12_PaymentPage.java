package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.DataUtils;
import utilities.Utility;

import java.awt.*;
import java.io.FileNotFoundException;

public class P12_PaymentPage {
    private final WebDriver driver;

    private final By nameOnCardInput = By.cssSelector("input[data-qa='name-on-card']");

    private final By cardNumberInput = By.cssSelector("input[data-qa='card-number']");

    private final By cvvInput = By.cssSelector("input[data-qa=\"cvc\"]");

    private final By expirationMonthInput = By.cssSelector("input[data-qa='expiry-month']");

    private final By expirationYearInput = By.cssSelector("input[data-qa='expiry-year']");

    private final By payAndConfirmOrderButton = By.cssSelector("button[data-qa='pay-button']");

    private final By alertSuccess = By.xpath("//div[contains(@id, 'success_message')]/div");

    private final By successMessage = By.xpath("//p[contains(text(), 'Congratulations')]");

    private final By downloadInvoiceButton = By.xpath("//a[contains(text(), 'Invoice')]");

    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");


    public P12_PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public P12_PaymentPage fillPaymentCardData() throws FileNotFoundException {
        Utility.sendKey(driver, nameOnCardInput, DataUtils.getJsonData("paymentDetails", "Name_on_Card"));
        Utility.sendKey(driver, cardNumberInput, DataUtils.getJsonData("paymentDetails", "Card_Number"));
        Utility.sendKey(driver, cvvInput, DataUtils.getJsonData("paymentDetails", "CVV"));
        Utility.sendKey(driver, expirationMonthInput, DataUtils.getJsonData("paymentDetails", "Ex_MM"));
        Utility.sendKey(driver, expirationYearInput, DataUtils.getJsonData("paymentDetails", "Ex_YY"));
        Utility.clickElement(driver, payAndConfirmOrderButton);
        return this;
    }

    public WebElement getAlertSuccess() {
        return Utility.byToWebElement(alertSuccess, driver);
    }

    public WebElement getSuccessMessage() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public P03_LoggedHomePage clickOnContinueButton() {
        Utility.clickElement(driver, continueButton);
        return new P03_LoggedHomePage(driver);
    }

    public P03_LoggedHomePage clickOnDownloadButton() throws AWTException {
        Utility.clickElement(driver, downloadInvoiceButton);
        return new P03_LoggedHomePage(driver);
    }
}
