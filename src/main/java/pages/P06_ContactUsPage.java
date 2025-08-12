package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DataUtils;
import utilities.Utility;

public class P06_ContactUsPage {

    private final By getInTouch = By.cssSelector("h2.title:nth-child(2)");
    private final By nameInput = By.cssSelector("input[name=\"name\"]");
    private final By emailInput = By.cssSelector("input[name=\"email\"]");
    private final By subjectInput = By.cssSelector("input[name=\"subject\"]");
    private final By messageInput = By.id("message");
    private final By uploadFileInput = By.cssSelector("input[name=\"upload_file\"]");
    private final By submitButton = By.cssSelector("input[name=\"submit\"]");
    private final By homePageButton = By.cssSelector("#form-section>a");
    private final By alertSuccess = By.cssSelector(".status.alert.alert-success");

    private final WebDriver driver;

    public P06_ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public P06_ContactUsPage fillContactUsFrom() {
        Utility.sendKey(driver, nameInput, "ahmed");
        Utility.sendKey(driver, emailInput, "ahmed@gmail.com");
        Utility.sendKey(driver, subjectInput, "Test subject");
        Utility.sendKey(driver, messageInput, "Please find the test results attached for your review. The execution covered the main scenarios related to");
        Utility.scrolling(driver, uploadFileInput);
        Utility.sendKey(driver, uploadFileInput, DataUtils.uploadFilePath(driver, "test_image.png"));

        return this;
    }

    public P06_ContactUsPage clickOnSubmitButton() {
        Utility.clickElement(driver, submitButton);
        return this;
    }

    public P06_ContactUsPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public WebElement getGetInTouch() {
        return Utility.byToWebElement(getInTouch, driver);
    }

    public WebElement getAlertSuccess() {
        return Utility.byToWebElement(alertSuccess, driver);
    }

    public P00_HomePage clickOnHomeButton() {
        Utility.clickElement(driver, homePageButton);
        return new P00_HomePage(driver);
    }
}
