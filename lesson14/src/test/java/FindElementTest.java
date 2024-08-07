
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindElementTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app");
        driver.manage().window().setPosition(new Point(2500, 0)); // Подвинуть окно браузера на 2500 пикселей вправо, чтобы он запускался на втором мониторе
        driver.manage().window().maximize(); // На весь экран развернуть браузер
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Ожидание локатора
    }

    // Ищем локатор элемента по тэгу в DOM-дереве сайта
    @Test
    public void testFindElementsTagName() {
        // Локатор с тегом <h1>
        WebElement h1 = driver.findElement(By.tagName("h1"));
        System.out.println(h1.getText());

        // Локатор с тегом <а> это картинка в этом случае
        WebElement a = driver.findElement(By.tagName("a"));
        System.out.println(a.getSize()); // Узнаём разрешение картинки в состоянии рендера

        // Массив локаторов с тегом <а>
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        // System.out.println(elements_a); Распечатает нам все элементы массива - что лишнее
        System.out.println(elements_a.size()); // Узнаём размер массива 31 элемента на странице с тегом <а>
        System.out.println(elements_a.get(2).getText());

    }
    @Test
    public void testFindElementByLocator(){
        driver.findElement(By.cssSelector("#city"));

    }
    @AfterMethod(enabled = true) // включение или отключения закрытия браузера после тестов
    public void tearDown() {
        driver.quit();
    }
}


