package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Checkout {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("firstNameInput");
    private By lastNameInput = By.id("lastNameInput");
    private By addressInput = By.id("addressLine1Input");
    private By stateInput = By.id("provinceInput");
    private By postalCodeInput = By.id("postCodeInput");
    private By submitBtn = By.id("checkout-shipping-continue");
    private By orderSummary = By.cssSelector("article.cart");
    private By orderItems = By.cssSelector(".productList-item");
    private By orderTotal = By.cssSelector(".cart-priceItem--total .cart-priceItem-value span");

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void fillForm(String firstName, String lastName, String address,
                         String state, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stateInput).clear();
        driver.findElement(stateInput).sendKeys(state);
        driver.findElement(postalCodeInput).clear();
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public boolean isOrderSummaryDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummary)).isDisplayed();
    }

    public List<String> getOrderItemNames() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(orderItems, 0));
        return driver.findElements(By.cssSelector(".product-title"))
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public String getOrderTotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderTotal)).getText();
    }

    public boolean isFirstNameValidationShown() {
        return driver.findElement(firstNameInput)
                .getAttribute("validationMessage") != null &&
                !driver.findElement(firstNameInput)
                        .getAttribute("validationMessage").isEmpty();
    }

    public boolean isOnConfirmationPage() {
        return driver.getCurrentUrl().contains("confirmation") ||
                driver.getCurrentUrl().contains("checkout");
    }
}
