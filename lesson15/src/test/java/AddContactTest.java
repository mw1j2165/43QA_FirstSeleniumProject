import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTest extends TestBase {
    private static final String CONTACT_NAME = "TestName";
    private static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    @BeforeMethod
    public void precondition() {
        loginExistedUserPositive();
    }

    @Test(invocationCount = 5)
    public void addContactPositiveTest() {
        addNewContactPositiveData(CONTACT_NAME);
        //! Добавляем проверку, что контакт появился
        Assert.assertTrue(isContactAdded(CONTACT_NAME));
    }

    @AfterMethod(enabled = true)
    public void postCondition() {
        //Click in card
        click(By.xpath(CONTACT_LOCATOR)); // Все карточки имеют такой локатор
        //click on remove button
        click(By.xpath("//button[.='Remove']"));
    }
}
