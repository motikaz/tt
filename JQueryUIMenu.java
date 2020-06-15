import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class JQueryUIMenu {
//downloading 3 files and navigating to another page listed ion the menu

    public static WebDriver driver;

    @BeforeClass // preparing for the test
    public static void beforeClassMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting the test page
    public void gettingURL() {
        driver.get("http://the-internet.herokuapp.com/jqueryui/menu");
    }

    @Test // #1 - downloading PDF-file
    public void method1() {

        WebElement enabled = driver.findElement(By.xpath("//*[@id='ui-id-2']"));
        WebElement downloads = driver.findElement(By.xpath("//*[@id='ui-id-4']"));
        WebElement pdf = driver.findElement(By.xpath("//*[@id='ui-id-6']"));

        Actions action1 = new Actions(driver);
        action1.moveToElement(enabled).click().build().perform();  //hover over Enabled option & click
        action1.moveToElement(downloads).click().build().perform();  //hover over Downloads' option & click
        action1.moveToElement(pdf, -70, 0).click().build().perform(); // 70 good here
   }

    @Test // #2 - downloading CSV-file
    public void method2() {

        WebElement enabled = driver.findElement(By.xpath("//*[@id='ui-id-2']"));
        WebElement downloads = driver.findElement(By.xpath("//*[@id='ui-id-4']"));
        WebElement csv = driver.findElement(By.xpath("//*[@id='ui-id-7']"));

        Actions action1 = new Actions(driver);
        action1.moveToElement(enabled).click().build().perform();  //hover over Enabled option & click
        action1.moveToElement(downloads).click().build().perform();  //hover over Downloads' option & click
        action1.moveToElement(csv, -70, 0).click().build().perform(); // 70 good here
    }

    @Test // #3 - downloading Excel-file
    public void method3() {

        WebElement enabled = driver.findElement(By.xpath("//*[@id='ui-id-2']"));
        WebElement downloads = driver.findElement(By.xpath("//*[@id='ui-id-4']"));
        WebElement xls = driver.findElement(By.xpath("//*[@id='ui-id-8']"));

        Actions action1 = new Actions(driver);
        action1.moveToElement(enabled).click().build().perform();  //hover over Enabled option & click
        action1.moveToElement(downloads).click().build().perform();  //hover over Downloads' option & click
        action1.moveToElement(xls, -70, 0).click().build().perform(); // 70 good here
    }

    @Test // #4 - navigating to "JQuery UI"-page
    public void method4() {

        WebElement enabled = driver.findElement(By.xpath("//*[@id='ui-id-2']"));
        WebElement back = driver.findElement(By.xpath("//*[@id='ui-id-5']"));

        Actions action1 = new Actions(driver);
        action1.moveToElement(enabled).click().build().perform();  //hover over Enabled option & click
        action1.moveToElement(back).click().build().perform();  //hover over Back' option & click

//we went to another page - lets check it out!
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://the-internet.herokuapp.com/jqueryui");
    }

    @AfterClass // closing the test-case
    public static void tearDown() { driver.quit();}
}