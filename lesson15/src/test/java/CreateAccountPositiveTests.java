import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountPositiveTests extends TestBase {

    @Test
    public void createAccountPositiveTest() {
// click on Login link
        driver.findElement(By.xpath("//a[.='LOGIN']")).click();
// enter email
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("user_admin_new2@gmail.com");
// enter password
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Password@1");
// click on the Registration button
        driver.findElement(By.name("registration")).click();
        System.out.println("Button 'registration' is pressed and User is registered successfully");
// Assert that button SignOut is present
        Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }

    @Test
    public void createAccountPositiveTestRefactor() {
        click(By.xpath("//a[.='LOGIN']"));
        type(By.name("email"),"user_admin_new3@gmail.com");
        type(By.name("password"),"Password@1");
        click(By.name("registration"));
        Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }

    @Test
    public void createExistedAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        click(By.xpath("//a[.='LOGIN']"));
        type(By.name("email"),"user_admin_new3@gmail.com"); //! Должен быть уже существующий юзер, чтобы перехватывать SoftAssert
        type(By.name("password"),"Password@1");
        click(By.name("registration"));
        //Assert.assertTrue(isAlertPresent());
        /*
         * В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
         * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
         * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */
        softAssert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
        /*
         *  Назначение: assertAll() используется для проверки всех утверждений, сделанных с помощью SoftAssert, в конце теста.
         * Если одно или несколько утверждений не прошли, assertAll() вызовет исключение и тест будет помечен как неудавшийся
         */
    }
}
