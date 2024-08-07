package phonebook.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import phonebook.core.BaseHelper;
import phonebook.data.UserData;
import phonebook.model.User;

public class UserHelper extends BaseHelper {
  Logger logger = LoggerFactory.getLogger(UserHelper.class);
  public UserHelper(WebDriver driver, WebDriverWait wait) {
    super(driver,wait);
  }

  public void loginExistedUserPositive() {
    clickLoginLink();
    logger.info("Login with data: "+ UserData.EMAIL + " | " + UserData.PASSWORD);
    fillInRegistrationForm(new User()
        .setEmail(UserData.EMAIL)
        .setPassword(UserData.PASSWORD));
    clickOnLoginButton();
  }

  public void fillInRegistrationForm(User user) {
    type(By.name("email"), user.getEmail());
    type(By.name("password"), user.getPassword());
  }
  public void clickOnLoginButton() {
    click(By.name("login"));
  }

  public void clickLoginLink() {
    click(By.xpath("//a[.='LOGIN']"));
  }

  public void isSignOutButtonPresent() {
    Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
  }

  public boolean isLoginLinkPresent() {
    return isElementPresent(By.xpath("//a[.='LOGIN']"));
  }

  public void clickOnSignOutButton() {
    click(By.xpath("//*[.='Sign Out']"));
  }
}
