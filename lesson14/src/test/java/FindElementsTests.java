import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FindElementsTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app");
        driver.manage().window().maximize(); // Развернуть браузер на весь экран
    }

    @Test
    public void findElementsByTagName() {
        //? By.tagName
        WebElement h1 = driver.findElement(By.tagName("h1")); // Привести пример ошибки с H6
        //! System.out.println(h1); [[ChromeDriver: chrome on windows (0290d2af3d10aa0876bd545361cd617a)] -> tag name: h1]
        System.out.println(h1.getText());


        WebElement a = driver.findElement(By.tagName("a"));
        //! System.out.println(a); // [[ChromeDriver: chrome on windows (ba2de7836d52d4e82a59b99bc00e8ed8)] -> tag name: a]
        System.out.println("тэг 'а'" + a.getText() + "текста нет потому что это картинка"); // Элементов несколько, а первый - картинка. Поэтому у неё нет текста.
        System.out.println("тэг 'а'" + a.getSize() + "размер картинки"); // Элементов несколько, а первый - картинка. Поэтому у неё нет текста.

        // Ищем все элементы с тегом "а"
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        if (elements.size() > 2) {
            // Получаем 3-й элемент (индекс 2)
            WebElement thirdElement = elements.get(2);

            // Выводим текст 3-го элемента на печать
            System.out.println("Текст 3-го элемента: " + thirdElement.getText());
        } else {
            System.out.println("Список содержит менее 3 элементов.");
        }
        //! System.out.println(elements);
        System.out.println(elements.size());
        System.out.println(elements.get(2).getText());

    }

    //****************************************** 12 урок окончание ****************************************
    @Test // Ищем id инпута City
    public void findElementsByLocator() {
        //? By.Id
        //* #city
        //! если нашли пару ng-reflect-name="city" то делаем из неё cssSelector [ng-reflect-name='city']
        WebElement h1 = driver.findElement(By.id("city"));
        System.out.println(h1.getSize()); // Размер инпута (396, 46)


        //? By.ClassName
        //* .telephone
        WebElement phone = driver.findElement(By.className("telephone"));
        System.out.println(phone.getText()); // Распечатался телефон 866-720-5721

        //? By.linkText
        //* //a[text()=' Let the car work ']
        //* //a[.=' Let the car work ']
        //* //a[@href='/let-car-work']
        WebElement linkText = driver.findElement(By.linkText("Let the car work")); // Let the car work
        System.out.println(linkText.getText());


        //? By.partialLinkText
        //* //a[contains(text(), 'work')] их будет 2 поэтому ищем каждый
        //* (//a[contains(text(), 'work')])[1]
        WebElement partialLinkText = driver.findElement(By.partialLinkText("work")); // Let the car work
        System.out.println(partialLinkText.getText());
    }

    @Test
    public void findElementsByCssSelector() {
        //? By.cssSelector
        //* h1
        driver.findElement(By.cssSelector("h1"));

        //* city
        driver.findElement(By.cssSelector("#city")); //* #city
        driver.findElement(By.cssSelector("input#city")); //* input#city
        driver.findElement(By.cssSelector("input[id='city']")); //* точное соответствие 'city'
        driver.findElement(By.cssSelector("input[id^='cit']")); //*  начинается с 'cit_'
        driver.findElement(By.cssSelector("input[id$='ty']")); //*  заканчивается на '__ty'

        //* .telephone
        //! Но если искать через простой поиск - будет 2 элемента потому что такой текст используется в имени стиля
        driver.findElement(By.cssSelector(".telephone"));

        //! a.logo>img                   [Но таких элементов 3 на странице]
        //! a.logo img                   [Но таких элементов 3 на странице]
        //! a.logo:first-child           [Первый]
        //! a.logo:last-child            [Последний]
        //! //a[@class='logo']/img       [3 elements]
        //* (//a[@class='logo']/img)[1]  [поэтому используем такой поиск]
        driver.findElement(By.cssSelector("a.logo > img:first-child"));


        //* .pac-container.pac-logo.hdpi
        driver.findElement(By.cssSelector(".pac-container.pac-logo.hdpi"));


        //? [attr="value"]
        //* [ng-reflect-name="city"] меняем на [ng-reflect-name='city']
        driver.findElement(By.cssSelector("[ng-reflect-name=\"city\"]")); //! Среда не любит двойные кавычки
        WebElement cityCss = driver.findElement(By.cssSelector("[ng-reflect-name='city']"));
        System.out.println(cityCss.getAttribute("type")); // text
        System.out.println(cityCss.getAttribute("id")); // city
        System.out.println(cityCss.getAttribute("formcontrolname")); // city
        System.out.println(cityCss.getAttribute("ng-reflect-label")); // [object HTMLLabelElement]
        System.out.println(cityCss.getAttribute("class")); // ng-untouched ng-pristine ng-invalid pac-target-input
        System.out.println(cityCss.getAttribute("autocomplete")); // off

        //? contains
        //* меняем ng-reflect-router-link="let-car-work" на [ng-reflect-router-link='let-car-work']
        WebElement carWork = driver.findElement(By.cssSelector("[ng-reflect-router-link='let-car-work']"));
        System.out.println(carWork.getText());
        //* [ng-reflect-router-link*='-car-work']
        WebElement carWorkContains = driver.findElement(By.cssSelector("[ng-reflect-router-link*='-car-work']"));
        System.out.println(carWorkContains.getText());
        //* [ng-reflect-router-link^='let']
        WebElement carWorkStarts = driver.findElement(By.cssSelector("[ng-reflect-router-link^='let']"));
        System.out.println(carWorkStarts.getText());
    }

    //****************************************** https://demowebshop.tricentis.com/ ****************************************
    // .header (1 element. Header сайта)
    // .header>a (6 elements. Logo и текст вверху)
    // .header>a>img (1 element. Logo)
    // .header>a.logo (1 element. Logo)
    // .header>a.logo>img (1 element. Logo)
    // .header>a[href="/search"] (1 element. Search)

/* ищем дальше на сайте https://demowebshop.tricentis.com/
  идём сверху вниз
*  .ico-register
*  [class='ico-register']
*  [href='/register']

! ul (16 element)
* ul .ico-register (1 element. Register)

* показать разные варианты поиска локаторов
*/
    //****************************************** 13 урок ****************************************

    @Test
    public void findElementsByXpath() {

        //? By.xpath
        //! //*[@attr='value'] (* значит искать любые элементы, место * поставить div и он будет искать только среди div)
        //! //*[contains(@attr,'value')] --> второй вариант поиска
        // //div[@class='someClass']  ищет div элемент с классом 'someClass'
        // //*[@class='someClass']  ищет любой элемент с классом 'someClass'
        // h1 -> //h1
        driver.findElement(By.xpath("//h1"));

        //!   id -> //*[@id='value']
        //* #city --> //*[@id='city']
        driver.findElement(By.xpath("//*[@id='city']"));


        //!    class -> //*[@class='value']
        // .telephone -> //*[@class='telephone']
        driver.findElement(By.xpath("//*[@class='telephone']"));

        //! //a[text()=' Let the car work ']
        driver.findElement(By.xpath("//a[text()=' Let the car work ']")); // точное совпадение
        driver.findElement(By.xpath("//a[.=' Let the car work ']")); // точное совпадение
        driver.findElement(By.xpath("//a[contains(text(), 'work')]")); // частичное совпадение
        driver.findElement(By.xpath("(//a[contains(text(), 'work')])[1]")); // частичное совпадение
        driver.findElement(By.xpath("//a[starts-with(text(), ' Let the')]")); // начинается с


        //! //*[contains(@attr,'value')]
        driver.findElement(By.xpath("//input[contains(@id,'city')]"));


        //? //div[contains(@class,'error')] - этот элемент появляется когда вводим неверные данные
    }


    @Test
    public void siblingsTest() {
        // Находит все предыдущие элементы относительно ссылки с текстом 'Let the car work'
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding::*"));

        // Находит второй предыдущий элемент (с учетом ссылок) относительно ссылки с текстом 'Let the car work'
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding::link[2]"));

        // Находит все последующие элементы относительно ссылки с текстом 'Let the car work'
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/following::*"));

        // Находит первый предыдущий соседний элемент (тег) относительно ссылки с текстом 'Let the car work'
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding-sibling::*[1]"));
    }

    @Test
    public void siblingsTest2() {
        // Открывает страницу с указанным URL "https://ticket-service-69443.firebaseapp.com/"
        driver.get("https://ticket-service-69443.firebaseapp.com/");

        // Находит элемент с текстом 'Berlin City Hall | Events and Tickets' и классом 'mt-3'
        driver.findElement(By.xpath("//*[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));

        // Находит элемент, содержащий класс 'mt-3' и текст 'Berlin City Hall | Events and Tickets'
        driver.findElement(By.xpath("//span[contains(@class, 'mt-3') and contains(text(), 'Berlin City Hall | Events and Tickets')]"));
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
