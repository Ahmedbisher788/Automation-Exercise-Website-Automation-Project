package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BrowserManager {

    public static WebDriver doBrowserSetup() throws IOException {
        WebDriver driver = null;
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : DataUtils.getPropertyValue("environment", "browser.name");
        LogUtility.info(System.getProperty("browser"));

        if (browser.equalsIgnoreCase("Chrome")) {

            // String pathExtension = DataUtils.getPropertyValue("environment", "chrome.extension.adblock.path");
            System.setProperty("webdriver.chrome.silentOutput", "true"); //prevent print message in console from web driver
            ChromeOptions chromeOptions = new ChromeOptions();
            //  chromeOptions.addArguments("load-extension=" + pathExtension); //uBlock Origin

            // chromeOptions.addArguments("--headless");
            // xxx most important thing xxx
            chromeOptions.addArguments("--incognito"); // this is like guest and let u download.default_directory


            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory", "C:\\Users\\lenovo\\Downloads");
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.directory_upgrade", true);
            prefs.put("safebrowsing.enabled", true);
            prefs.put("safebrowsing.disable_download_protection", true);

            chromeOptions.setExperimentalOption("prefs", prefs);


            // Additional arguments
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-plugins");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-web-security");
            chromeOptions.addArguments("--allow-running-insecure-content");

            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("--guest");
            edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            driver = new EdgeDriver(edgeOptions);
        }
        return driver;
    }
}
