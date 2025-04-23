package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
      super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // initialize the wait
    }

    @FindBy(xpath="//a[@class='icon-left both nav-link dropdown-toggle' and @href='https://ecommerce-playground.lambdatest.io/index.php?route=account/account']")
    public WebElement myAccount;

//    @FindBy(xpath="//*[@id=\"widget-navbar-217834\"]/ul/li[6]/a")
//    public WebElement login;
    @FindBy(xpath="(//a[@class='icon-left both nav-link dropdown-toggle'])[3]")
    public WebElement login;

    @FindBy(xpath="//*[@id=\"input-email\"]")
    public WebElement emailId;

    @FindBy(xpath="//*[@id=\"input-password\"]")
    public WebElement password;

    @FindBy(xpath="//input[@class='btn btn-primary']")
    public WebElement loginButton;

    @FindBy(xpath="//*[@id=\"content\"]/div[1]/h2")
    public WebElement titleMyAccount;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    public WebElement warningMessage;

    @FindBy(xpath="//*[@id=\"column-right\"]/div/a[14]")
    public WebElement logout;

    @FindBy(xpath="//*[@id=\"content\"]/h1")
    public WebElement accountLogout;

    public void clickMyAccount() {
        myAccount.click();
    }

    public void clickLogin() {
        login.click();
//        wait.until(ExpectedConditions.visibilityOf(emailId)); // ensure email field is visible
    }

    public void loginUser(String email, String pass) {
        wait.until(ExpectedConditions.visibilityOf(emailId));
        emailId.clear();
        emailId.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(password));
        password.clear();
        password.sendKeys(pass);

        loginButton.click();
    }

    public boolean isMyAccountDisplayed() {
        return titleMyAccount.isDisplayed();
    }

    public boolean isWarningDisplayed() {
        return warningMessage.isDisplayed();
    }

    public void clickLogout() {
        logout.click();
    }

    public String getLogoutText() {
        return accountLogout.getText();
    }
}
