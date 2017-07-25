package Pages;

import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by hillel on 25.07.17.
 */
public class FilesPage extends Tools {

    public FilesPage(WebDriver driver){
        this.driver=driver;

    }

    private By uploadButton = By.xpath("//span[contains(text(),'Upload files')]/ancestor::button");
    private By basicUploader = By.cssSelector("button[aria-label='Switch to the basic file uploader']");
    private By fileInput = By.cssSelector("input[type='file'][aria-label='Upload files']");
    private String FILE_PATH = "/home/hillel/tools.txt";
    private By testFile = By.xpath("//span[contains(text(),'tools.txt')]") ;


    public void doUpload(){
        waitForElementDisplayed(uploadButton);
        driver.findElement(uploadButton).click();
        waitForElementDisplayed(basicUploader);
        driver.findElement(basicUploader).click();
        driver.findElement(fileInput).sendKeys(FILE_PATH);
        waitForElementDisplayed(testFile);
    }

    public void doDelete(){
        driver.get("https://dropbox.com/home");
        waitForElementDisplayedXpath("//span[contains(text(),'tools.txt')]");
        waitForElementDisplayedXpath("//button[@role='button'][contains(@class,'browse-overflow')]");
        driver.findElement(By.xpath("//button[@role='button'][contains(@class,'browse-overflow')]")).click();
        waitForElementDisplayedXpath("//div//button[@role='menuitem'][contains(text(),'Delete')]");
        driver.findElement(By.xpath("//div//button[@role='menuitem'][contains(text(),'Delete')]")).click();
        sleep(3);
        driver.findElement(By.cssSelector("button.button-primary")).click();
        sleep(3);
        Assert.assertTrue(driver.findElements(By.xpath("//span[contains(text(),'tools.txt')]")).isEmpty());

    }
}

