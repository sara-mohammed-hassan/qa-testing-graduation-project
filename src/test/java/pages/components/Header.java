package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;
    private WebDriverWait wait;

    private By navbar = By.cssSelector(".Navbar_root__2kbI9");
    private By logo = By.cssSelector(".Navbar_logo__image__3Blki");
    private By favouritesLink = By.id("favourites");
    private By ordersLink = By.id("orders");
    private By offersLink = By.id("offers");
    private By username = By.cssSelector("span.username");
    private By logoutBtn = By.id("signin");

    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(navbar)).isDisplayed();
    }

    public String getUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(username)).getText();
    }

    public void goToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    public void goToFavourites() {
        wait.until(ExpectedConditions.elementToBeClickable(favouritesLink)).click();
    }

    public void goToOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink)).click();
    }

    public void goToOffers() {
        wait.until(ExpectedConditions.elementToBeClickable(offersLink)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}
