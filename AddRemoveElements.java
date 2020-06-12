import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class AddRemoveElements {

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
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test // #1 - verifying Add Element and Delete buttons are available
    public void method1() {

        String nameofthepage = driver.findElement(By.xpath("//*[@id='content']/h3")).getText();
        Assert.assertEquals(nameofthepage, "Add/Remove Elements");


        driver.findElement(By.xpath("//*[@id='content']/div/button")).isDisplayed();

//checking whether Delete button is available
        System.out.println(driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size());
        if (driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size() > 0)
            System.out.println("We see Delete button - it's incorrect! Call a developer!");
        else
            System.out.println("Button Delete isn't available prior pressing Add Element button - it's good.");
    }

    @Test // #2 - pressing Add Element and checking whether Delete button appears
    public void method2() {
        driver.findElement(By.xpath("//*[@id='content']/div/button")).click();

        //checking whether Delete button is available
        System.out.println(driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size());
        if (driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size() > 0)
            System.out.println("After pressing Add Element we see Delete button - it's correct.");
        else
            System.out.println("Button Delete isn't available after pressing Add Element button - it's incorrect! Call a developer!");

//pressing Add Element for the second time
        driver.findElement(By.xpath("//*[@id='content']/div/button")).click();

//checking whether a second Delete button is available
        System.out.println(driver.findElements(By.xpath("//*[@id='elements']/button[2]")).size());
        if (driver.findElements(By.xpath("//*[@id='elements']/button[2]")).size() > 0)
            System.out.println("We see a second Delete button - it's correct.");
        else
            System.out.println("A second Button Delete isn't available - it's incorrect! Call a developer!");

//pressing a second Delete button
        driver.findElement(By.xpath("//*[@id='elements']/button[2]")).click();

//checking whether a second Delete button is available
        System.out.println(driver.findElements(By.xpath("//*[@id='elements']/button[2]")).size());
        if (driver.findElements(By.xpath("//*[@id='elements']/button[2]")).size() > 0)
            System.out.println("We see a second Delete button - it's incorrect. We shouldn't. Call a developer!");
        else
            System.out.println("A second Button Delete isn't available - it's correct.");

//pressing a first/single Delete button
        driver.findElement(By.xpath("//*[@id='elements']/button[1]")).click();

//checking whether a second Delete button is available
        System.out.println(driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size());
        if (driver.findElements(By.xpath("//*[@id='elements']/button[1]")).size() > 0)
            System.out.println("We see a first/single Delete button - it's incorrect. We shouldn't. Call a developer!");
        else
            System.out.println("A first/single Button Delete isn't available - it's correct.");
    }


    @Test // #3 - adding one more element
    @Ignore
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


    @AfterClass // closing the test-case
    public static void tearDown() {
        driver.quit();
    }
}
