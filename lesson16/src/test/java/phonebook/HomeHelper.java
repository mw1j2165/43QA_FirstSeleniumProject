package phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeHelper extends BaseHelper {

    public HomeHelper(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public boolean isHomeComponentPresent(){
        return isElementPresent(By.xpath("//h1[text()='Home Component']"));
    }
}