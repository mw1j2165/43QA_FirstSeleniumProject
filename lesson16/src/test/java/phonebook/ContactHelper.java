package phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper extends BaseHelper {
    protected static final String CONTACT_NAME = "TestName";
    private static final String BUTTON_REMOVE = "//button[text()='Remove']";
    private static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";

    public ContactHelper (WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2")); // сколько h2 - столько и контактов
        for(WebElement element : contacts){
            if(element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    public void addNewContactPositiveData(String name) {
        //click on Add link
        click(By.xpath("//a[.='ADD']"));
        //enter name
        type(By.xpath("//input[@placeholder='Name']"), name);
        //enter lastname
        type(By.xpath("//input[@placeholder='Last Name']"), "TestLastName");
        //enter phone
        type(By.xpath("//input[@placeholder='Phone']"), "1234567890");
        //enter email
        type(By.xpath("//input[@placeholder='email']"), "admin@gmail.com");
        //enter address
        type(By.xpath("//input[@placeholder='Address']"), "Germany, Berlin");
        //enter description
        type(By.xpath("//input[@placeholder='description']"), "My contact test");
        //click on Save button
        click(By.xpath("//b[.='Save']"));
    }

    public int actualSizeOfContacts() {
        if(isElementPresent(By.className(CONTACT_LOCATOR))){
            return driver.findElements(By.xpath("//div[@class='contact-page_leftdiv__yhyke']//div")).size();
        }
        return 0;
    }

    public void deleteOneContactOnly() {
        click(By.className(CONTACT_LOCATOR));
        click(By.xpath("//button[text()='Remove']"));
    }

    protected void deleteAllContacts() {
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
    }

    private boolean hasContacts() {
        // Проверьте, нет ли контактов, не дожидаясь долго
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }
}
