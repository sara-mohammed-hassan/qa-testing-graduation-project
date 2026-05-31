package tests;

import baseTest.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FooterTests extends BaseTest {

    @Test(priority = 1)
    public void verifyFooterIsDisplayed() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertTrue(footer.isDisplayed(),
                "Footer is not displayed!");
    }

    @Test(priority = 2)
    public void verifyOffersLinkNavigation() {
        loginPage.login("demouser", "testingisfun99");
        footer.goToOffers();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("offers"));
        Assert.assertTrue(driver.getCurrentUrl().contains("offers"),
                "Offers page did not load!");
    }

    @Test(priority = 3)
    public void verifyContactLinkNavigation() {
        loginPage.login("demouser", "testingisfun99");
        footer.goToContact();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("contact"));
        Assert.assertTrue(driver.getCurrentUrl().contains("contact"),
                "Contact page did not load!");
    }

    @Test(priority = 4)
    public void verifyPrivacyLinkNavigation() {
        loginPage.login("demouser", "testingisfun99");
        footer.goToPrivacy();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("privacy"));
        Assert.assertTrue(driver.getCurrentUrl().contains("privacy"),
                "Privacy page did not load!");
    }

    @Test(priority = 5)
    public void verifyCareersLinkNavigation() {
        loginPage.login("demouser", "testingisfun99");
        footer.goToCareers();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("careers"));
        Assert.assertTrue(driver.getCurrentUrl().contains("careers"),
                "Careers page did not load!");
    }

    @Test(priority = 6)
    public void verifyCopyrightText() {
        loginPage.login("demouser", "testingisfun99");
        Assert.assertEquals(footer.getCopyrightText(),
                "© 2026 BrowserStack Demo",
                "Copyright text is wrong!");
    }
}
