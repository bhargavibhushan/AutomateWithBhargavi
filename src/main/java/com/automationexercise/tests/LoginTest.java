package com.automationexercise.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseClass;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.ExcelUtils;
import com.automationexercise.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import com.automationexercise.utils.ScreenshotUtils;

import java.time.Duration;

public class LoginTest extends BaseClass {

    LoginPage loginPage;
    private static final Logger log = 
        LogManager.getLogger(LoginTest.class);

    @Test
    public void testLoginWithExcelData() throws IOException {
    	ExtentTest extentTest = ExtentReportManager
    		    .getInstance()
    		    .createTest("Login Data Driven Test",
    		        "Login test with multiple data from Excel");

        ExcelUtils.openExcel(
            "src/main/resources/testdata/logindata.xlsx",
            "Sheet1"
        );

        int rowCount = ExcelUtils.getRowCount();
        log.info("Total test rows: " + rowCount);
        extentTest.info("Total test rows: " + rowCount);

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(30));

        for (int i = 1; i <= rowCount; i++) {

            String email    = ExcelUtils.getCellData(i, 0);
            String password = ExcelUtils.getCellData(i, 1);
            String testcase = ExcelUtils.getCellData(i, 2);

            log.info("=============================");
            log.info("Running Test: " + testcase);
            log.info("Email: " + email);
            extentTest.info("Running: " + testcase + " | Email: " + email);
            
            //clear cookies and navigate fresh
            driver.manage().deleteAllCookies();
            driver.get("https://automationexercise.com/login");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='login-email']")
            ));

            loginPage = new LoginPage(driver);
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("login"),
                ExpectedConditions.urlContains("dashboard"),
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[@href='/logout']")
                )
            ));

            log.info("Completed: " + testcase);

            try {
                driver.findElement(
                    By.xpath("//a[@href='/logout']")).click();
                log.info("Logged out successfully");
                extentTest.pass("✅ Login PASSED: " + testcase);
            } catch (Exception e) {
                log.warn("Login failed as expected: " + testcase);

                // Take screenshot on failure
                String screenshotPath = ScreenshotUtils
                    .captureScreenshot(driver, testcase);

                // Attach screenshot to report
                extentTest.warning("⚠️ Login failed as expected: " + testcase);
                extentTest.addScreenCaptureFromPath(
                    "../" + screenshotPath, testcase);
            }
        }
        ExcelUtils.closeExcel();
        log.info("=============================");
        log.info("Data Driven Test COMPLETED!");
        extentTest.pass("✅ All test cases completed!");
    }
}