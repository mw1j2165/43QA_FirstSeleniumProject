import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {


    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(isHomeComponentPresent(),"Элемент HomePageElement не найден на домашней странице");
        System.out.println("Элемент HomePageElement найден на домашней странице");
    }

    public boolean isHomeComponentPresent() {
        System.out.println("Ищем HomeComponent на домашней странице");
        return isElementPresent(By.xpath("//html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/h1[1]"));
    }
}

