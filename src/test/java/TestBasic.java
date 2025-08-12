import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import utilities.BrowserManager;
import utilities.DataUtils;

import java.io.IOException;
import java.time.Duration;

public class TestBasic {
    /**
     * This method returns the WebDriver instance associated with the current thread.
     * <p>
     * Since we are using ThreadLocal to support parallel test execution, each thread
     * will have its own separate WebDriver instance.
     * <p>
     * The use of 'synchronized' ensures that only one thread can access this method
     * at a time, preventing potential race conditions in multi-threaded test runs.
     * <p>
     * Usage: Call this method instead of directly accessing WebDriver in test classes
     * or page objects to ensure thread safety.
     */
    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {

        String url = DataUtils.getPropertyValue("environment", "Base_URL");
        WebDriver driver = BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //for wait loading all element in page

    }

    @AfterClass
    public void tearDown() {
        getDriver().quit();
        tdriver.remove(); // to avoid memory leak
    }
}
