package com.automationexercise.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.ExtentReportManager;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class BaseClass {

    private static ThreadLocal<WebDriver> tlDriver =
        new ThreadLocal<WebDriver>();

    public static final Logger log =
        LogManager.getLogger(BaseClass.class);

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        ConfigReader.initConfig();
        log.info("Environment: " + ConfigReader.getEnvironment());
        log.info("Browser: " + browser);

        try {
        	URI gridUri = new URI(ConfigReader.getGridUrl());
        	URL gridUrl = gridUri.toURL();

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                tlDriver.set(new RemoteWebDriver(gridUrl, options));

            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                tlDriver.set(new RemoteWebDriver(gridUrl, options));

            } else if (browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                tlDriver.set(new RemoteWebDriver(gridUrl, options));
            }

        } catch (Exception e) {
            log.error("Grid connection failed: " + e.getMessage());
        }

        getDriver().manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(
                ConfigReader.getImplicitWait()));
        getDriver().manage().window().maximize();
        getDriver().get(ConfigReader.getBaseUrl());
        log.info("Browser opened on Grid: " + browser);

        ExtentReportManager.getInstance();
        log.info("Extent Report initialized!");
    }

    @AfterClass
    public void teardown() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
            log.info("Browser closed on Grid!");
        }
        if (ExtentReportManager.extent != null) {
            ExtentReportManager.extent.flush();
            log.info("Report saved!");
        }
    }
}