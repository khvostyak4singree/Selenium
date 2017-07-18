import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by hillel on 18.07.17.
 */
public class Box {

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
    }
    WebDriver driver;



    @BeforeMethod
    void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.box.com/home");

    }

    @Test
    void downloadFile() throws InterruptedException, AWTException {
        File file = new File("/home/hillel/IdeaProjects/Hillel/Selenium/Selenium.iml");
        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.xpath("//*[@id=\"mod-action-bar-1\"]/div[2]/div[2]/div[2]/a")).click();
        driver.findElement(By.xpath(" //*[@id=\"upload-menu\"]/li[1]/label")).click();
        Thread.sleep(3000);
        setClipboardData(file.getAbsolutePath());
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void teardown(ITestResult testResult){
        System.out.println(testResult.getMethod().getInstance());
        driver.quit();
    }
}


