package org.example.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.example.core.config.Configuration;
import org.example.core.models.config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeOptions;

import java.nio.file.Path;
import java.util.Map;


@Slf4j
public class DriverFactory {
    private static final BrowserConfig config = Configuration.getBrowserConfig();
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static WebDriver create() {
        String name = config.getName().toLowerCase();
        try {
            switch (name.toUpperCase()) {
                case "CHROME":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    setupDownloadOptions(chromeOptions);
                    setupChromiumOptions(chromeOptions);
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "EDGE":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    setupChromiumOptions(edgeOptions);
//                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new Exception("Browser {} is not supported yet.");
            }
            driver.get().manage().deleteAllCookies();
            driver.get().manage().window().maximize();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        log.info("Starting browser in thread: " + Thread.currentThread().getId() + " and driver ref: " + driver);
        return driver.get();
    }

    public static void closeDriver() {
        log.info("Quitting driver in thread: " + Thread.currentThread().getId() + " and driver ref: " + driver);
        driver.get().quit();
        driver.remove();
    }

    public static <T extends ChromiumOptions<?>> T setupChromiumOptions(ChromiumOptions<T> options) {
        if (config.getHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");
        options.addArguments("window-size=" + config.getSize());
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        options.setAcceptInsecureCerts(config.getIgnoreSSL());
        return (T) options;
    }

    private static <T extends ChromiumOptions<?>> void setupDownloadOptions(T options) {
        String downloadPath = String.valueOf(Path.of(System.getProperty("user.dir"), System.getProperty("browser.downloads")));
        Map<String, Object> preferences = Map.of(
                "profile.default_content_settings.popups", 0,
                "profile.content_settings.pattern_pairs.*.multiple_automatic_downloads", 1,
                "profile.default_content_setting_values.automatic_downloads", 1,
                "download.prompt_for_downloadd", false,
                "download.default_directory", downloadPath
        );
        options.setExperimentalOption("prefs", preferences);
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("--safebrowsing-disable-extension-blacklist");
    }
}
