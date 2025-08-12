package utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Utility {
    private final static String screenShotPath = "test_outputs/screenshots/";

    public static void clickElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }

    public static void sendKey(WebDriver driver, By locator, String data) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);
    }

    public static void getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebDriverWait generalWaitWithDuration(WebDriver driver, Duration duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", byToWebElement(locator, driver));
    }

    public static WebElement byToWebElement(By locator, WebDriver driver) {  // we used by to avoid still element reference exception
        return driver.findElement(locator);
    }


    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-ssa").format(new Date());
    }


    public static void takeScreenShot(WebDriver driver, String screenName) throws IOException {
        try {
            File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenDes = new File(screenShotPath + screenSrc + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenSrc, screenDes);
            Allure.addAttachment(screenName, Files.newInputStream(Path.of(screenDes.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "screenshot", type = "png")
    public static byte[] takeScreenShotSaveToAllureReportDirect(WebDriver driver, String screenShotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void selectFromDropDownByText(WebDriver driver, By locator, String text) {
        new Select(byToWebElement(locator, driver)).selectByVisibleText(text);
    }

    public static void selectFromDropDownByValue(WebDriver driver, By locator, String value) {
        new Select(byToWebElement(locator, driver)).selectByValue(value);
    }

    public static List<WebElement> byToWebElements(By locator, WebDriver driver) {
        return driver.findElements(locator);
    }

}
