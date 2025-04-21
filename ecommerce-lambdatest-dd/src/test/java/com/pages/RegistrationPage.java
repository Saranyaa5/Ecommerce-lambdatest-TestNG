package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage extends BasePage {
    
    public RegistrationPage(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
    }
    
    // Locators
    @FindBy(xpath = "//a[@class='icon-left both nav-link dropdown-toggle']")
    private WebElement myAccountDropdown;
    
    @FindBy(xpath = "//a[contains(@href,'register')]")
    private WebElement registerLink;
    
    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement registerPageTitle;
    
    @FindBy(id = "input-firstname")
    private WebElement firstNameField;
    
    @FindBy(id = "input-lastname")
    private WebElement lastNameField;
    
    @FindBy(id = "input-email")
    private WebElement emailField;
    
    @FindBy(id = "input-telephone")
    private WebElement telephoneField;
    
    @FindBy(id = "input-password")
    private WebElement passwordField;
    
    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordField;
    
    @FindBy(id = "input-newsletter-yes")
    private WebElement newsletterYesRadio;
    
    @FindBy(id = "input-newsletter-no")
    private WebElement newsletterNoRadio;
    
    @FindBy(name = "agree")
    private WebElement privacyPolicyCheckbox;
    
    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;
    
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement successMessage;
    
    @FindBy(className = "alert-danger")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//div[@class='col-sm-10']//div[@class='text-danger'][1]")
    private WebElement firstNameError;
    
    @FindBy(xpath = "//div[@id='content']/form/fieldset[2]/div[1]/div/div")
    private WebElement passwordError;
    
    @FindBy(xpath = "//div[@id='content']/form/fieldset[2]/div[2]/div/div")
    private WebElement passwordMismatchError;
    
    @FindBy(xpath = "//div[@id='account-register']/div[1]")
    private WebElement privacyPolicyError;
    
    // Page actions
    public void navigateToRegistrationPage() {
        hoverOverElement(myAccountDropdown);
        clickElement(registerLink);
        waitForPageTitle("Register Account");
    }
    
    public boolean isRegistrationPageDisplayed() {
        return waitForElementVisible(registerPageTitle).isDisplayed();
    }
    
    public void enterRegistrationDetails(String firstName, String lastName, String email, 
                                      String telephone, String password, String confirmPassword) {
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(emailField, email);
        enterText(telephoneField, telephone);
        enterText(passwordField, password);
        enterText(confirmPasswordField, confirmPassword);
    }
    
    public void selectNewsletterOption(boolean subscribe) {
        if(subscribe) {
            clickElement(newsletterYesRadio);
        } else {
            clickElement(newsletterNoRadio);
        }
    }
    
    public void acceptPrivacyPolicy() {
        clickElement(privacyPolicyCheckbox);
    }
    
    public void submitRegistration() {
        clickElement(continueButton);
    }
    
    // Verification methods
    public String getSuccessMessage() {
        return waitForElementVisible(successMessage).getText();
    }
    
    public String getErrorMessage() {
        return waitForElementVisible(errorMessage).getText();
    }
    
    public String getFirstNameError() {
        return waitForElementVisible(firstNameError).getText();
    }
    
    public String getPasswordError() {
        return waitForElementVisible(passwordError).getText();
    }
    
    public String getPasswordMismatchError() {
        return waitForElementVisible(passwordMismatchError).getText();
    }
    
    public String getPrivacyPolicyError() {
        return waitForElementVisible(privacyPolicyError).getText();
    }
    
    // Helper methods
    private WebElement waitForElementVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(element));
    }
    
    private void clickElement(WebElement element) {
        waitForElementVisible(element).click();
    }
    
    private void enterText(WebElement element, String text) {
        WebElement el = waitForElementVisible(element);
        el.clear();
        el.sendKeys(text);
    }
    
    private void hoverOverElement(WebElement element) {
        new org.openqa.selenium.interactions.Actions(driver)
            .moveToElement(waitForElementVisible(element))
            .perform();
    }
    
    private void waitForPageTitle(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.titleContains(title));
    }
}