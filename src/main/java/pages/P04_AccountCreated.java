package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Utility;

public class P04_AccountCreated {
    private final WebDriver driver;
    //
    private final By accountCreatedTitle = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");

    public P04_AccountCreated(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAccountCreatedHeader() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(accountCreatedTitle));

    }

    public P03_LoggedHomePage clickOnContinueButton() {
        Utility.clickElement(driver, continueButton);
        return new P03_LoggedHomePage(driver);
    }

}
