import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginExistedUserPositiveTest(){
        loginExistedUserPositive();
        Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }
}

