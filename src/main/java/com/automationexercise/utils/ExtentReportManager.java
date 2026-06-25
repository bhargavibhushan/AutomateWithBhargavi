package com.automationexercise.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // Report will be saved here
            ExtentSparkReporter spark = new ExtentSparkReporter(
                "reports/AutomationReport.html");

            // Report settings
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle(
                "Automation Test Report");
            spark.config().setReportName(
                "Selenium Hybrid Framework - Login Tests");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info shown in report
            extent.setSystemInfo("Tester", "Bhargavi");
            extent.setSystemInfo("Application", 
                "AutomationExercise.com");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Framework", 
                "Selenium Hybrid Framework");
        }

        return extent;
    }
}