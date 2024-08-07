import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize(); // Развернуть браузер на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Ожидание локатора
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public boolean isElementPresent(By locator){
        System.out.println("Проверка есть ли элемент ["+locator+"] на странице");
        return driver.findElements(locator).size()>0;
    }
}

