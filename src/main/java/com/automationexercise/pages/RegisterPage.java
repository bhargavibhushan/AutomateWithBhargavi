package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    // Locators
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement emailField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signupButton;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@data-qa='first_name']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@data-qa='last_name']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@data-qa='address']")
    WebElement addressField;

    @FindBy(xpath = "//select[@data-qa='country']")
    WebElement countryDropdown;

    @FindBy(xpath = "//input[@data-qa='state']")
    WebElement stateField;

    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement cityField;

    @FindBy(xpath = "//input[@data-qa='zipcode']")
    WebElement zipcodeField;

    @FindBy(xpath = "//input[@data-qa='mobile_number']")
    WebElement mobileField;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountButton;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void clickSignupButton() {
        signupButton.click();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void enterNameFirstName(String firstName) {
    	firstNameField.sendKeys(firstName);
    }
    public void enterNameLastName(String lastName) {
    	lastNameField.sendKeys(lastName);
    }

  
    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }

    public void enterState(String state) {
        stateField.sendKeys(state);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterZipcode(String zipcode) {
        zipcodeField.sendKeys(zipcode);
    }

    public void enterMobile(String mobile) {
        mobileField.sendKeys(mobile);
    }

    public void clickCreateAccount() {
        createAccountButton.click();
    }
}