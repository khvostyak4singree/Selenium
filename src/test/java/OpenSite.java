import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenSite {

   public static WebDriver driver;
    public static WebDriverWait wait;
        @BeforeMethod
                public static void setup(){
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get("https://www.random.org");
                wait = new WebDriverWait(driver,10);
                driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
            }

    @Test
  public static void tests(int i) throws InterruptedException {
        Thread.sleep(1000);
        WebElement min = driver.findElement(By.id("true-random-integer-generator-min"));
        min.clear();
        min.sendKeys("5");
        WebElement max = driver.findElement(By.id("true-random-integer-generator-max"));
        max.clear();
        max.sendKeys("10");
        WebElement button = driver.findElement(By.id("true-random-integer-generator-button"));
        button.click();
        WebElement number = driver.findElement(By.cssSelector("#true-random-integer-generator-result"));
        Thread.sleep(1000);
        Integer result = Integer.parseInt(number.getText());
        System.out.println(result);
        Assert.assertTrue(result > 0 && result < 100);
    }

        @AfterMethod
    public static void teardown(){
                    driver.close();
        }
    }
