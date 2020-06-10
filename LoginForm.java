import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class LoginForm {
// checking Login form

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
        driver.get("http://the-internet.herokuapp.com/login");
    }

    @Test // #1 - verifying main words on the page
    public void method1() {
        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/div/h2")).getText();
        Assert.assertEquals(nameofthepage, "Login Page");
        String username = driver.findElement(By.xpath("//*[@id='login']/div[1]/div/label")).getText();
        Assert.assertEquals(username, "Username");
        String password = driver.findElement(By.xpath("//*[@id='login']/div[2]/div/label")).getText();
        Assert.assertEquals(password, "Password");
        String login = driver.findElement(By.xpath("//*[@id='login']/button/i")).getText();
        Assert.assertEquals(login, "Login");
    }

    @Test // #2 - just pressing "Login" without adding any username/password in the forms
    public void method2() {
        driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
        boolean result = driver.findElement(By.xpath("//*[@id='flash']")).getText().contains("Your username is invalid!");
        System.out.println(result); //just to be sure that we are getting exact words

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/login");
    }

    @Test // #3 - adding the correct username/password "tomsmith"|"SuperSecretPassword!"
    public void method3() {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
//after getting in, we check phrases
        String pageurlloggedin = driver.getCurrentUrl();
        Assert.assertEquals(pageurlloggedin, "http://the-internet.herokuapp.com/secure");

        boolean resultli = driver.findElement(By.xpath("//*[@id='flash']")).getText().contains("You logged into a secure area!");
        System.out.println(resultli); //just to be sure that we are getting exact words

        boolean nameofthepage = driver.findElement(By.xpath("//*[@id='content']")).getText().contains("Secure Area");
        System.out.println(nameofthepage); //just to be sure that we are getting exact words

        boolean resultlit = driver.findElement(By.xpath("//*[@id='content']")).getText().contains("Welcome to the Secure Area. When you are done click logout below.");
        System.out.println(resultlit); //just to be sure that we are getting exact words

        boolean logoutbutton = driver.findElement(By.xpath("//*[@id='content']/div/a/i")).isDisplayed();
        System.out.println(logoutbutton); //just to be sure that the button is visible on the page
//exiting from Secured Area
        driver.findElement(By.xpath("//*[@id='content']/div/a/i")).click(); //pressing the Logout button

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/login");

        String nameofthepageafter = driver.findElement(By.xpath("//*[@id='content']/div/h2")).getText();
        Assert.assertEquals(nameofthepageafter, "Login Page");
    }

    @Test // #4 - just pressing "Login" with username only, no password in the forms
    public void method4() {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
        boolean result = driver.findElement(By.xpath("//*[@id='flash']")).getText().contains("Your password is invalid!");
        System.out.println(result); //just to be sure that we are getting exact words

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/login");
    }

    @Test // #5 - just pressing "Login" with username only, no password in the forms
    public void method5() {
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("12345abc");
        driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
        boolean result = driver.findElement(By.xpath("//*[@id='flash']")).getText().contains("Your password is invalid!");
        System.out.println(result); //just to be sure that we are getting exact words

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/login");
    }

    @Test // #6 - just pressing "Login" with username only, no password in the forms
    public void method6() {
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("12345abc");
        driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
        boolean result = driver.findElement(By.xpath("//*[@id='flash']")).getText().contains("Your username is invalid!");
        System.out.println(result); //just to be sure that we are getting exact words

        String pageurl = driver.getCurrentUrl();
        Assert.assertEquals(pageurl, "http://the-internet.herokuapp.com/login");
    }

    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}