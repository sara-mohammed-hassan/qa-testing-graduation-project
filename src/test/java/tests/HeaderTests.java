package tests;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HeaderTests extends BaseTest {

    @Test(priority = 1)
    public void verifyHeaderIsDisplayed() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertTrue(header.isDisplayed(),
                "Header is not displayed!");
    }

    @Test(priority = 2)
    public void verifyUsernameInHeader() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertEquals(header.getUsername(), "demouser",
                "Username in header is wrong!");
    }

    @Test(priority = 3)
    public void verifyLogoNavigatesToHome() {
        loginPage.login("demouser", "testingisfun99");
        header.goToHome();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://bstackdemo.com/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://bstackdemo.com/",
                "Logo did not navigate to home!");
    }

    @Test(priority = 4)
    public void verifyFavouritesNavigation() {
        loginPage.login("demouser", "testingisfun99");
        header.goToFavourites();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("favourites"));
        Assert.assertTrue(driver.getCurrentUrl().contains("favourites"),
                "Favourites page did not load!");
    }

    @Test(priority = 5)
    public void verifyOrdersNavigation() {
        loginPage.login("demouser", "testingisfun99");
        header.goToOrders();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("orders"));
        Assert.assertTrue(driver.getCurrentUrl().contains("orders"),
                "Orders page did not load!");
    }

    @Test(priority = 6)
    public void verifyOffersNavigation() {
        loginPage.login("demouser", "testingisfun99");
        header.goToOffers();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("offers"));
        Assert.assertTrue(driver.getCurrentUrl().contains("offers"),
                "Offers page did not load!");
    }

    @Test(priority = 7)
    public void verifyLogout() {
        loginPage.login("demouser", "testingisfun99");
        header.logout();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(By.id("signin"), "Sign In"));
        Assert.assertEquals(driver.findElement(By.id("signin")).getText(), "Sign In",
                "Logout failed!");
    }
}
