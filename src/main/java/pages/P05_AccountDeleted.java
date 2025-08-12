package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class P05_AccountDeleted {
    private final WebDriver driver;

    private final By accountDeletedTitle = By.cssSelector("h2[data-qa=\"account-deleted\"]>b");
    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");


    public P05_AccountDeleted(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAccountDeletedTitle() {
        return Utility.byToWebElement(accountDeletedTitle, driver);
    }

    public P00_HomePage clickOnContinueButton() {
        Utility.clickElement(driver, continueButton);
        return new P00_HomePage(driver);
    }

}
