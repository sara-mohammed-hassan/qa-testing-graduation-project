package tests;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HomePageTests extends BaseTest {

    @Test(priority = 1)
    public void verifyLoggedInUser() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertEquals(homePage.getUsername(), "demouser");
    }

    @Test(priority = 2)
    public void verifyProductsCount() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertEquals(
                homePage.getProductsCount(),
                "25 Product(s) found.",
                "Products count is different than expected!"
        );
    }

    @Test(priority = 3)
    public void verifyFilterByApple() {
        loginPage.login("demouser", "testingisfun99");
        homePage.filterByVendor("Apple");
        Assert.assertTrue(homePage.getProductsListSize() > 0,
                "No products found after filtering by Apple!");
    }

    @Test(priority = 4)
    public void verifySortByLowestPrice() {
        loginPage.login("demouser", "testingisfun99");
        homePage.sortBy("lowestprice");
        List<Double> prices = homePage.getProductPrices();
        Assert.assertEquals(prices,
                prices.stream().sorted().collect(Collectors.toList()),
                "Products are not sorted lowest to highest!");
    }

    @Test(priority = 5)
    public void verifyLogout() {
        loginPage.login("demouser", "testingisfun99");
        homePage.logout();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBe(By.id("signin"), "Sign In"));
        Assert.assertEquals(driver.findElement(By.id("signin")).getText(), "Sign In");
    }

    @Test(priority = 6)
    public void verifyFilterByAppleCount() {
        loginPage.login("demouser", "testingisfun99");
        homePage.filterByVendor("Apple");
        Assert.assertEquals(homePage.getProductsCount(),
                "9 Product(s) found.",
                "Apple products count is wrong!");
    }

    @Test(priority = 7)
    public void verifyFilterBySamsungCount() {
        loginPage.login("demouser", "testingisfun99");
        homePage.filterByVendor("Samsung");
        Assert.assertEquals(homePage.getProductsCount(),
                "7 Product(s) found.",
                "Samsung products count is wrong!");
    }

    @Test(priority = 8)
    public void verifyFilterByGoogleCount() {
        loginPage.login("demouser", "testingisfun99");
        homePage.filterByVendor("Google");
        Assert.assertEquals(homePage.getProductsCount(),
                "3 Product(s) found.",
                "Google products count is wrong!");
    }

    @Test(priority = 9)
    public void verifyFilterByOnePlusCount() {
        loginPage.login("demouser", "testingisfun99");
        homePage.filterByVendor("OnePlus");
        Assert.assertEquals(homePage.getProductsCount(),
                "6 Product(s) found.",
                "OnePlus products count is wrong!");
    }

    @Test(priority = 10)
    public void verifySortLowestToHighest() {
        loginPage.login("demouser", "testingisfun99");
        homePage.sortBy("lowestprice");
        List<Double> prices = homePage.getProductPrices();
        Assert.assertEquals(prices,
                prices.stream().sorted().collect(Collectors.toList()),
                "Products are not sorted lowest to highest!");
    }

    @Test(priority = 11)
    public void verifySortHighestToLowest() {
        loginPage.login("demouser", "testingisfun99");
        homePage.sortBy("highestprice");
        List<Double> prices = homePage.getProductPrices();
        Assert.assertEquals(prices,
                prices.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                "Products are not sorted highest to lowest!");
    }
}