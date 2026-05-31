package tests;

import baseTest.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTests extends BaseTest {

    @Test(priority = 1)
    public void verifyCartOpens() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        Assert.assertTrue(cart.isCartDisplayed(),
                "Cart did not open!");
    }

    @Test(priority = 2)
    public void verifyCartCloses() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        cart.closeCart();
        Assert.assertTrue(cart.isCartClosed(),
                "Cart should be closed!");
    }

    @Test(priority = 3)
    public void verifyProductAddedToCart() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        Assert.assertTrue(cart.getItemTitles().contains("Galaxy Note 20 Ultra"),
                "Product not found in cart!");
    }

    @Test(priority = 4)
    public void verifyCartQuantityUpdates() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        Assert.assertEquals(cart.getCartQuantity(), 1,
                "Cart quantity is wrong!");
    }

    @Test(priority = 5)
    public void verifyIncreaseQuantity() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        cart.increaseItemQuantity("Galaxy Note 20 Ultra");
        Assert.assertEquals(cart.getCartQuantity(), 2,
                "Quantity did not increase!");
    }

    @Test(priority = 6)
    public void verifyRemoveSpecificItem() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        homePage.addProductToCart("Galaxy S20+");
        cart.openCart();
        cart.removeItem("Galaxy Note 20 Ultra");
        Assert.assertFalse(cart.getItemTitles().contains("Galaxy Note 20 Ultra"),
                "Item was not removed!");
        Assert.assertTrue(cart.getItemTitles().contains("Galaxy S20+"),
                "Other item should still be in cart!");
    }

    @Test(priority = 7)
    public void verifyRemoveAllItems() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        homePage.addProductToCart("Galaxy S20+");
        cart.openCart();
        cart.removeAllItems();
        Assert.assertTrue(cart.isCartEmpty(),
                "Cart should be empty!");
        Assert.assertEquals(cart.getCartQuantity(), 0,
                "Cart quantity should be 0!");
    }

    @Test(priority = 8)
    public void verifySubtotalWhenCartEmpty() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        cart.removeAllItems();
        Assert.assertEquals(cart.getSubtotal(), "$ 0.00",
                "Subtotal should be $ 0.00!");
    }

    @Test(priority = 9)
    public void verifyContinueShoppingWhenCartEmpty() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        cart.removeAllItems();
        Assert.assertEquals(cart.getCheckoutBtnText(), "CONTINUE SHOPPING",
                "Button should say Continue Shopping!");
    }

    @Test(priority = 10)
    public void verifyCheckoutNavigation() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("Galaxy Note 20 Ultra");
        cart.openCart();
        cart.clickCheckout();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout"));
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"),
                "Checkout page did not load!");
    }
}
