import Pages.FilesPage;
import Pages.LoginPage;
import Utils.ChromeConfig;
import org.testng.annotations.Test;


public class TestCase extends ChromeConfig {

    @Test
    protected void doFailureLogin(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doFailureLogin();
    }

    @Test
    protected void doSuccessLogin(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doSuccessLogin();
    }

    @Test
    protected void uploadTest(){
        FilesPage filePage = new FilesPage(driver);
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doSuccessLogin();
        filePage.doUpload();
    }

    @Test
    protected void deleteTest(){
        FilesPage filePage = new FilesPage(driver);
        LoginPage loginpage = new LoginPage(driver);
        loginpage.doSuccessLogin();
        filePage.doUpload();
        filePage.doDelete();
    }
}
