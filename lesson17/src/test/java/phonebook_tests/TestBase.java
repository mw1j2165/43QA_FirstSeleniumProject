package phonebook_tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import phonebook.core.ApplicationManager;

public class TestBase{

    protected static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }
    @AfterMethod(enabled = true)
    public void tearDown() {
        app.stop();
    }
}