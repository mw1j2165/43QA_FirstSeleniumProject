import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase {
    WebDriver driver;
    WebDriverWait wait;
    public static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize(); // Развернуть браузер на весь экран
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1)); // Ожидание локатора
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.quit();
    }

    public boolean isElementPresent(By locator){
        System.out.println("Проверка есть ли элемент [" + locator + "] на странице");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        try {
            // Пытаемся кликнуть, если элемент кликабелен
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (TimeoutException te) {
            // Обработка исключения, если время ожидания истекло
            System.out.println("Время ожидания элемента истекло: " + locator);
            throw te;
        } catch (WebDriverException e) {
            // Если элемент не кликабелен, проверяем, видим ли он
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                // Если элемент видим, пытаемся снова кликнуть
                driver.findElement(locator).click();
            } catch (WebDriverException ex) {
                // Логирование или другая обработка исключения
                System.out.println("Не удалось кликнуть по элементу: " + locator);
                throw ex;
            }
        }
    }

    protected boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if(alert == null){
            return false;
        }else{
            driver.switchTo().alert().accept();
            return true;
        }
    }

    protected void loginExistedUserPositive() {
        clickLoginLinkButton();
        type(By.name("email"),"user_admin_new3@gmail.com");
        type(By.name("password"),"Password@1");
        click(By.name("login"));
    }

    private void clickLoginLinkButton() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    protected boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2")); // сколько h2 - столько и контактов
        for(WebElement element : contacts){
            if(element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    protected void addNewContactPositiveData(String name) {
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

    protected int actualSizeOfContacts() {
        if(isElementPresent(By.className(CONTACT_LOCATOR))){
            return driver.findElements(By.xpath("//div[@class='contact-page_leftdiv__yhyke']//div")).size();
        }
        return 0;
    }
}
