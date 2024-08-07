package phonebook.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MyListener implements WebDriverListener {
  Logger logger = LoggerFactory.getLogger(MyListener.class);

  @Override
  public void beforeFindElement(WebDriver driver, By locator) {
    WebDriverListener.super.beforeFindElement(driver, locator);
    logger.info("Before find Element ==>> "+locator);
  }

  @Override
  public void afterFindElement(WebDriver driver, By locator, WebElement result) {
    WebDriverListener.super.afterFindElement(driver, locator, result);
    logger.info("After find Element ==>> "+locator);
  }

  @Override
  public void beforeFindElements(WebDriver driver, By locator) {
    WebDriverListener.super.beforeFindElements(driver, locator);
    logger.info("Before find Elements ==>> "+locator);
  }

  @Override
  public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
    WebDriverListener.super.afterFindElements(driver, locator, result);
    logger.info("After find Elements ==>> "+locator);
    logger.info("After find Elements ==>> "+result.size());
  }

  @Override
  public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
    WebDriverListener.super.onError(target, method, args, e);
    logger.error("Что-то пошло не так!!");
    logger.error("***************************************************");
    logger.error("Method ==>> " + method.getName());
    logger.error("***************************************************");
    logger.error("Object target ==>> " + target.toString());
    logger.error("***************************************************");
    logger.error("Target exception ==>> " + e.getTargetException());
    logger.error("***************************************************");
  }

  @Override
  public void beforeGet(WebDriver driver, String url) {
    WebDriverListener.super.beforeGet(driver, url);
    logger.info("Before get url ==>> "+url);
  }

  @Override
  public void afterGet(WebDriver driver, String url) {
    WebDriverListener.super.afterGet(driver, url);
    logger.info("After get url ==>> "+url);
  }
}
