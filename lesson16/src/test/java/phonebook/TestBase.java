package phonebook;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase{

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }
    @AfterMethod(enabled = true)
    public void tearDown() {
        app.stop();
    }
}