import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class Hover {
//to hover over the page's element and do some work

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
        driver.get("http://the-internet.herokuapp.com/hovers");
    }

    @Test // checking several elements of the page are available
    public void method1() {

        String m1 = driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
        Assert.assertEquals(m1, "Hovers");

        boolean adtext = driver.findElement(By.xpath("//*[@id='content']/div/p")).getText().contains("Hover over the image");
        System.out.println(adtext); //just to be sure that we have exact words
    }

    @Test // main test - hover over img1
    public void method2() {

        WebElement img = driver.findElement(By.xpath("//*[@id='content']/div/div[1]/img"));

        Actions actions = new Actions(driver);
        actions.moveToElement(img).build().perform();  //hover over an img1

        String nameuser = driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/h5")).getText();
        Assert.assertEquals(nameuser, "name: user1");

        driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div/a")).click();

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/users/1");
    }

    @Test // main test - hover over img2
    public void method3() {

        WebElement img = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/img"));

        Actions actions = new Actions(driver);
        actions.moveToElement(img).build().perform();  //hover over an img2

        String nameuser = driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/h5")).getText();
        Assert.assertEquals(nameuser, "name: user2");

        driver.findElement(By.xpath("//*[@id='content']/div/div[2]/div/a")).click();

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/users/2");
    }
    
    @Test // main test - hover over img3
    public void method4() {

        WebElement img = driver.findElement(By.xpath("//*[@id='content']/div/div[3]/img"));

        Actions actions = new Actions(driver);
        actions.moveToElement(img).build().perform();  //hover over an img3

        String nameuser = driver.findElement(By.xpath("//*[@id='content']/div/div[3]/div/h5")).getText();
        Assert.assertEquals(nameuser, "name: user3");

        driver.findElement(By.xpath("//*[@id='content']/div/div[3]/div/a")).click();

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/users/3");
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}