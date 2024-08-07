package phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import phonebook.fw.ContactHelper;
import phonebook.fw.HomeHelper;
import phonebook.fw.UserHelper;

import java.time.Duration;

    public class ApplicationManager {
    public WebDriver driver;
    WebDriverWait wait;

    UserHelper userHelper;
    HomeHelper homeHelper;
    ContactHelper contactHelper;

    public void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize(); // Развернуть браузер на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // Ожидание локатора
        userHelper = new UserHelper(driver, wait);
        homeHelper = new HomeHelper(driver, wait);
        contactHelper = new ContactHelper(driver, wait);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public HomeHelper getHomeHelper() {
        return homeHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void stop() {
        driver.quit();
    }
}