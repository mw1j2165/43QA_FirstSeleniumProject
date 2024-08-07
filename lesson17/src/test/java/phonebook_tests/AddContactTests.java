package phonebook_tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import phonebook.model.Contact;

public class AddContactTests extends TestBase {
    private static final String CONTACT_NAME = "TestName";
    private static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().loginExistedUserPositive();
    }

    @Test(invocationCount = 5)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        //! Добавляем проверку, что контакт появился
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test
    public void addContactWithOutDescription(){
        app.getContactHelper().clickADDLink();
        app.getContactHelper().fillAddContactForm(new Contact()
                .setName(CONTACT_NAME)
                .setLastName("TestLastName")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Berlin"));
        //app.getContactHelper().clickOnSAVEContactButton();
    }

    @AfterMethod(enabled = true)
    public void postCondition() {
        //Click in card
        app.getContactHelper().click(By.xpath(CONTACT_LOCATOR)); // Все карточки имеют такой локатор
        //click on remove button
        app.getContactHelper().click(By.xpath("//button[.='Remove']"));
    }
}

