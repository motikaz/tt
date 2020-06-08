import org.junit.*;
import org.openqa.selenium.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DropDownList {

    public static WebDriver driver;

    @BeforeClass // перед выполнением всех тестов - аннотация к методу
    public static void beforeClassMethod()  {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting onto the test page
    public void gettingURL() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }

    @Test // #1 - press the dropdown field#1
    public void method1() {
        driver.findElement(By.xpath("//*[@id='dropdown']")).click();
        driver.findElement(By.xpath("//*[@id='dropdown']/option[2]")).click();
    }

    @Test // #2 - press the dropdown field#2
    public void method2() {
        driver.findElement(By.xpath("//*[@id='dropdown']")).click();
        driver.findElement(By.xpath("//*[@id='dropdown']/option[3]")).click();
    }

    @Test // #3 - assert the name of the page
    public void method3() {
        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
        Assert.assertEquals(nameofthepage, "Dropdown List");
    }

    @AfterClass // после выполнения всех тестов - аннотация к методу
    public static void tearDown() {
        driver.quit();
    }

}