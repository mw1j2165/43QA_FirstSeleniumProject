package phonebook_tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountPositiveTests extends TestBase {

    @Test
    public void createAccountPositiveTest() {
// click on Login link
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
// enter email
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("user_admin_new2@gmail.com");
// enter password
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
// click on the Registration button
        app.driver.findElement(By.name("registration")).click();
        System.out.println("Button 'registration' is pressed and User is registered successfully");
// Assert that button SignOut is present
        app.getUserHelper().isSignOutButtonPresent();
    }

    @Test
    public void createAccountPositiveTestRefactor() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().type(By.name("email"),"user_admin_new3@gmail.com");
        app.getUserHelper().type(By.name("password"),"Password@1");
        app.getUserHelper().click(By.name("registration"));
        app.getUserHelper().isSignOutButtonPresent();
    }

    @Test
    public void createExistedAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().type(By.name("email"),"user_admin_new3@gmail.com"); //! Должен быть уже существующий юзер, чтобы перехватывать SoftAssert
        app.getUserHelper().type(By.name("password"),"Password@1");
        app.getUserHelper().click(By.name("registration"));
        //Assert.assertTrue(isAlertPresent());
        /*
         * В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
         * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
         * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        //Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertTrue(app.getUserHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
        /*
         *  Назначение: assertAll() используется для проверки всех утверждений, сделанных с помощью SoftAssert, в конце теста.
         * Если одно или несколько утверждений не прошли, assertAll() вызовет исключение и тест будет помечен как неудавшийся
         */
    }
}
