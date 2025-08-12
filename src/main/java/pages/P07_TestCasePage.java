package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Utility;

public class P07_TestCasePage {


    private final By testCases = By.cssSelector("h2[class='title text-center'] b");


    private final WebDriver driver;

    public P07_TestCasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTestCases() {
        return Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(testCases));
    }
}
