package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static WebDriver driver;

    public static void InitializeDriver() {
        InitializeDriver(System.getProperty("browser"), Boolean.parseBoolean(System.getProperty("headless")), Boolean.parseBoolean(System.getProperty("windowMaximize")));
    }

    public static void InitializeDriver(String browser, boolean headless, boolean maximizeWindow) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2); // block images
        prefs.put("profile.managed_default_content_settings.ads", 2);    // block ads
        switch (browser.toLowerCase().trim()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", prefs);
                if (headless) chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.setExperimentalOption("prefs", prefs);
                if (headless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setExperimentalOption("prefs", prefs);
                if (headless) edgeOptions.addArguments("--headless=new");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        if (maximizeWindow) {
            driver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @Step("Quit the browser")
    public static void quitDriver() {
        driver.quit();
    }
}