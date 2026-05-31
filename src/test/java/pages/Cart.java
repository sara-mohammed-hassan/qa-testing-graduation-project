package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private WebDriver driver;
    private WebDriverWait wait;

    private By cartIcon = By.cssSelector(".bag__quantity");
    private By cartContent = By.cssSelector(".float-cart__content");
    private By cartItems = By.cssSelector(".float-cart__shelf-container .shelf-item");
    private By cartSubtotal = By.cssSelector(".sub-price__val");
    private By checkoutBtn = By.cssSelector(".buy-btn");
    private By itemTitle = By.cssSelector(".shelf-item__details .title");
    private By removeButtons = By.cssSelector(".shelf-item__del");
    private By cartQuantity = By.cssSelector(".bag__quantity");
    private By closeBtn = By.cssSelector(".float-cart__close-btn");
    private By emptyCartMsg = By.cssSelector(".shelf-empty");
    private By cartOpen = By.cssSelector(".float-cart--open");

    public Cart(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartContent));
    }

    public void closeCart() {
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartOpen));
    }

    public boolean isCartDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartContent)).isDisplayed();
    }

    public boolean isCartClosed() {
        return driver.findElements(cartOpen).isEmpty();
    }

    public boolean isCartEmpty() {
        return !driver.findElements(emptyCartMsg).isEmpty();
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public int getCartQuantity() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantity)).getText()
        );
    }

    public String getSubtotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartSubtotal)).getText();
    }

    public String getCheckoutBtnText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn)).getText();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void removeItem(String productName) {
        List<org.openqa.selenium.WebElement> titles = driver.findElements(itemTitle);
        List<org.openqa.selenium.WebElement> removes = driver.findElements(removeButtons);
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).getText().equals(productName)) {
                removes.get(i).click();
                return;
            }
        }
    }

    public void removeAllItems() {
        while (!driver.findElements(removeButtons).isEmpty()) {
            driver.findElements(removeButtons).get(0).click();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

    public void increaseItemQuantity(String productName) {
        By increaseBtn = By.xpath("//p[@class='title' and text()='" + productName + "']/ancestor::div[@class='shelf-item']//button[text()='+']");
        wait.until(ExpectedConditions.elementToBeClickable(increaseBtn)).click();
    }

    public void decreaseItemQuantity(String productName) {
        By decreaseBtn = By.xpath("//p[@class='title' and text()='" + productName + "']/ancestor::div[@class='shelf-item']//button[text()='-']");
        wait.until(ExpectedConditions.elementToBeClickable(decreaseBtn)).click();
    }

    public List<String> getItemTitles() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cartItems, 0));
        return driver.findElements(itemTitle)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public int getCartCount() {
        String count = driver.findElement(By.className("bag__quantity"))
                .getText();
        return Integer.parseInt(count);
    }
}

