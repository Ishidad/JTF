package com.selenium.test.webtestsbase;

import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;
import com.selenium.test.configuration.LoadableComponent;
import com.selenium.test.utils.TimeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sidelnikov Mikhail on 19.09.14.
 * This is the main class for pages. When you create your page - you must extend your class from this
 */
public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    private WebDriver driver;
    private static final String BASE_URL = "https://github.com";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public T openPage(Class<T> clazz) {
        T page = PageFactory.initElements(getDriver(), clazz);
        getDriver().get(BASE_URL + getPageUrl());
        return page.get();
    }

    public abstract String getPageUrl();

    public void open(String url) {
        driver.get(url);
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void type(By inputLocator, String text) {
        find(inputLocator).sendKeys(text);
    }

    public void type(WebElement input, String text) {
        input.sendKeys(text);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void click(WebElement element) {
        element.click();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * getting webdriver instance
     * @return initialized in tests webdriver instance
     */
    protected WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }

}
