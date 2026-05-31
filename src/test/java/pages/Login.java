package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameDropdown = By.id("username");
    private By passwordDropdown = By.id("password");
    private By usernameInput = By.cssSelector("#username input");
    private By passwordInput = By.cssSelector("#password input");
    private By loginBtn = By.id("login-btn");
    private By errorMsg = By.cssSelector(".api-error");

    private String expectedUrl = "https://bstackdemo.com/signin";
    private String expectedUsernameErrorMSG = "Invalid Username";
    private String expectedPasswordErrorMSG = "Invalid Password";

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getExpectedUsernameErrorMSG() {
        return expectedUsernameErrorMSG;
    }

    public String getExpectedPasswordErrorMSG() {
        return expectedPasswordErrorMSG;
    }

    public String getActualErrorMSG() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class,'option') and text()='" + username + "']")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(passwordDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(@class,'option') and text()='" + password + "']")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        wait.until(ExpectedConditions.urlContains("bstackdemo.com"));
    }

    public void loginWithPasswordOnly(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordDropdown)).click();
        driver.findElement(passwordInput).sendKeys(password + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void loginWithUsernameOnly(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameDropdown)).click();
        driver.findElement(usernameInput).sendKeys(username + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
}