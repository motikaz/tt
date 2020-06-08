import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class FloatingMenu {

    public static WebDriver driver;

    @BeforeClass // preparing for the test
    public static void beforeClassMethod()  {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting the test page
    public void gettingURL() {
        driver.get("http://the-internet.herokuapp.com/floating_menu");
    }

    @Test // #1 - checking 4 parts of the menu are available and have expected names
    public void method1() {

        String m1 = driver.findElement(By.xpath("//*[@id='menu']/ul/li[1]/a")).getText();
        Assert.assertEquals(m1, "Home");

        String m2 = driver.findElement(By.xpath("//*[@id='menu']/ul/li[2]/a")).getText();
        Assert.assertEquals(m2, "News");

        String m3 = driver.findElement(By.xpath("//*[@id='menu']/ul/li[3]/a")).getText();
        Assert.assertEquals(m3, "Contact");

        String m4 = driver.findElement(By.xpath("//*[@id='menu']/ul/li[4]/a")).getText();
        Assert.assertEquals(m4, "About");
    }

    @Test // #2 - verifying the name of the header
    public void method2() {
        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
        Assert.assertEquals(nameofthepage, "Floating Menu");
    }

    @Test // #3 - scrolling down and verifying the menu tab#2 is visible
    public void method3() {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,3000)");
        boolean fm1 = driver.findElement(By.xpath("//*[@id='menu']/ul/li[1]/a")).isDisplayed();
        if (fm1)
        {
            System.out.println("ALERT: Menu tab#2 is displayed.");
        }
        else
        {
            System.out.println("ALERT: Menu tab#2 isn't displayed !");
        }
        driver.findElement(By.xpath("//*[@id='menu']/ul/li[2]/a")).click();
    }

    @AfterClass // после выполнения всех тестов - аннотация к методу
    public static void tearDown() {
        driver.quit();
    }

}
