import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {
    private static final String BUTTON_REMOVE = "//button[text()='Remove']";
    private static final String CONTACT_NAME = "TestName";
    private static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    @BeforeMethod
    public void precondition() {
        loginExistedUserPositive();
        addNewContactPositiveData(CONTACT_NAME);
    }

    @Test(invocationCount = 15)
    public void createOneAndDeleteOneContactPositiveTest() {
        // Шаг 1: Получить текущее количество контактов
        int sizeBefore = actualSizeOfContacts();

        // Шаг 2: Удалить контакт
        click(By.className(CONTACT_LOCATOR));
        click(By.xpath("//button[text()='Remove']"));

        // Шаг 3: Получить количество контактов после удаления
        int sizeAfterDelete = actualSizeOfContacts();

        // Шаг 4: Проверить, что количество контактов уменьшилось на 1
        Assert.assertEquals(sizeBefore, sizeAfterDelete);
    }

    @Test
    public void deleteAllContacts() {
        try {
            while (hasContacts()) { // Цикл пока контакты не закончатся
                // Шаг 1: Получить текущее количество контактов
                int contactsBefore = actualSizeOfContacts();
                // Шаг 2: Выполнить удаление контакта
                click(By.className(CONTACT_LOCATOR));
                click(By.xpath(BUTTON_REMOVE));
                // Шаг 3: Ожидать, пока количество контактов на странице не станет меньше
                /*
                 * Лямбда-выражение, которое принимает экземпляр WebDriver и возвращает true или false.
                 * WebDriver d - параметр лямбда-выражения, представляющий текущий экземпляр драйвера.
                 * Условие проверяет, уменьшилось ли количество контактов на странице по сравнению с исходным значением contactsBefore
                 * */
                wait.until((WebDriver d) -> actualSizeOfContacts() < contactsBefore);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все контакты были удалены.");
        }
        // Шаг 4: Проверить, что все контакты были удалены
        Assert.assertEquals(actualSizeOfContacts(), 0, "Not all contacts were deleted.");
    }

    private boolean hasContacts() {
        // Проверьте, нет ли контактов, не дожидаясь долго
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }
}
