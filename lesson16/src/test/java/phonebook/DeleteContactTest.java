package phonebook;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().loginExistedUserPositive();
        app.getContactHelper().addNewContactPositiveData(ContactHelper.CONTACT_NAME);
    }

    @Test(invocationCount = 1)
    public void createOneAndDeleteOneContactPositiveTest() {
        // Шаг 1: Получить текущее количество контактов
        int sizeBefore = app.getContactHelper().actualSizeOfContacts();
        // Шаг 2: Удалить контакт
        app.getContactHelper().deleteOneContactOnly();
        // Шаг 3: Получить количество контактов после удаления
        int sizeAfterDelete = app.getContactHelper().actualSizeOfContacts();
        // Шаг 4: Проверить, что количество контактов уменьшилось на 1
        Assert.assertEquals(sizeBefore, sizeAfterDelete);
    }

    @Test
    public void deleteAllContactsTests() {
        app.getContactHelper().deleteAllContacts();
        Assert.assertEquals(app.getContactHelper().actualSizeOfContacts(), 0, "Not all contacts were deleted.");
    }

}