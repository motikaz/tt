import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class JavaScriptAlerts{
//operating 3 JS Alerts/menus. http://internetka.in.ua/selenium-driver-aler/

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
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test // #1 - JS Alert - clicking OK; confirming the result
    public void method1() {

        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[1]/button")).click(); //jsAlert
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(text, "I am a JS Alert");
        alert.dismiss(); // here we could say both dismiss or accept.

        String actionresult = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(actionresult, "You successfuly clicked an alert");
    }

    @Test // #2 - JS Confirmation - clicking OK or Cancel; confirming the result
    public void method2() {

        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button")).click(); //jsConfirm
        Alert alert1 = driver.switchTo().alert();
        String text1 = alert1.getText();
        Assert.assertEquals(text1, "I am a JS Confirm");
        alert1.dismiss();

        String actionresult1 = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(actionresult1, "You clicked: Cancel");

        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button")).click(); //jsConfirm
        Alert alert2 = driver.switchTo().alert();
        String text2 = alert2.getText();
        Assert.assertEquals(text2, "I am a JS Confirm");
        alert2.accept();

        String actionresult2 = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(actionresult2, "You clicked: Ok");
    }

    @Test // #3 - JS Prompt- adding text; confirming/declining the result
    public void method3() {

        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button")).click(); //jsPrompt
        Alert alert1 = driver.switchTo().alert();
        String text1 = alert1.getText();
        Assert.assertEquals(text1, "I am a JS prompt");
        alert1.sendKeys("ho-ho-ho");
        alert1.accept();

        String actionresult1 = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(actionresult1, "You entered: ho-ho-ho");

        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button")).click(); //jsPrompt
        Alert alert2 = driver.switchTo().alert();
        String text2 = alert2.getText();
        Assert.assertEquals(text2, "I am a JS prompt");
        alert2.sendKeys("ho-ho-ho");
        alert2.dismiss();

        String actionresult2 = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(actionresult2, "You entered: null");
    }

    @AfterClass // closing the test-case
    public static void tearDown() { driver.quit();}
}