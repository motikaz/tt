import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class HorizontalSlider {

    public static WebDriver driver;

    @BeforeClass // preparing for the test
    public static void beforeClassMethod() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // getting the test page
    public void gettingURL() {
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");
    }

    @Test // #0 - setting 0
    public void method0() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
//INFO: When using the W3C Action commands, offsets are from the center of element
//hence: 0/0=2.5. 75/0= 75%=4.5. means need to split all steps by 0.5 by percentage!
        builder.clickAndHold(slider).moveToElement(slider, -100, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @Test // #1 - setting 0.5
    public void method1() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(slider).moveToElement(slider, -75, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @Test // #2 - setting 1.0
    public void method2() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(slider).moveToElement(slider, -50, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @Test // #3 - setting 1.5
    public void method3() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(slider).moveToElement(slider, -35, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @Test // #4 - setting 2.0
    public void method4() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(slider).moveToElement(slider, -15, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @Test // #5 - setting 2.5
    public void method5() {

        String range1 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range1);

        WebElement slider = driver.findElement(By.xpath("//*[@id='content']/div/div/input"));

        Actions builder = new Actions(driver);
        builder.clickAndHold(slider).moveToElement(slider, 0, 0).release().build().perform();

        String range2 = driver.findElement(By.xpath("//*[@id='range']")).getText();
        System.out.println(range2);
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}
