package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase2 {

    public static String BaseURL = "http://localhost:5000";
    protected ThreadLocal<RemoteWebDriver> driver;

    @BeforeClass
    @Parameters(value = {"browser"})
    public void setup(@Optional("chrome") String browser) {

        driver = new ThreadLocal<>();

        try {

            // لو البراوزر Chrome
            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), options));

            }
            // لو البراوزر Firefox
            else if (browser.equalsIgnoreCase("firefox")) {

                FirefoxOptions options = new FirefoxOptions();
                driver.set(new RemoteWebDriver(new URL("http://localhost:4444"), options));

            }
            // لو براوزر غير مدعوم
            else {
                throw new RuntimeException("Browser not supported: " + browser);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }

        // فتح الموقع
        driver.get().navigate().to(BaseURL);
    }

    // Getter عشان نستخدم الدرايفر في التستات
    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterClass
    public void stopDriver() {

        if (driver != null && driver.get() != null) {
            driver.get().quit();
        }

        if (driver != null) {
            driver.remove();
        }
    }
}