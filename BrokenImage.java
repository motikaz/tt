import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class BrokenImage {

    public static WebDriver driver;

    @BeforeClass // перед выполнением всех тестов//анотация к методу
    public static void beforeClassMethod()  {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\VAD\\IdeaProjects\\testselenium\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before // перед выполнением одного из тестов - загружаем тестовую страницу
    public void gettingURL() {

        driver.get("http://the-internet.herokuapp.com/broken_images");
    }

    @Test // тест #1 - находим 1 картинку, проверяем что она есть
    public void method1() {

        WebElement image = driver.findElement(By.xpath("//*[@id='content']/div/img[1]"));
        boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
        if (!ImagePresent)
        {
            System.out.println("ALERT: Image#1 not displayed !");
        }
        else
        {
            System.out.println("Image#1 displayed.");
        }
    }

    @Test // тест #2 - находим 2 картинку, проверяем что она есть
    public void method2() {

        WebElement image = driver.findElement(By.xpath("//*[@id='content']/div/img[2]"));
        boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
        if (!ImagePresent)
        {
            System.out.println("ALERT: Image#2 not displayed !");
        }
        else
        {
            System.out.println("Image#2 displayed.");
        }
    }

    @Test // тест #3 - находим 1 картинку, проверяем что она есть
    public void method3() {

        WebElement image = driver.findElement(By.xpath("//*[@id='content']/div/img[3]"));
        boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
        if (!ImagePresent)
        {
            System.out.println("ALERT: Image#3 not displayed !");
        }
        else
        {
            System.out.println("Image#3 displayed.");
            System.out.println();
        }
    }

    @Test // тест #4 - проверяем размеры картинок: checking sizes of images
    public void method4() {

        int height1 = driver.findElement(By.xpath("//*[@id='content']/div/img[3]")).getSize().height;
        int width1 = driver.findElement(By.xpath("//*[@id='content']/div/img[3]")).getSize().width;

        if (height1 == 160)
        {
            System.out.println("Height of the Image#3 is 160 px as expected.");
        }
        else
        {
            System.out.println("ALERT: Height of the Image#3 is " + height1 + " ! Expected 160 px.");
        }

        if (width1 == 160) {
            System.out.println("Width of the Image#3 is 160 px as expected.");
        }
        else
        {
            System.out.println("ALERT: Width of the Image#3 is " + width1 + " ! Expected 160 px.");
        }
    }

    @AfterClass // после выполнения всех тестов//анотация к методу
    public static void tearDown() {
        driver.quit();
    }
}