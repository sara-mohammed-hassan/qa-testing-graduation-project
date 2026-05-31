package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Homepage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By username = By.cssSelector("span.username");
    private By productsCount = By.cssSelector(".products-found h3");
    private By sortDropdown = By.xpath("//select");
    private By productsList = By.cssSelector(".shelf-item");
    private By logoutBtn = By.id("signin");

    public Homepage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(username)).getText();
    }

    public String getProductsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsCount));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(productsCount, "0 Product(s) found.")
        ));
        return driver.findElement(productsCount).getText();
    }

    public List<Double> getProductPrices() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productsList, 0));
        return driver.findElements(By.cssSelector(".shelf-item__price .val b"))
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace(",", "")))
                .collect(Collectors.toList());
    }

    public int getProductsListSize() {
        return driver.findElements(productsList).size();
    }

    public void filterByVendor(String vendor) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsCount));

        WebElement checkbox = driver.findElement(
                By.xpath("//input[@value='" + vendor + "']")
        );
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(productsCount, "0 Product(s) found.")
        ));
        String countBefore = driver.findElement(productsCount).getText();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(productsCount, countBefore)
        ));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productsList, 0));
    }

    public void sortBy(String option) {
        List<Double> pricesBefore = getProductPrices();
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        driver.findElement(By.xpath("//option[@value='" + option + "']")).click();
        wait.until(driver -> !getProductPrices().equals(pricesBefore));
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }

    public void addProductToCart(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[@class='shelf-item__title' and text()='" + productName + "']/following-sibling::div[@class='shelf-item__buy-btn']")
        )).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".bag__quantity")
        ));
    }
}