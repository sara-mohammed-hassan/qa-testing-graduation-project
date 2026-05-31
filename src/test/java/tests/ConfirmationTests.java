package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfirmationTests extends BaseTest {

    @BeforeMethod
    public void goToConfirmationPage() {
        placeOrder();
    }


    @Test
    public void testDownloadReceiptLinkIsVisible() {
        Assert.assertTrue(
                confirmation.isDownloadReceiptDisplayed(),
                "'Download order receipt' link should be displayed"
        );
    }

    @Test
    public void testClickDownloadReceiptStaysOnPage() {
        confirmation.clickDownloadReceipt();
        Assert.assertTrue(
                confirmation.getCurrentUrl().contains("confirmation"),
                "Should remain on confirmation page after clicking download"
        );
    }


    @Test
    public void testContinueShoppingIsVisible() {
        Assert.assertTrue(
                confirmation.isContinueShoppingDisplayed(),
                "'CONTINUE SHOPPING' button should be displayed"
        );
    }

    @Test
    public void testContinueShoppingNavigatesToHome() {
        confirmation.clickContinueShopping();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://bstackdemo.com/",
                "Should navigate back to Home Page"
        );
    }


    @Test
    public void testOrderSummaryIsDisplayed() {
        Assert.assertTrue(
                confirmation.isOrderSummaryDisplayed(),
                "Order Summary section should be displayed"
        );
    }

    @Test
    public void testOrderSummaryHeaderIsDisplayed() {
        Assert.assertTrue(
                confirmation.isOrderSummaryHeaderDisplayed(),
                "'Order Summary' header should be displayed"
        );
    }

    @Test
    public void testProductNameInSummary() {
        Assert.assertTrue(
                confirmation.getSummaryProductName().contains("iPhone 12"),
                "Expected 'iPhone 12' in Order Summary"
        );
    }

    @Test
    public void testBrandNameInSummary() {
        Assert.assertTrue(
                confirmation.getSummaryBrandName().contains("Apple"),
                "Expected 'Apple' in Order Summary"
        );
    }

    @Test
    public void testProductPriceInSummary() {
        Assert.assertTrue(
                confirmation.getSummaryProductPrice().contains("799"),
                "Expected price '799' in Order Summary"
        );
    }

    @Test
    public void testProductImageInSummary() {
        Assert.assertTrue(
                confirmation.isSummaryImageDisplayed(),
                "Product image should be displayed in Order Summary"
        );
    }

    @Test
    public void testItemCountInSummary() {
        Assert.assertTrue(
                confirmation.getSummaryItemCount().contains("1"),
                "Expected '1 item(s)' in Order Summary"
        );
    }

    @Test
    public void testOrderTotalInSummary() {
        Assert.assertTrue(
                confirmation.getOrderTotal().contains("799"),
                "Expected total '799' in Order Summary"
        );
    }

    @Test(description = "TC14 – الضغط على StackDemo logo بيوديني للـ Homepage")
    public void testClickStackDemoLogoNavigatesToHome() {
        confirmation.clickStackDemoLogo();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://bstackdemo.com/",
                "Clicking StackDemo logo should navigate to Homepage"
        );
    }
}