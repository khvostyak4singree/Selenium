import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.NoSuchElementException;
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
        driver.get("https://accounts.google.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    void  PerformStep(String inputfield, String input){
       WebElement field = driver.findElement(By.cssSelector(inputfield));
       field.clear();
       field.sendKeys(input);
        driver.findElement(By.cssSelector("div[role='button']")).click();
        mySleep(1);
    }

    void FirstStep(String input){
        PerformStep("input[type='email']",input);
    }

    void SecondStep(String input){
        PerformStep("input[type='password']", input);
    }

    @Test(groups = "positive", priority = 1)
    public void emailTrue() throws Exception {
        FirstStep(validEmail);
        Assert.assertTrue(doesElementExist("input[name='password']"));

    }
    @Test(groups = "negative", priority = 1)
    public void emailFalse() throws Exception {
        FirstStep(notValidEmail);
        Assert.assertTrue((doesElementExist("input[type='email']")));

    }
    @Test(groups = "positive", dependsOnMethods = {"emailTrue"})
    public void passTrue() throws Exception {
      SecondStep(truePass);
        Assert.assertFalse((doesElementExist("input[name='password']")));


    }
    @Test( groups = "negative", dependsOnMethods = {"emailFalse"})
    public void passFalse() throws Exception {
        SecondStep(notTruePass);
        Assert.assertTrue((doesElementExist("input[name='password']")));

    }

    void mySleep(int second){
        try{
            Thread.sleep(second*1000);
        }catch (InterruptedException e){}
    }

    boolean doesElementExist(String selector){
        try{
            driver.findElement(By.cssSelector(selector));
            return  true;
        }catch (NoSuchElementException e){
            return  false;
        }
    }

    @AfterMethod
    public static void teardown(ITestResult testResult){
        System.out.println(testResult.getMethod().getInstance());
        driver.close();
    }
}


