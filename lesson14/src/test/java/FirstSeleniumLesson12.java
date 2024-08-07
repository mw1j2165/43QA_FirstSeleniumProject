
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumLesson12 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // Запускаем браузер Chrome
        driver.get("https://www.google.com"); // Не сохраняет историю
        driver.navigate().to("https://www.google.com"); // Сохраняет историю
        driver.manage().window().maximize();
        driver.navigate().back(); // Возвращает на предыдущую страницу
        driver.navigate().forward(); // Переводит вперёд
        driver.navigate().refresh(); // Обновляет страницу
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000)); // Ожидание локатора
    }

    @Test
    public void openGoogle() {
        System.out.println("Opening Google");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit(); // закрывает браузер
        //driver.close();// закрывает вкладку
    }

}
