package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class P00_HomePage {
    private final WebDriver driver;
    private final By automationExerciseImg = By.cssSelector("img[src=\"/static/images/home/logo.png\"]");


    private final By homeButton = By.cssSelector("li>a[href=\"/\"]");

    private final By productsButton = By.cssSelector("li>a[href=\"/products\"]");

    private final By cartButton = By.cssSelector("li>a[href=\"/view_cart\"]");

    private final By signupLoginButton = By.cssSelector("li>a[href=\"/login\"]");

    private final By testCasesButton = By.cssSelector("li>a[href=\"/test_cases\"]");

    private final By apiTestingButton = By.cssSelector("li>a[href=\"/api_list\"]");

    private final By videoTutorialButton = By.cssSelector("li>a[href=\"https://www.youtube.com/c/AutomationExercise\"]");

    private final By contactUsButton = By.cssSelector("li>a[href=\"/contact_us\"]");

    private final By girlImageResponsive = By.cssSelector("div[class='item active'] img[alt=\"demo website for practice\"]");

    private final By categories = By.id("accordian");

    private final By womanCategory = By.xpath("//*[@id='accordian']/div[1]/div[1]/h4/a/span/i");

    private final By dressCategory = By.cssSelector("a[href='/category_products/1']");

    private final By recommendedItem = By.cssSelector("div[class='recommended_items'] h2");

    private final By titleTextCenter = By.cssSelector(".title.text-center");

    private final By addToCarButtonForRecommendedItem1 = By.cssSelector(".recommended_items a[data-product-id=\"1\"]");

    private final By rightButtonForRecommendedList = By.xpath("//a[@href=\"#recommended-item-carousel\"][2]");

    private final By viewCartLink = By.xpath("//p/a[@href=\"/view_cart\"]");

    private final By scrollUpButton = By.id("scrollUp");

    private final By subscriptionTitle = By.cssSelector("div[class=\"single-widget\"] >h2");


// TODO: 7/21/2025 write locator for residual page  & method


    public P00_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public P00_HomePage navigateToHomePage(WebDriver driver, By locator) {
        Utility.clickElement(driver, homeButton);
        return this;
    }

    // TODO: class and method and control flow
//  public CartPage navigateToProductsPage(WebDriver driver, By locator){
//  Utility.clickElement(driver, productsButton);
//  return CartPage(driver); }

    public P01_LoginSignupPage navigateToSignupLoginPage() {
        Utility.clickElement(driver, signupLoginButton);
        return new P01_LoginSignupPage(driver);
    }

    public P06_ContactUsPage navigateToContactUsPage() {
        Utility.clickElement(driver, contactUsButton);
        return new P06_ContactUsPage(driver);
    }

    public WebElement homePageIsVisible(WebDriver driver) {
        return Utility.byToWebElement(girlImageResponsive, driver);
    }

    public P07_TestCasePage navigateToTestCasePage() {
        Utility.clickElement(driver, testCasesButton);
        return new P07_TestCasePage(driver);
    }

    public P08_ProductsPage navigateToProductPage() {
        Utility.clickElement(driver, productsButton);
        return new P08_ProductsPage(driver);
    }

    public WebElement getCategories() {
        return Utility.byToWebElement(categories, driver);
    }

    public P00_HomePage openWomanCategory() {
        Utility.clickElement(driver, womanCategory);
        return this;
    }

    public P00_HomePage clickOnDressCategory() {
        Utility.clickElement(driver, dressCategory);
        return this;
    }

    public WebElement getTitleTextCenter() {
        return Utility.byToWebElement(titleTextCenter, driver);
    }

    public WebElement getRecommendedTitleTextCenter() {
        Utility.scrolling(driver, recommendedItem);
        return Utility.byToWebElement(recommendedItem, driver);
    }

    public P00_HomePage cickOnScrollUpButton() {
        Utility.clickElement(driver, scrollUpButton);
        return this;
    }


    public P00_HomePage clickOnBlueTopFromRecommended() {
        Utility.scrolling(driver, addToCarButtonForRecommendedItem1);
        if (driver.findElement(addToCarButtonForRecommendedItem1).isDisplayed()) {
            Utility.clickElement(driver, addToCarButtonForRecommendedItem1);
        } else {
            Utility.clickElement(driver, rightButtonForRecommendedList);
            Utility.clickElement(driver, addToCarButtonForRecommendedItem1);
        }
        return this;
    }

    public P10_CartPage clickOnViewCartPage() {
        Utility.clickElement(driver, viewCartLink);
        return new P10_CartPage(driver);
    }

    public WebElement getSubscriptionTitle() {
        Utility.scrolling(driver, subscriptionTitle);
        return Utility.byToWebElement(subscriptionTitle, driver);
    }

    public WebElement getAutomationExerciseImg() {
        return Utility.byToWebElement(automationExerciseImg, driver);
    }
}


