package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Footer {
    private WebDriver driver;
    private WebDriverWait wait;

    private By footer = By.id("custom-footer");
    private By offersLink = By.cssSelector("a[href='/offers']");
    private By contactLink = By.cssSelector("a[href='/contact']");
    private By privacyLink = By.cssSelector("a[href='/privacy']");
    private By careersLink = By.cssSelector("a[href='/careers']");
    private By copyrightText = By.cssSelector("#custom-footer p");

    public Footer(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(footer)).isDisplayed();
    }

    public void goToOffers() {
        wait.until(ExpectedConditions.elementToBeClickable(offersLink)).click();
    }

    public void goToContact() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
    }

    public void goToPrivacy() {
        wait.until(ExpectedConditions.elementToBeClickable(privacyLink)).click();
    }

    public void goToCareers() {
        wait.until(ExpectedConditions.elementToBeClickable(careersLink)).click();
    }

    public String getCopyrightText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(copyrightText)).getText();
    }
}
