import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EntryAd {
// checking the advertisement appearing at the page entry

public static WebDriver driver;

    @BeforeClass // preparing for the test
    public static void beforeClassMethod() {
//  System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//  driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting the test page
    public void gettingURL() {
            driver.get("http://the-internet.herokuapp.com/entry_ad");
        }

    @Test // #1 - verifying main words on the page
    public void method1() {
//this needs to get a modal window to appear!
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));
//this needs to switch to modal window
//both elements Wait & Switch are must!
        driver.switchTo().activeElement();

        String nameofad = driver.findElement(By.xpath("//*[@id='modal']/div[2]/div[1]/h3")).getText();
        Assert.assertEquals(nameofad, "THIS IS A MODAL WINDOW");

        boolean adtext = driver.findElement(By.xpath("//*[@class='modal-body']/p")).getText().contains("It's commonly used to encourage");
        System.out.println(adtext); //just to be sure that we are getting exact words

        String buttontext = driver.findElement(By.xpath("//*[@class='modal-footer']/p")).getText();
        Assert.assertEquals(buttontext, "Close");

//pressing close button
        driver.findElement(By.xpath("//*[@id='modal']/div[2]/div[3]/p")).click();

        String nameofthepage = driver.findElement(By.xpath("//*[@class='example']/h3")).getText();
        Assert.assertEquals(nameofthepage, "Entry Ad");

        driver.findElement(By.xpath("//*[@id='restart-ad']")).click();
        driver.findElement(By.xpath("//*[@class='modal-title']/h3")).isDisplayed();
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
            driver.quit();
    }
}