import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class ForgotPassword {
// adding the proper e-mail verification

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
        driver.get("http://the-internet.herokuapp.com/forgot_password");
    }

    @Test // #1 - verifying the name of the header
    public void method1() {
        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div/h2")).getText();
        Assert.assertEquals(nameofthepage, "Forgot Password");
    }

    @Test // #2 - adding the correct email "co77@77co.co"
    public void method2() {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("co77@77co.co");
        driver.findElement(By.xpath("//*[@id='form_submit']")).click();
        String result = driver.findElement(By.xpath("//*[@id='content']")).getText();
        Assert.assertEquals(result, "Your e-mail's been sent!");
    }

    @Test // #3 - just pressing "Retrieve password" without adding any emails in the form
    public void method3() {
        driver.findElement(By.xpath("//*[@id='form_submit']")).click();
        String result = driver.findElement(By.xpath("/html/body/h1")).getText();
        Assert.assertEquals(result, "Internal Server Error");
    }

    @Test // #4 - adding the incorrect email1 - space in the middle
    public void method4() {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("co@co. co");
        driver.findElement(By.xpath("//*[@id='form_submit']")).click();
        String result = driver.findElement(By.xpath("/html/body/h1")).getText();
        Assert.assertEquals(result, "Internal Server Error");
    }

    @Test // #5 - adding the incorrect email2 - <alert!/>
    public void method5() {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("<alert!/>");
        driver.findElement(By.xpath("//*[@id='form_submit']")).click();
        String result = driver.findElement(By.xpath("/html/body/h1")).getText();
        Assert.assertEquals(result, "Internal Server Error");
    }

    @Test // #6 - adding the incorrect email3 - dot in the middle 777.alex@777.auto
    public void method6() {
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("777.alex@777.auto");
        driver.findElement(By.xpath("//*[@id='form_submit']")).click();
        String result = driver.findElement(By.xpath("//*[@id='content']")).getText();
        Assert.assertEquals(result, "Your e-mail's been sent!");
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}
