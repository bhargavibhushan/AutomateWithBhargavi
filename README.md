# 🚀 Selenium Hybrid Automation Framework

![Java](https://img.shields.io/badge/Java-JDK%2026-orange?style=for-the-badge&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.21.0-green?style=for-the-badge&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-blue?style=for-the-badge&logo=apachemaven)
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.1-purple?style=for-the-badge)

> A professional **Hybrid Test Automation Framework** built from scratch using Selenium Java — combining Page Object Model, Data Driven Testing, ExtentReports, Log4j logging and Screenshot on Failure.

---

## 📌 About This Project

This framework automates the AutomationExercise.com e-commerce application. covering login, registration, product search, cart and checkout test scenarios.
Built with a focus on clean architecture, reusability and maintainability, following industry best practices including Page Object Model, Data Driven Testing, structured logging and automated reporting.

---

## 🛠️ Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | JDK 26 | Programming Language |
| Selenium WebDriver | 4.21.0 | Browser Automation |
| TestNG | 7.10.2 | Test Execution Framework |
| Maven | Latest | Build & Dependency Management |
| Page Factory | Built-in | Page Object Model Implementation |
| Apache POI | 5.2.3 | Excel Data Driven Testing |
| ExtentReports | 5.1.1 | HTML Test Reporting |
| Log4j | 2.23.1 | Test Execution Logging |
| WebDriverManager | 5.8.0 | Automatic Driver Management |
| Git + GitHub | Latest | Version Control |

---

## 📁 Project Structure

```
SeleniumFramework/
│
├── src/main/java/
│   ├── com.automationexercise.base/
│   │   └── BaseClass.java          ← WebDriver setup & teardown
│   │
│   ├── com.automationexercise.pages/
│   │   └── LoginPage.java          ← Page Factory locators & methods
│   │
│   ├── com.automationexercise.tests/
│   │   └── LoginTest.java          ← Data Driven test cases
│   │
│   └── com.automationexercise.utils/
│       ├── ExcelUtils.java         ← Read Excel test data
│       ├── ExtentReportManager.java← HTML report configuration
│       └── ScreenshotUtils.java    ← Capture screenshots on failure
│
├── src/main/resources/
│   ├── log4j2.xml                  ← Log4j configuration
│   └── testdata/
│       └── logindata.xlsx          ← Test data file
│
├── reports/
│   └── AutomationReport.html       ← ExtentReports HTML output
│
├── screenshots/                    ← Screenshots on test failure
├── logs/
│   └── automation.log              ← Log4j log file
│
└── pom.xml                         ← Maven dependencies
```

---

## ✅ Test Cases Automated

### Login Module
| TC ID | Test Case | Type | Status |
|---|---|---|---|
| TC_001 | Login with valid credentials | Positive | ✅ Pass |
| TC_002 | Login with invalid email | Negative | ✅ Pass |
| TC_003 | Login with invalid password | Negative | ✅ Pass |

---

## 🔑 Framework Features

### 1. 📄 Page Object Model + Page Factory
Each web page has its own Java class. Locators use `@FindBy` annotations with `PageFactory.initElements()`.

```java
@FindBy(xpath = "//input[@data-qa='login-email']")
WebElement emailField;
```

### 2. 📊 Data Driven Testing
Test data is read from Excel using Apache POI. Same test runs with multiple data sets without code changes.

```java
ExcelUtils.openExcel("src/main/resources/testdata/logindata.xlsx", "Sheet1");
String email = ExcelUtils.getCellData(i, 0);
```

### 3. 📸 Screenshot on Failure
Automatically captures screenshot when a test fails and attaches it to the ExtentReport.

```java
String screenshotPath = ScreenshotUtils.captureScreenshot(driver, testcase);
extentTest.addScreenCaptureFromPath("../" + screenshotPath, testcase);
```

### 4. 📋 ExtentReports HTML Report
Beautiful dark-themed HTML report with timestamps, pass/fail/warning status and screenshots.

### 5. 📝 Log4j Logging
All test steps logged with timestamps to console and saved to `logs/automation.log` file.

```
11:14:41 INFO  - Browser opened successfully!
11:14:47 INFO  - Running Test: Valid Login
11:16:00 INFO  - Logged out successfully
```

### 6. ⏳ Explicit Wait
`WebDriverWait` with `ExpectedConditions` for reliable element detection.

```java
wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//input[@data-qa='login-email']")));
```

---

## 🚀 How to Run

### Prerequisites
- JDK 26 installed
- Eclipse IDE / IntelliJ
- Maven installed
- Chrome browser

### Steps

**1. Clone the repository:**
```bash
git clone https://github.com/bhargavibhushan/AutomateWithBhargavi.git
```

**2. Import in Eclipse:**
```
File → Import → Maven → Existing Maven Projects → Browse to folder
```

**3. Run tests:**
```bash
mvn test
```

Or right click `LoginTest.java` → Run As → TestNG Test

**4. View report:**
```
Open reports/AutomationReport.html in Chrome
```

---

## 📊 Sample Report

The ExtentReport shows:
- ✅ **PASS** — green for successful steps
- ⚠️ **WARNING** — yellow for expected failures
- ❌ **FAIL** — red for unexpected failures
- 📸 Screenshots attached for failed test cases
- ⏱️ Timestamp for every step

---

## 🗂️ Excel Test Data Format

| email | password | testcase |
|---|---|---|
| user@gmail.com | Test@123 | Valid Login |
| wrong@gmail.com | Test@123 | Invalid Email |
| user@gmail.com | wrongpass | Invalid Password |

---


## 👩‍💻 About Me

**Bhargavi**
Software Engineer | Bangalore

- 6+ years experience across software development, manual testing and enterprise system validation
- Strong foundation in manual testing with 
  hands-on automation using Selenium Java
- Experience testing government fund management 
  systems, e-commerce and HR applications
- Passionate about building clean, 
  maintainable test automation frameworks
---

## 📬 Contact

- **GitHub:** [bhargavibhushan](https://github.com/bhargavibhushan)
- **Email:** bhargavikotian999@gmail.com
- **Location:** Bangalore, India

---

⭐ **If you find this project useful, please give it a star!** ⭐
