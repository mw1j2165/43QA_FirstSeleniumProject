package phonebook_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import phonebook.model.User;

public class LoginTests extends TestBase{

    @Test
    public void loginExistedUserPositiveTest(){
        app.getUserHelper().loginExistedUserPositive();
        app.getUserHelper().isSignOutButtonPresent();
    }

    @Test
    public void loginUserWitOutPasswordNegativeTest(){
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("user_admin_new3@gmail.com"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }
}
