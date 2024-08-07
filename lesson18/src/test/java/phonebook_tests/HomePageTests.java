package phonebook_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
  @BeforeMethod
  public void ensurePrecondition(){
    if (!app.getHomeHelper().isHomeComponentPresent()) {
      app.getHomeHelper().clickOnHomeLink();
    }
  }

  @Test
  public void homePageTest(){
    Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent());
  }
}
