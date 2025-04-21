package com.util;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    
    @DataProvider(name = "validRegistrationData")
    public Object[][] getValidRegistrationData() {
        return new Object[][] {
            {"Priya", "Ram", "", "1234567891", "1234", "1234", true},
            {"Saran", "A", "", "1239306", "12345", "12345", false}
        };
    }
    
    @DataProvider(name = "invalidRegistrationData")
    public Object[][] getInvalidRegistrationData() {
        return new Object[][] {
            // FirstName, LastName, Email, Telephone, Password, ConfirmPassword, AcceptPolicy, ExpectedError, ErrorType
            {"", "ram", "priya123@gmail.com", "1234567891", "1234", "1234", true, "First Name must be between", "firstName"},
            {"priya", "ram", "oign@gma.com", "1234567891", "1234", "1234", true, "already registered", "general"},
            {"priya", "ram", "priya123@gmail.com", "1234567891", "", "1234", true, "Password must be between", "password"},
            {"priya", "ram", "priya123@gmail.com", "1234567891", "1234", "4321", true, "Password confirmation", "passwordMismatch"},
            {"priya", "ram", "priya123@gmail.com", "1234567891", "1234", "1234", false, "Privacy Policy", "privacyPolicy"}
        };
    }
}