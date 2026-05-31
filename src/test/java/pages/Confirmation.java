package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Confirmation {
    private WebDriver driver;
    private WebDriverWait wait;
    private By successMessage = By.id("confirmation-message");
    private By downloadReceipt = By.id("downloadpdf");
    private By continueShopping = By.cssSelector(".button--tertiary");
    private By orderSummary = By.cssSelector("article.cart");
    private By summaryHeader = By.cssSelector(".cart-title");
    private By summaryItemCount = By.cssSelector(".cart-section-heading");
    private By summaryProduct = By.cssSelector(".product-title");
    private By summaryBrand = By.cssSelector(".product-option:first-child");
    private By summaryPrice = By.cssSelector(".product-price");
    private By summaryImage = By.cssSelector(".product-figure img");
    private By summaryTotal = By.cssSelector(".cart-priceItem--total .cart-priceItem-value span");
    private By stackDemoLogo = By.cssSelector("a.checkoutHeader-link");

    public Confirmation(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickDownloadReceipt() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadReceipt)).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShopping)).click();
        wait.until(ExpectedConditions.urlToBe("https://bstackdemo.com/"));
    }


    public boolean isDownloadReceiptDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(downloadReceipt)).isDisplayed();
    }

    public boolean isContinueShoppingDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(continueShopping)).isDisplayed();
    }

    public boolean isOrderSummaryDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummary)).isDisplayed();
    }

    public boolean isOrderSummaryHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryHeader)).isDisplayed();
    }

    public String getSummaryItemCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryItemCount));
        return driver.findElement(summaryItemCount).getText();
    }

    public String getSummaryProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryProduct)).getText();
    }

    public String getSummaryBrandName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryBrand)).getText();
    }

    public String getSummaryProductPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryPrice)).getText();
    }

    public boolean isSummaryImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryImage)).isDisplayed();
    }

    public String getOrderTotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTotal)).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickStackDemoLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(stackDemoLogo)).click();
        wait.until(ExpectedConditions.urlToBe("https://bstackdemo.com/"));
    }
}
