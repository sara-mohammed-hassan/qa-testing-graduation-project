package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Favourites {
    private WebDriver driver;
    private WebDriverWait wait;

    private By shelfContainer = By.cssSelector(".shelf-container");
    private By allProducts = By.cssSelector(".shelf-item");
    private By productTitles = By.cssSelector(".shelf-item__title");
    private By productImages = By.cssSelector(".shelf-item__thumb img");
    private By productCount = By.cssSelector(".products-found h3");
    private By favouritesLink = By.id("favourites");

    public Favourites(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToHome() {
        driver.get("https://bstackdemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(shelfContainer));
    }

    public void addProductToFavouritesAndNavigate(String productName) {
        List<WebElement> items = driver.findElements(allProducts);
        for (WebElement item : items) {
            String title = item.findElement(By.cssSelector(".shelf-item__title")).getText();
            if (title.contains(productName)) {
                WebElement heartBtn = item.findElement(By.cssSelector(".shelf-stopper .Button"));
                if (!heartBtn.getAttribute("class").contains("clicked")) {
                    heartBtn.click();
                    wait.until(ExpectedConditions.attributeContains(heartBtn, "class", "clicked"));
                }
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(favouritesLink)).click();
        wait.until(ExpectedConditions.urlContains("favourites"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(shelfContainer));
    }

    public void navigateToFavourites() {
        wait.until(ExpectedConditions.elementToBeClickable(favouritesLink)).click();
        wait.until(ExpectedConditions.urlContains("favourites"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(shelfContainer));
    }

    public void refreshPage() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("main")));
    }

    public boolean isFavouritesPageLoaded() {
        return driver.getCurrentUrl().contains("favourites");
    }

    public int getProductsCount() {
        try {
            String text = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(productCount)).getText();
            return Integer.parseInt(text.split(" ")[0]);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<String> getAllProductTitles() {
        return driver.findElements(productTitles)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public boolean isProductPresent(String productName) {
        return getAllProductTitles().stream()
                .anyMatch(t -> t.contains(productName));
    }

    public int getProductImagesCount() {
        return driver.findElements(productImages).size();
    }

    public boolean isProductPricePresent(String price) {
        return driver.findElements(By.cssSelector(".shelf-item__price .val b"))
                .stream()
                .anyMatch(e -> e.getText().contains(price));
    }
}
