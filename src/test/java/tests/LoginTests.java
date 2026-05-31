package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(priority = 1)
    public void invalidUsername() {
        loginPage.loginWithPasswordOnly("testingisfun99");

        Assert.assertEquals(loginPage.getActualErrorMSG(), loginPage.getExpectedUsernameErrorMSG());
    }

    @Test(priority = 2)
    public void invalidPassword() {
        loginPage.loginWithUsernameOnly("demouser");

        Assert.assertEquals(loginPage.getActualErrorMSG(), loginPage.getExpectedPasswordErrorMSG());
    }

    @Test(priority = 3)
    public void validLogin() {
        String currentURL = driver.getCurrentUrl();

        if (currentURL.contains("signin")) {
            System.out.println("You are in the login page");
        } else {
            System.out.println("You are not in the login page");
        }

        loginPage.login("demouser", "testingisfun99");
    }
}

