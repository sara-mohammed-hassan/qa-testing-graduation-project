package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Orders {
    private WebDriver driver;
    private WebDriverWait wait;

    private By ordersContainer = By.cssSelector(".orders-listing");
    private By allOrders = By.cssSelector(".a-box-group.order");
    private By orderTotals = By.cssSelector(".a-column.a-span2 .value");

    private By deliveredLabels = By.cssSelector(".a-size-medium.a-color-base.a-text-bold");
    private By productTitles = By.cssSelector(".a-fixed-left-grid-col.a-col-right .a-row:first-child");

    private By productImages = By.cssSelector(".item-image");

    public Orders(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void navigateToOrders() {
        driver.get("https://bstackdemo.com/orders");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ordersContainer));
    }


    public int getOrdersCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ordersContainer));
        return driver.findElements(allOrders).size();
    }

    public List<String> getAllOrderTotals() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allOrders, 0));
        return driver.findElements(orderTotals)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public List<String> getAllProductTitles() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(allOrders, 0));
        return driver.findElements(productTitles)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public boolean isOrderWithTotalPresent(String total) {
        return getAllOrderTotals().stream()
                .anyMatch(t -> t.contains(total));
    }

    public boolean isOrderWithProductPresent(String productName) {
        return getAllProductTitles().stream()
                .anyMatch(t -> t.contains(productName));
    }

    public int getProductImagesCount() {
        return driver.findElements(productImages).size();
    }

    public List<String> getDeliveredStatuses() {
        return driver.findElements(deliveredLabels)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }
}
