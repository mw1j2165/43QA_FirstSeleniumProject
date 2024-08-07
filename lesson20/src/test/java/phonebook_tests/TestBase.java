package phonebook_tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import phonebook.core.ApplicationManager;

import java.lang.reflect.Method;

import static phonebook.core.ApplicationManager.logger;

public class TestBase {

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeSuite
  public void setUp() {
    logger.info("****************** TESTING IN PROGRESS ******************");
    app.init();
  }

  @BeforeMethod
  public void startTest(Method method) {
    logger.info("Test is started: [" + method.getName() +"]");
  }

  @AfterMethod
  public void endTest(Method method, ITestResult result) {
    if(result.isSuccess()){
      logger.info("Test is PASSED: ["+ method.getName() +"]");
    } else {
      logger.error("Test is FAILD: ["+ method.getName() +"], Screenshot: ["+ app.getUserHelper().takeScreenshot()+"]");
    }
    logger.info("Test is ended: [" + method.getName() +"]");
  }

  //@AfterMethod(enabled = true)
  @AfterSuite(enabled = true)
  public void tearDown() {
    app.stop();
    logger.info("****************** ALL TEST END ******************");
  }
}
