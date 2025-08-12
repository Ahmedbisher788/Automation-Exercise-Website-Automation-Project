package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Utility;

public class P03_LoggedHomePage {

    private final By cartButton = By.cssSelector("li>a[href=\"/view_cart\"]");
    private final By username = By.xpath("//a[contains(text(),'Logged in as')]/b");
    private final By logout = By.xpath("//a[contains(text(),\" Logout\")]");
    private final By deleteAccount = By.xpath("//a[contains(text(),\" Delete Account\")]");
    private final WebDriver driver;

    public P03_LoggedHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUserName() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(username));
    }

    public P05_AccountDeleted clickOnDeleteAccount() {
        Utility.clickElement(driver, deleteAccount);
        return new P05_AccountDeleted(driver);
    }

    public P01_LoginSignupPage clickOnLogout() {
        Utility.clickElement(driver, logout);
        return new P01_LoginSignupPage(driver);
    }

    public P10_CartPage navigateToCartPage() {
        Utility.clickElement(driver, cartButton);
        return new P10_CartPage(driver);
    }

    public P08_ProductsPage navigateToProductPage() {
        new P00_HomePage(driver).navigateToProductPage();
        return new P08_ProductsPage(driver);
    }
}
