package tests;

import baseTest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void addProductAndGoToCheckout() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("iPhone 12 Mini");
        cart.openCart();
        cart.clickCheckout();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout"));
    }

    @Test(priority = 1)
    public void verifyOrderSummaryDisplayed() {
        Assert.assertTrue(checkout.isOrderSummaryDisplayed(),
                "Order summary is not displayed!");
    }

    @Test(priority = 2)
    public void verifyOrderSummaryContainsProduct() {
        Assert.assertTrue(checkout.getOrderItemNames().contains("iPhone 12 Mini"),
                "Product not found in order summary!");
    }

    @Test(priority = 3)
    public void verifySubmitWithEmptyFields() {
        checkout.clickSubmit();
        Assert.assertTrue(checkout.isFirstNameValidationShown(),
                "Validation message not shown for empty first name!");
    }

    @Test(priority = 4)
    public void verifySubmitWithSpecialCharacters() {
        checkout.fillForm("@#$%!", "@#$%!", "123 Test St", "CA", "12345");
        checkout.clickSubmit();
        Assert.assertFalse(checkout.isOnConfirmationPage(),
                "BUG: Site accepted special characters in name fields!");
    }

    @Test(priority = 5)
    public void verifySubmitWithLettersInPostalCode() {
        checkout.fillForm("John", "Doe", "123 Test St", "CA", "ABCDE");
        checkout.clickSubmit();
        Assert.assertFalse(checkout.isOnConfirmationPage(),
                "BUG: Site accepted letters in postal code!");
    }

    @Test(priority = 6)
    public void verifySubmitWithSpacesOnly() {
        checkout.fillForm("   ", "   ", "   ", "   ", "   ");
        checkout.clickSubmit();
        Assert.assertFalse(checkout.isOnConfirmationPage(),
                "BUG: Site accepted spaces only in all fields!");
    }

    @Test(priority = 7)
    public void verifySubmitWithNumbersInName() {
        checkout.fillForm("12345", "67890", "123 Test St", "CA", "12345");
        checkout.clickSubmit();
        Assert.assertFalse(checkout.isOnConfirmationPage(),
                "BUG: Site accepted numbers in name fields!");
    }

    @Test(priority = 8)
    public void verifySuccessfulCheckout() {
        checkout.fillForm("John", "Doe", "123 Main St", "California", "12345");
        checkout.clickSubmit();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout"));
        Assert.assertTrue(checkout.isOnConfirmationPage(),
                "Checkout did not complete successfully!");
    }

    @Test(priority = 9)
    public void verifyOrderTotal() {
        Assert.assertFalse(checkout.getOrderTotal().isEmpty(),
                "Order total should not be empty!");
    }

    @Test(priority = 10)
    public void verifyCartIsEmptyAfterCheckout() {
        checkout.fillForm("John", "Doe", "123 Main St", "California", "12345");
        checkout.clickSubmit();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("confirmation"));

        driver.get("https://bstackdemo.com/");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.className("bag__quantity")));

        Assert.assertEquals(cart.getCartCount(), 0,
                "Cart should be empty after successful checkout!");
    }
}

