import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExitIntent {
//user must read the page and click Close

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
        driver.get("http://the-internet.herokuapp.com/exit_intent");
    }

    @Test // #1 - verifying main idea of the test - moving the mouse to the top/url side
    public void method1() {

        try {
// These coordinates are screen coordinates where I want to place a mouse
            int xCoord = -1;
            int yCoord = 0;

// Move the cursor to the above place
            Robot robot = new Robot();
            robot.mouseMove(xCoord, yCoord);
        } catch (AWTException e) { e.printStackTrace();
        }

// We see the modal window now! great!
        WebDriverWait wait = new WebDriverWait(driver, 5);
//this needs to get a modal window to appear!
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal")));

//this needs to switch to modal window
//both elements Wait & Switch are must!
        driver.switchTo().activeElement();

        String nameofad = driver.findElement(By.xpath("//*[@id='ouibounce-modal']/div[2]/div[1]/h3")).getText();
        Assert.assertEquals(nameofad, "THIS IS A MODAL WINDOW");

        boolean adtext = driver.findElement(By.xpath("//*[@id='ouibounce-modal']/div[2]/div[2]/p")).getText().contains("It's commonly used to encourage");
        System.out.println(adtext); //just to be sure that we have exact words

        String buttontext = driver.findElement(By.xpath("//*[@id='ouibounce-modal']/div[2]/div[3]/p")).getText();
        Assert.assertEquals(buttontext, "Close");

//pressing close button
        driver.findElement(By.xpath("//*[@id='ouibounce-modal']/div[2]/div[3]/p")).click();
//Modal window is disappeared
        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div[1]/h3")).getText();
        Assert.assertEquals(nameofthepage, "Exit Intent");

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/exit_intent");

//making a screenshot
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HHmmss"); //screenshot template
        String fileName = format.format(dateNow) + ".png";

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //making a file

        try {
            FileHandler.copy(screenshot, new File("C:\\QA-main\\AQA\\Screenshots\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace(); //file's save
        }
//screenshot is done
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}