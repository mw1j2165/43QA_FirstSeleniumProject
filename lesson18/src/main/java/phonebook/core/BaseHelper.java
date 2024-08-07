package phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseHelper {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BaseHelper(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public boolean isElementPresent(By locator){
    //System.out.println("Проверка есть ли элемент [" + locator + "] на странице");
    try {
      wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public void type(By locator, String text) {
    if(text != null){
      click(locator);
      driver.findElement(locator).clear();
      driver.findElement(locator).sendKeys(text);
    }
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

  public boolean isAlertPresent() {
    Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
    if(alert == null){
      return false;
    }else{
      driver.switchTo().alert().accept();
      return true;
    }
  }
}
