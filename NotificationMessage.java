import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class NotificationMessage {

    public static WebDriver driver;

    @BeforeClass // preparing for the test
    public static void beforeClassMethod() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting the test page
    public void gettingURL() {
        driver.get("http://the-internet.herokuapp.com/notification_message");
    }

    @Test // #1 - check the notification after loading the page
    public void method1() {

        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
        Assert.assertEquals(nameofthepage, "Notification Message");

        boolean txt1 = driver.findElement(By.xpath("//*[@id='content']/div/p")).getText().contains("The message displayed above");
        System.out.println(txt1);

        driver.findElement(By.xpath("//*[@id='flash']")).isDisplayed();

        String resultn = driver.findElement(By.xpath("//*[@id='flash']")).getText();
        System.out.println("Notice is available and text is : " + resultn); //notification text

        driver.findElement(By.xpath("//*[@id='content']/div/p/a")).click(); //click1

        String resultnm = driver.findElement(By.xpath("//*[@id='flash']")).getText();
        System.out.println("Notice is available and text is : " + resultnm); //notification text

        driver.findElement(By.xpath("//*[@id='content']/div/p/a")).click(); //click2

        String resultnmm = driver.findElement(By.xpath("//*[@id='flash']")).getText();
        System.out.println("Notice is available and text is : " + resultnmm); //notification text

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/notification_message_rendered");
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}