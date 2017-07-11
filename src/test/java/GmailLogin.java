import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class GmailLogin {

    public static WebDriver driver;
    public static final String validEmail = "khvostyak@singree.com";
    public static final String notValidEmail = "khvostyak234singree.com";
    public static final String truePass = "123scarys123";
    public static final String notTruePass = "123";


    @BeforeMethod
    public static void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/signin/v2/identifier?passive=1209600&continue=https%3A%2F%2Faccounts.google.com%2FManageAccount&followup=https%3A%2F%2Faccounts.google.com%2FManageAccount&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test(priority = 1, description = "positive email")
    public static void emailTrue() throws Exception {
      Thread.sleep(1000);
        WebElement changeAcc = driver.findElement(By.id("identifierId"));
        changeAcc.sendKeys(validEmail);
       WebElement enter = driver.findElement(By.className("CwaK9"));
        enter.click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.name("password")).isDisplayed());

    }
    @Test(priority = 2, description = "negative email")
    public void emailFalse() throws Exception {
      WebElement changeAcc = driver.findElement(By.id("identifierId"));
        changeAcc.sendKeys(notValidEmail);
      WebElement enter = driver.findElement(By.className("CwaK9"));
        enter.click();
        Assert.assertFalse(driver.findElement(By.xpath("//*[@id=\"view_container\"]/form/div[2]/div/div[1]/div[1]/div/div[2]/div[2]")).isDisplayed());
        changeAcc.clear();
        Thread.sleep(1000);
    }
    @Test(priority = 3, description = "positive pass")
    public void passTrue() throws Exception {
        Thread.sleep(1000);
        emailTrue();
       WebElement pass = driver.findElement(By.name("password"));
        pass.sendKeys(truePass);
       WebElement enterAcc = driver.findElement(By.className("CwaK9"));
        enterAcc.click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[2]/c-wiz[2]/div/div/div[5]/div[1]/content/div/h1")).isDisplayed());
        Thread.sleep(2000);

    }
    @Test(priority = 4, description = "negative email")
    public void passFalse() throws Exception {
        Thread.sleep(1000);
        emailTrue();
        WebElement pass = driver.findElement(By.name("password"));
        pass.sendKeys(notTruePass);
       WebElement enterAcc = driver.findElement(By.className("CwaK9"));
        enterAcc.click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".dEOOab.RxsGPe")).isDisplayed());

    }

    @AfterMethod
    public static void teardown(ITestResult testResult){
        System.out.println(testResult.getMethod().getInstance());
        driver.close();
    }
}


