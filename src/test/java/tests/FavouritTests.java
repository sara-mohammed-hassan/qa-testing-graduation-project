package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Favourites;

public class FavouritTests extends BaseTest {

    private Favourites favouritesPage;

    @BeforeMethod
    public void addToFavouritesAndNavigate() {
        loginPage.login("demouser", "testingisfun99");
        favouritesPage = new Favourites(driver);
        favouritesPage.navigateToHome();
        favouritesPage.addProductToFavouritesAndNavigate("iPhone 12");
    }


    @Test
    public void testFavouritesPageLoaded() {
        Assert.assertTrue(
                favouritesPage.isFavouritesPageLoaded(),
                "Favourites page should be loaded"
        );
    }

    @Test
    public void testFavouritesUrl() {
        Assert.assertTrue(
                driver.getCurrentUrl().contains("favourites"),
                "URL should contain '/favourites'"
        );
    }

    @Test
    public void testProductsCountGreaterThanZero() {
        Assert.assertTrue(
                favouritesPage.getProductsCount() > 0,
                "Favourites products count should be greater than 0"
        );
    }

    @Test
    public void testProductIsVisibleInFavourites() {
        Assert.assertTrue(
                favouritesPage.isProductPresent("iPhone 12"),
                "iPhone 12 should be present in favourites"
        );
    }

    @Test
    public void testProductPriceIsCorrect() {
        Assert.assertTrue(
                favouritesPage.isProductPricePresent("799"),
                "iPhone 12 price $799 should be visible in favourites"
        );
    }

    @Test
    public void testProductImagesAreDisplayed() {
        Assert.assertTrue(
                favouritesPage.getProductImagesCount() > 0,
                "Product images should be displayed in favourites"
        );
    }


    @Test
    public void testFavouritePersistsAfterRefresh() {
        int countBefore = favouritesPage.getProductsCount();
        System.out.println("TC07 – Before refresh: " + countBefore);

        favouritesPage.refreshPage();

        int countAfter = favouritesPage.getProductsCount();
        System.out.println("TC07 – After refresh: " + countAfter);

        Assert.assertEquals(
                countAfter, countBefore,
                "BUG: Favourites should persist after refresh but got "
                        + countAfter + " instead of " + countBefore
        );
    }

    @Test
    public void testProductVisibleAfterRefresh() {
        System.out.println("TC08 – Before refresh: iPhone 12 present = "
                + favouritesPage.isProductPresent("iPhone 12"));

        favouritesPage.refreshPage();

        boolean visibleAfter = favouritesPage.isProductPresent("iPhone 12");
        System.out.println("TC08 – After refresh: iPhone 12 present = " + visibleAfter);

        Assert.assertTrue(
                visibleAfter,
                "BUG: iPhone 12 should still be visible in favourites after refresh"
        );
    }

    @Test
    public void testFavouritePersistsAfterRelogin() {
        int countBefore = favouritesPage.getProductsCount();
        System.out.println("TC09 – Before logout: " + countBefore);

        header.logout();
        driver.get("https://bstackdemo.com/signin");
        loginPage.login("demouser", "testingisfun99");
        favouritesPage.navigateToFavourites();

        int countAfter = favouritesPage.getProductsCount();
        System.out.println("TC09 – After re-login: " + countAfter);

        Assert.assertEquals(
                countAfter, countBefore,
                "BUG: Favourites should persist after logout/login but got "
                        + countAfter + " instead of " + countBefore
        );
    }

    @Test
    public void testProductVisibleAfterRelogin() {
        System.out.println("TC10 – Before logout: iPhone 12 present = "
                + favouritesPage.isProductPresent("iPhone 12"));

        header.logout();
        driver.get("https://bstackdemo.com/signin");
        loginPage.login("demouser", "testingisfun99");
        favouritesPage.navigateToFavourites();

        boolean visibleAfter = favouritesPage.isProductPresent("iPhone 12");
        System.out.println("TC10 – After re-login: iPhone 12 present = " + visibleAfter);

        Assert.assertTrue(
                visibleAfter,
                "BUG: iPhone 12 should still be visible in favourites after logout/login"
        );
    }
}