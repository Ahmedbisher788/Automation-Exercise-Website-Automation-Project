package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.DataUtils;
import utilities.Utility;

import java.io.FileNotFoundException;

public class P01_LoginSignupPage {

    private final By newUserSignupHeader = By.cssSelector("div.signup-form>h2");
    private final By loginToUserAccountHeader = By.cssSelector("div.login-form>h2");

    private final By signupNameInput = By.cssSelector("input[data-qa=\"signup-name\"]");
    private final By signupEmailInput = By.cssSelector("input[data-qa=\"signup-email\"]");
    private final By signupButton = By.cssSelector("button[data-qa=\"signup-button\"]");
    private final By errorEmailAlreadyExist = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p");
    private final By loginEmailInput = By.cssSelector("input[data-qa=\"login-email\"]");
    private final By loginPasswordInput = By.cssSelector("input[type=\"password\"]");
    private final By loginButton = By.cssSelector("button[data-qa=\"login-button\"]");
    private final By errorLogin = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p");
    private final WebDriver driver;


    public P01_LoginSignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginSignupPage enterNameForSignup(String userName) {
        Utility.sendKey(driver, signupNameInput, userName);
        return this;
    }

    public P01_LoginSignupPage enterEmailForSignup(String email) {
        Utility.sendKey(driver, signupEmailInput, email);
        return this;
    }

    public P02_EnterAccountInformationPage clickOnSignupButton() {
        Utility.clickElement(driver, signupButton);
        return new P02_EnterAccountInformationPage(driver);
    }

    public P01_LoginSignupPage enterEmailForLogin(String Email) {
        Utility.sendKey(driver, loginEmailInput, Email);
        return this;
    }

    public P01_LoginSignupPage enterPasswordForLogin(String password) {
        Utility.sendKey(driver, loginPasswordInput, password);
        return this;
    }

    public P03_LoggedHomePage clickOnLoginButton() {
        Utility.clickElement(driver, loginButton);
        return new P03_LoggedHomePage(driver);
    }

    public WebElement getNewUserSignup() {
        return Utility.byToWebElement(newUserSignupHeader, driver);
    }

    public WebElement getLoginToUrAccount(WebDriver driver) {
        return Utility.byToWebElement(loginToUserAccountHeader, driver);
    }

    public P02_EnterAccountInformationPage fillCorrectSignup(String name, String email) {
        enterNameForSignup(name);
        enterEmailForSignup(email);
        clickOnSignupButton();
        return new P02_EnterAccountInformationPage(driver);
    }

    public P01_LoginSignupPage fillInCorrectSignup(String name, String email) {
        enterNameForSignup(name);
        enterEmailForSignup(email);
        clickOnSignupButton();
        return this;
    }

    public P03_LoggedHomePage fillCorrectAccount(String email, String password) {
        enterEmailForLogin(email);
        enterPasswordForLogin(password);
        clickOnLoginButton();
        return new P03_LoggedHomePage(driver);
    }

    public P02_EnterAccountInformationPage fillIncorrectAccount(String email, String password) {
        enterEmailForLogin(email);
        enterPasswordForLogin(password);
        clickOnLoginButton();
        return new P02_EnterAccountInformationPage(driver);
    }

    public WebElement getLoginError() {
        return Utility.byToWebElement(errorLogin, driver);
    }

    public WebElement getSignupError() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(errorEmailAlreadyExist));
    }

    public P03_LoggedHomePage registerForExistingAccount() throws FileNotFoundException {
        String name = DataUtils.getJsonData("existingUser", "name");
        String email = DataUtils.getJsonData("existingUser", "email");
        fillCorrectSignup(name, email)
                .fillAccountDetails().clickOnContinueButton();
        return new P03_LoggedHomePage(driver);

    }
}
