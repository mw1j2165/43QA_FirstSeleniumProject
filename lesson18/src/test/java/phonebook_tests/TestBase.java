package phonebook_tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import phonebook.core.ApplicationManager;

import java.lang.reflect.Method;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));


  @BeforeSuite
  public void setUp() {
    app.logger.info("****************** TESTING IN PROGRESS ******************");
    app.init();
  }

  @BeforeMethod
  public void startTest(Method method) {
    app.logger.info("Test is started: [" + method.getName() +"]");
  }


  @AfterMethod
  public void endTest(Method method) {
    app.logger.info("Test is ended: [" + method.getName() +"]");
  }

  //@AfterMethod(enabled = true)
  @AfterSuite
  public void tearDown() {
    app.stop();
    app.logger.info("****************** ALL TEST END ******************");
  }
}
