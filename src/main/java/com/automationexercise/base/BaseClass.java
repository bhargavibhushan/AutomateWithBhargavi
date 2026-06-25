
package com.automationexercise.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseClass {

    public WebDriver driver;
    public static final Logger log =
        LogManager.getLogger(BaseClass.class);

    @BeforeSuite
    public void setup() {

        // Load config file first
        ConfigReader.initConfig();
        log.info("Config loaded — Environment: "
            + ConfigReader.getEnvironment());

        // Setup browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Use wait values from config
        driver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(
                ConfigReader.getImplicitWait()));

        driver.manage().window().maximize();

        // Use baseUrl from config
        driver.get(ConfigReader.getBaseUrl());
        log.info("Browser opened: " + ConfigReader.getBaseUrl());

        // Initialize Extent Report
        ExtentReportManager.getInstance();
        log.info("Extent Report initialized!");
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed successfully!");
        }
        if (ExtentReportManager.extent != null) {
            ExtentReportManager.extent.flush();
            log.info("Report saved to reports/AutomationReport.html");
        }
    }
}







/*package com.automationexercise.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.automationexercise.utils.ExtentReportManager;


public class BaseClass {

    public WebDriver driver;
    public static final Logger log = 
            LogManager.getLogger(BaseClass.class);

    @BeforeSuite
    public void setup() {
    	
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(
        	    java.time.Duration.ofSeconds(10)
        	);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        log.info("Browser opened successfully!");
        log.info("Navigated to automationexercise.com");
        ExtentReportManager.getInstance();
        log.info("Extent Report initialized!");
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed successfully!");
            if (ExtentReportManager.extent != null) {
                ExtentReportManager.extent.flush();
                log.info("Report saved to reports/AutomationReport.html");
            }
        }
    }
}*/