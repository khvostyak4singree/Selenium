package Pages;

import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends Tools {

    public  LoginPage(WebDriver driver){
        this.driver=driver;

    }

    private  String SERVICE_URL = "http://dropbox.com/login";
    private  By email_field = By.cssSelector("div>input[name='login_email']");
    private  By pass_field = By.cssSelector("div>input[name='login_password']");
    private  By sign_in = By.cssSelector("button[type='submit']>div.sign-in-text");

    public void doLogin(String PASS){
        driver.navigate().to(SERVICE_URL);
        driver.findElement(email_field).sendKeys("testusermail2017@gmail.com");
        driver.findElement(pass_field).sendKeys(PASS);
        driver.findElement(sign_in).click();
        sleep(5);
    }

    public void doSuccessLogin(){
        doLogin("Password11");
        Assert.assertTrue(driver.getTitle().contains("Dropbox"));
    }

    public void doFailureLogin(){
        doLogin("killpass");
    }


}
