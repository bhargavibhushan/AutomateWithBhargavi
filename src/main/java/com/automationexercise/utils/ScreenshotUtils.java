package com.automationexercise.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, 
            String testName) {

        // Create timestamp for unique filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
            .format(new Date());

        // Screenshot file name
        String fileName = testName + "_" + timestamp + ".png";

        // Screenshot save path
        String filePath = "screenshots/" + fileName;

        try {
            // Take screenshot
            File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

            // Save to screenshots folder
            FileUtils.copyFile(src, new File(filePath));

            System.out.println("Screenshot saved: " + filePath);

        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }

        return filePath;
    }
}