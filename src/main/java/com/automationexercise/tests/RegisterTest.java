package com.automationexercise.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.automationexercise.base.BaseClass;
import com.automationexercise.pages.RegisterPage;
import com.automationexercise.utils.ExcelUtils;
import com.automationexercise.utils.ExtentReportManager;
import com.automationexercise.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;

public class RegisterTest extends BaseClass {

    RegisterPage registerPage;
    private static final Logger log =
        LogManager.getLogger(RegisterTest.class);

    @Test
    public void testRegisterWithExcelData() throws IOException {

        ExtentTest extentTest = ExtentReportManager
            .getInstance()
            .createTest("Register Data Driven Test",
                "Registration test with multiple data from Excel");

        ExcelUtils.openExcel(
            "src/main/resources/testdata/logindata.xlsx",
            "RegisterData"
        );

        int rowCount = ExcelUtils.getRowCount();
        log.info("Total register test rows: " + rowCount);
        extentTest.info("Total test rows: " + rowCount);

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(30));

        for (int i = 1; i <= rowCount; i++) {

        	String name     = ExcelUtils.getCellData(i, 0);
        	String emailBase = ExcelUtils.getCellData(i, 1);
        	String timestamp = new java.text.SimpleDateFormat("HHmmss")
        	    .format(new java.util.Date());
        	String email = emailBase.replace("@", timestamp + "@");
        	String password = ExcelUtils.getCellData(i, 2);
        	String firstname=ExcelUtils.getCellData(i, 3);  
        	String lastname=ExcelUtils.getCellData(i, 4);
        	String address  = ExcelUtils.getCellData(i, 5);
        	String state    = ExcelUtils.getCellData(i, 6);
        	String city     = ExcelUtils.getCellData(i, 7);
        	String zipcode  = ExcelUtils.getCellData(i, 8);
        	String mobile   = ExcelUtils.getCellData(i, 9);
        	String testcase = ExcelUtils.getCellData(i, 10);

            log.info("=============================");
            log.info("Running Test: " + testcase);
            extentTest.info("Running: " + testcase + " | Email: " + email);

            // Clear cookies and go to login page
            driver.manage().deleteAllCookies();
            driver.get("https://automationexercise.com/login");

            // Wait for signup name field
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='signup-name']")
            ));

            registerPage = new RegisterPage(driver);

            // Enter name and email
            registerPage.enterName(name);
            registerPage.enterEmail(email);
            registerPage.clickSignupButton();

            try {
                // Wait for password field on next page
            	wait.until(ExpectedConditions.visibilityOfElementLocated(
            		    By.id("password")
            		));
                // Fill registration form
                registerPage = new RegisterPage(driver);
                registerPage.enterPassword(password);
                registerPage.enterNameFirstName(firstname);
                registerPage.enterNameLastName(lastname);
                registerPage.enterAddress(address);
                registerPage.enterState(state);
                registerPage.enterCity(city);
                registerPage.enterZipcode(zipcode);
                registerPage.enterMobile(mobile);
                registerPage.clickCreateAccount();

                // Wait for account created confirmation
              /*  wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[@data-qa='account-created']")
                ));

                log.info("Registration PASSED: " + testcase);
                extentTest.pass("✅ Registration PASSED: " + testcase);

                // Delete account after test
                driver.findElement(
                    By.xpath("//a[@href='/delete_account']")).click();
                log.info("Account deleted after test");
                extentTest.info("Account deleted successfully");*/
                
             // Wait for account created confirmation
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[@data-qa='account-created']")
                ));

                log.info("Registration PASSED: " + testcase);
                extentTest.pass("✅ Registration PASSED: " + testcase);

                // Click continue button first
                try {
                    driver.findElement(
                        By.xpath("//a[@data-qa='continue-button']")).click();
                    log.info("Clicked continue button");
                } catch (Exception ex) {
                    log.info("No continue button found");
                }

                // Then delete account
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@href='/delete_account']")));
                    driver.findElement(
                        By.xpath("//a[@href='/delete_account']")).click();
                    log.info("Account deleted after test");
                    extentTest.info("Account deleted successfully");
                } catch (Exception ex) {
                    log.warn("Could not delete account: " + ex.getMessage());
                }

            } catch (Exception e) {
                log.warn("Registration failed: " + testcase);

                // Take screenshot on failure
                String screenshotPath = ScreenshotUtils
                    .captureScreenshot(driver, testcase);

                extentTest.warning("⚠️ Registration failed: " + testcase);
                extentTest.addScreenCaptureFromPath(
                    "../" + screenshotPath, testcase);
            }
        }

        ExcelUtils.closeExcel();
        log.info("Register Test COMPLETED!");
        extentTest.pass("✅ Register test completed!");
    }
}