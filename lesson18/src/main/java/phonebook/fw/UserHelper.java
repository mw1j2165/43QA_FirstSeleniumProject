package phonebook.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import phonebook.core.BaseHelper;
import phonebook.model.User;

public class UserHelper extends BaseHelper {
  public UserHelper(WebDriver driver, WebDriverWait wait) {
    super(driver,wait);
  }

  public void loginExistedUserPositive() {
    clickLoginLink();
    fillInRegistrationForm(new User()
        .setEmail("user_admin_new3@gmail.com")
        .setPassword("Password@1"));
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
