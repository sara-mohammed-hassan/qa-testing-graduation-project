import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class BrowserActions {
    WebDriver driver;

    @Test
    public void testCase() { //test runner
        driver = new EdgeDriver();
        //driver.get("https://bstackdemo.com/"); get() = navigate().to()
        maximize();
        navigateTo("https://bstackdemo.com/");
        getcurrentUrl();
        getTitle();
        //getPagesource();
        //setPosition(100, 50);
        //setSize(150, 300);

        /*navigateTo("https://bstackdemo.com/signin?favourites=true");
        navigateBack();
        navigateForward();
        refresh();*/
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void setPosition(int x, int y) {
        driver.manage().window().setPosition(new Point(x, y));
    }

    public void setSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));

    }

    public void getcurrentUrl() {
        String URL = driver.getCurrentUrl();
        System.out.println("the Current URL :" + URL);
    }

    public void getTitle() {
        String title = driver.getTitle();
        System.out.println("the title of the page :" + title);
    }

    public void getPagesource() {
        String PageSource = driver.getPageSource();
        System.out.println("page source:" + PageSource);
    }
}
