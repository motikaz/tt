import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class CheckBoxes {

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

        @Before // перед выполнением одного из тестов - загружаем тестовую страницу
        public void gettingURL() {
            driver.get("http://the-internet.herokuapp.com/checkboxes");
        }

        @Test // тест #1 - finding the 1st checkbox and understanding if it's checked
        public void method1() {

            boolean checkBox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected();
            if (checkBox)
            {
                System.out.println("The checkbox#1 is checked.");
            }
            else
            {
                System.out.println("The checkbox#1 isn't checked!");
            }
        }

        @Test // тест #2 - finding the 2nd checkbox and understanding if it's checked
        public void method2() {

            boolean checkBox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected();
            if (checkBox)
            {
                System.out.println("The checkbox#2 is checked.");
            }
            else
            {
                System.out.println("The checkbox#2 isn't checked!");
            }
        }

        @Test // тест #3 - - verifying the checkbox#1 is checkable and status is changing
        public void method3() {

            driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
            boolean checkBox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected();
            if (checkBox)
            {
                System.out.println("The checkbox#1 is now checked.");
            }
            else
            {
                System.out.println("The checkbox#1 isn't checked now!");
            }
        }

        @Test // тест #4 - verifying the checkbox#2 is checkable and status is changing
        public void method4() {

            driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).click();
            boolean checkBox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected();
            if (checkBox)
            {
                System.out.println("The checkbox#2 is now checked.");
            }
            else
            {
                System.out.println("The checkbox#2 isn't checked now!");
            }

        }

        @AfterClass // после выполнения всех тестов - аннотация к методу
        public static void tearDown() {
            driver.quit();
        }

}
