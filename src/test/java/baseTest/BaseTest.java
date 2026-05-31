package baseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import pages.components.Footer;
import pages.components.Header;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected Login loginPage;
    protected Homepage homePage;
    protected Header header;
    protected Footer footer;
    protected Cart cart;
    protected Checkout checkout;
    protected Confirmation confirmation;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        loginPage = new Login(driver);
        homePage = new Homepage(driver);
        header = new Header(driver);
        footer = new Footer(driver);
        cart = new Cart(driver);
        driver.get("https://bstackdemo.com/signin");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("username")));
        checkout = new Checkout(driver);
        confirmation = new Confirmation(driver);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void placeOrder() {
        loginPage.login("demouser", "testingisfun99");
        homePage.addProductToCart("iPhone 12");
        cart.openCart();
        cart.clickCheckout();
        checkout.fillForm("Test", "User", "123 Main St", "Cairo", "12345");
        checkout.clickSubmit();
    }
}