import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HillelSoft {

    WebDriver driver;

    @BeforeMethod
    void setUP() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--kiosk");
        options.addArguments("--lang=en");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

        void stuff() throws AWTException {
            ArrayList<String> window = new ArrayList<String>(driver.getWindowHandles());
            //превключение между окнами
            driver.switchTo().window(window.get(1));
            driver.close();
            //перевключение между табами в браузере
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "2");
            //дествия с системой
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ESCAPE);
            //действия с елементами на страницы
            Actions a =new Actions(driver);
            a.contextClick().build().perform();
            //перевод в джаваскрипт движок
            ((JavascriptExecutor)driver).executeScript("");

        }
    }

