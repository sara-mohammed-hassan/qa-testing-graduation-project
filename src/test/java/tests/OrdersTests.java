package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Orders;

public class OrdersTests extends BaseTest {

    private Orders ordersPage;


    @BeforeMethod
    public void placeOrderAndGoToOrders() {
        placeOrder();
        ordersPage = new Orders(driver);
        ordersPage.navigateToOrders();
    }


    @Test
    public void testOrdersUrl() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("orders"),
                "URL should contain '/orders'"
        );
    }

    @Test
    public void testOrdersCountGreaterThanZero() {
        Assert.assertTrue(
                ordersPage.getOrdersCount() > 0,
                "Orders count should be greater than 0"
        );
    }

    @Test
    public void testNewOrderProductIsVisible() {
        Assert.assertTrue(
                ordersPage.isOrderWithProductPresent("iPhone 12"),
                "iPhone 12 order should be present"
        );
    }

    @Test
    public void testNewOrderTotalIsVisible() {
        Assert.assertTrue(
                ordersPage.isOrderWithTotalPresent("$799"),
                "Order with total $799 should be present"
        );
    }

    @Test
    public void testProductImagesAreDisplayed() {
        Assert.assertTrue(
                ordersPage.getProductImagesCount() > 0,
                "Product images should be displayed"
        );
    }

    @Test
    public void testOrdersHaveDeliveredStatus() {
        Assert.assertFalse(
                ordersPage.getDeliveredStatuses().isEmpty(),
                "Orders should have 'Delivered' status"
        );
    }


    @Test
    public void testOrderPersistsAfterRelogin() {
        int countBefore = ordersPage.getOrdersCount();
        System.out.println("TC08 – Before logout: " + countBefore);

        header.logout();
        loginPage.login("demouser", "testingisfun99");
        ordersPage.navigateToOrders();

        int countAfter = ordersPage.getOrdersCount();
        System.out.println("TC08 – After re-login: " + countAfter);

        Assert.assertEquals(
                countAfter, countBefore,
                "BUG: Orders should persist after logout/login but got "
                        + countAfter + " instead of " + countBefore
        );
    }

    @Test
    public void testOrderProductVisibleAfterRelogin() {
        System.out.println("TC09 – Before logout: iPhone 12 present = "
                + ordersPage.isOrderWithProductPresent("iPhone 12"));

        header.logout();
        loginPage.login("demouser", "testingisfun99");
        ordersPage.navigateToOrders();

        boolean visibleAfter = ordersPage.isOrderWithProductPresent("iPhone 12");
        System.out.println("TC09 – After re-login: iPhone 12 present = " + visibleAfter);

        Assert.assertTrue(
                visibleAfter,
                "BUG: iPhone 12 should still be visible after logout/login"
        );
    }

    @Test
    public void testOrderTotalVisibleAfterRelogin() {
        System.out.println("TC10 – Before logout: $799 present = "
                + ordersPage.isOrderWithTotalPresent("$799"));

        header.logout();
        loginPage.login("demouser", "testingisfun99");
        ordersPage.navigateToOrders();

        boolean visibleAfter = ordersPage.isOrderWithTotalPresent("$799");
        System.out.println("TC10 – After re-login: $799 present = " + visibleAfter);

        Assert.assertTrue(
                visibleAfter,
                "BUG: Order total $799 should still be visible after logout/login"
        );
    }
}