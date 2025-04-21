package com.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pages.RegistrationPage;
import com.util.DataProviderClass;

public class RegistrationTest extends BaseTest {
    
    private RegistrationPage registrationPage;
    
    @BeforeMethod
    public void setUp() {
        registrationPage = new RegistrationPage(getDriver());
        registrationPage.navigateToRegistrationPage();
    }
    
    @Test(priority = 1, description = "Verify registration page elements")
    public void verifyRegistrationPageElements() {
        Assert.assertTrue(registrationPage.isRegistrationPageDisplayed(), 
            "Registration page is not displayed");
    }
    
    @Test(priority = 2, dataProvider = "validRegistrationData", dataProviderClass = DataProviderClass.class,
          description = "Test valid registration scenarios")
    public void testValidRegistration(String firstName, String lastName, String email, 
                                   String telephone, String password, String confirmPassword, 
                                   boolean subscribeNewsletter) {
        // Generate unique email if empty
        String testEmail = email.isEmpty() ? "test" + System.currentTimeMillis() + "@example.com" : email;
        
        registrationPage.enterRegistrationDetails(firstName, lastName, testEmail, 
                                               telephone, password, confirmPassword);
        registrationPage.selectNewsletterOption(subscribeNewsletter);
        registrationPage.acceptPrivacyPolicy();
        registrationPage.submitRegistration();
        
        Assert.assertEquals(registrationPage.getSuccessMessage(), 
                          "Your Account Has Been Created!",
                          "Registration was not successful");
    }
    
    @Test(priority = 3, dataProvider = "invalidRegistrationData", dataProviderClass = DataProviderClass.class,
          description = "Test invalid registration scenarios")
    public void testInvalidRegistration(String firstName, String lastName, String email, 
                                     String telephone, String password, String confirmPassword, 
                                     boolean acceptPolicy, String expectedError, String errorType) {
        registrationPage.enterRegistrationDetails(firstName, lastName, email, 
                                               telephone, password, confirmPassword);
        
        if(acceptPolicy) {
            registrationPage.acceptPrivacyPolicy();
        }
        
        registrationPage.submitRegistration();
        
        String actualError = "";
        switch(errorType) {
            case "firstName":
                actualError = registrationPage.getFirstNameError();
                break;
            case "password":
                actualError = registrationPage.getPasswordError();
                break;
            case "passwordMismatch":
                actualError = registrationPage.getPasswordMismatchError();
                break;
            case "privacyPolicy":
                actualError = registrationPage.getPrivacyPolicyError();
                break;
            default:
                actualError = registrationPage.getErrorMessage();
        }
        
        Assert.assertTrue(actualError.contains(expectedError), 
                         String.format("Expected error '%s' but got '%s'", expectedError, actualError));
    }
    
    @Test(priority = 4, description = "Test registration without accepting privacy policy")
    public void testRegistrationWithoutPrivacyPolicy() {
        registrationPage.enterRegistrationDetails("Test", "User", "test@example.com", 
                                               "1234567890", "password", "password");
        registrationPage.submitRegistration();
        
        Assert.assertTrue(registrationPage.getPrivacyPolicyError().contains("Privacy Policy"),
                        "Privacy policy error not displayed");
    }
}