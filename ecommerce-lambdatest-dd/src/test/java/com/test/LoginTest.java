package com.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pages.*;
import com.util.*;
@Listeners(com.util.ListenerClass.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginDetails", dataProviderClass =com.util.DataProviderClass.class)
    public void loginValidation(String email, String password, String check) {
        LoginPage lp = new LoginPage(getDriver());

        lp.clickMyAccount();
        lp.clickLogin();
        lp.loginUser(email, password);
        
       
        if (check.equalsIgnoreCase("check1") || check.equalsIgnoreCase("check2") ||
            check.equalsIgnoreCase("check3") || check.equalsIgnoreCase("check4")) {
            Assert.assertTrue(lp.isWarningDisplayed());
        } else if (check.equalsIgnoreCase("success")) {
            Assert.assertTrue(lp.isMyAccountDisplayed());
            lp.clickLogout();
            Assert.assertEquals(lp.getLogoutText(), "Account Logout");
        } else {
            Assert.fail("Unknown test case identifier: " + check);
        }
    }
}