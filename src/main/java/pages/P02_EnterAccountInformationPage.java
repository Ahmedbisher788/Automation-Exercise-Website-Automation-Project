package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DataUtils;
import utilities.Utility;

import java.io.FileNotFoundException;

public class P02_EnterAccountInformationPage {

    private final By enterAccountInfoHeadLine = By.cssSelector("div>h2>b");
    private final By mrRadioCheckBox = By.cssSelector("label input[value=\"Mr\"]");
    private final By mrsRadioCheckBox = By.id("#id_gender2");
    private final By nameInputField = By.id("#name");
    private final By emailInputField = By.id("#email");
    private final By passwordInputField = By.cssSelector("input[type=\"password\"]");
    private final By daysSelect = By.cssSelector(".selector>select[data-qa=\"days\"]");
    private final By monthsSelect = By.cssSelector(".selector>select[data-qa=\"months\"]");
    private final By yearsSelect = By.cssSelector(".selector>select[data-qa=\"years\"]");
    private final By specialOffersCheckbox = By.cssSelector("div.checker input[name=\"optin\"]");
    private final By newsletterCheckbox = By.cssSelector("div.checker input[name=\"newsletter\"]");
    private final By firstNameInputField = By.cssSelector("input[data-qa=\"first_name\"]");
    private final By lastNameInputField = By.cssSelector("input[data-qa=\"last_name\"]");
    private final By companyInputField = By.cssSelector("input[data-qa=\"company\"]");
    private final By address1InputField = By.cssSelector("input[data-qa=\"address\"]");
    private final By address2InputField = By.cssSelector("input[data-qa=\"address2\"]");
    private final By countryDropDownList = By.cssSelector("select[data-qa=\"country\"]");
    private final By stateInputField = By.cssSelector("input[data-qa=\"state\"]");
    private final By cityInputField = By.cssSelector("input[data-qa=\"city\"]");
    private final By zipcodeInputField = By.cssSelector("input[data-qa=\"zipcode\"]");
    private final By mobileNumberInputField = By.cssSelector("input[data-qa=\"mobile_number\"]");
    private final By createAccountButton = By.cssSelector("button[data-qa=\"create-account\"]");


    private final WebDriver driver;

    public P02_EnterAccountInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement enterAccountInformationText() {
        return Utility.byToWebElement(enterAccountInfoHeadLine, driver);
    }

    public P04_AccountCreated fillAccountDetails() throws FileNotFoundException {
        driver.findElement(mrRadioCheckBox).click();
        Utility.clickElement(driver, mrRadioCheckBox);
        //  Utility.scrolling(driver, passwordInputField);
        Utility.sendKey(driver, passwordInputField, DataUtils.getJsonData("AccountDetails", "password"));
        Utility.selectFromDropDownByText(driver, daysSelect, DataUtils.getJsonData("AccountDetails", "days"));
        Utility.selectFromDropDownByText(driver, monthsSelect, DataUtils.getJsonData("AccountDetails", "months"));
        Utility.selectFromDropDownByText(driver, yearsSelect, DataUtils.getJsonData("AccountDetails", "years"));
        // Utility.clickElement(driver, newsletterCheckbox);
        Utility.scrolling(driver, specialOffersCheckbox);
        Utility.clickElement(driver, specialOffersCheckbox);
        //must scrolling to view element
        Utility.scrolling(driver, firstNameInputField);
        Utility.sendKey(driver, firstNameInputField, DataUtils.getJsonData("AccountDetails", "first_name"));
        Utility.sendKey(driver, lastNameInputField, DataUtils.getJsonData("AccountDetails", "last_name"));
        Utility.sendKey(driver, address1InputField, DataUtils.getJsonData("AccountDetails", "address1"));
        Utility.sendKey(driver, address2InputField, DataUtils.getJsonData("AccountDetails", "address2"));
        Utility.selectFromDropDownByText(driver, countryDropDownList, DataUtils.getJsonData("AccountDetails", "country"));
        Utility.scrolling(driver, stateInputField);
        Utility.sendKey(driver, stateInputField, DataUtils.getJsonData("AccountDetails", "state"));
        Utility.sendKey(driver, cityInputField, DataUtils.getJsonData("AccountDetails", "city"));
        Utility.sendKey(driver, companyInputField, DataUtils.getJsonData("AccountDetails", "company"));
        Utility.sendKey(driver, zipcodeInputField, DataUtils.getJsonData("AccountDetails", "zipcode"));
        Utility.sendKey(driver, mobileNumberInputField, DataUtils.getJsonData("AccountDetails", "mobileNumber"));
        Utility.clickElement(driver, createAccountButton);
        return new P04_AccountCreated(driver);

        //h2[data-qa="account-created"]>b
    }
}
