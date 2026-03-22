package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    private static Process chromedriverProcess = null;
    private static final int FALLBACK_PORT = 9515;

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browserName) throws Exception {

        if (!"chrome".equalsIgnoreCase(browserName))
            throw new IllegalArgumentException("Unsupported browser: " + browserName);

        WebDriverManager.chromedriver().setup();

        Path logsDir = Path.of(System.getProperty("user.dir"), "logs");
        try { Files.createDirectories(logsDir); } catch (IOException ignored) {}

        File driverExe = null;
        String drv = System.getProperty("webdriver.chrome.driver");
        if (drv != null && !drv.isEmpty()) driverExe = new File(drv);

        ChromeOptions options = new ChromeOptions();

        // مهم جداً 👇 يخلي البراوزر واضح
        options.addArguments("--start-maximized");
        options.addArguments("--window-position=0,0");
        options.addArguments("--window-size=1366,768");

        // stability options
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        options.setAcceptInsecureCerts(true);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + "downloads");
        options.setExperimentalOption("prefs", prefs);

        try {
            ChromeDriverService.Builder b = new ChromeDriverService.Builder()
                    .withLogFile(logsDir.resolve("chromedriver.log").toFile())
                    .usingAnyFreePort();

            if (driverExe != null && driverExe.exists())
                b.usingDriverExecutable(driverExe);

            ChromeDriverService svc = b.build();
            svc.start();

            driver = new ChromeDriver(svc, options);

        } catch (Exception e) {

            if (driverExe == null || !driverExe.exists())
                throw new RuntimeException("No chromedriver executable for fallback", e);

            File fallbackLog = logsDir.resolve("chromedriver_fallback.log").toFile();

            String[] cmd = {
                    driverExe.getAbsolutePath(),
                    "--port=" + FALLBACK_PORT,
                    "--verbose",
                    "--log-path=" + fallbackLog.getAbsolutePath()
            };

            chromedriverProcess = new ProcessBuilder(cmd)
                    .redirectErrorStream(true)
                    .start();

            URL status = new URL("http://localhost:" + FALLBACK_PORT + "/status");

            boolean up = false;

            for (int i = 0; i < 20; i++) {
                try {
                    HttpURLConnection c = (HttpURLConnection) status.openConnection();
                    c.setConnectTimeout(1000);
                    c.setReadTimeout(1000);
                    c.setRequestMethod("GET");

                    if (c.getResponseCode() == 200) {
                        up = true;
                        break;
                    }

                } catch (IOException ignored) {}

                Thread.sleep(500);
            }

            if (!up) {
                String out = new String(chromedriverProcess.getInputStream().readAllBytes());
                throw new RuntimeException("Fallback chromedriver failed: " + out, e);
            }

            driver = new RemoteWebDriver(
                    new URL("http://localhost:" + FALLBACK_PORT), options);
   
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://localhost:5000");

        // delay optional
        String delayStr = System.getProperty("visual.delay", "0");
        try {
            long delay = Long.parseLong(delayStr);
            if (delay > 0) {
                System.out.println("[TestBase] visual.delay=" + delay);
                Thread.sleep(delay);
            }
        } catch (NumberFormatException ignored) {}
    }
    }
    

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
            driver = null;
        }

        if (chromedriverProcess != null) {
            try {
                chromedriverProcess.destroy();
                chromedriverProcess.waitFor(2, TimeUnit.SECONDS);
            } catch (Exception ignored) {}
            chromedriverProcess = null;
        }
    }
}