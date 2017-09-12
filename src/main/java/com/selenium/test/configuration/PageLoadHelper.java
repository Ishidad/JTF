package com.selenium.test.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;

/**
 * PageLoadHelper
 *
 * Created by Sargis on 4/3/16.
 */
public class PageLoadHelper {
    public static PageLoadHelper isLoaded() {
        PageLoadHelper loadHelper = new PageLoadHelper();
        return loadHelper;
    }


    public PageLoadHelper isElementClickable(By by) {
        try {
            new WebDriverWait(getDriver(), 10).until(ExpectedConditions.elementToBeClickable(by));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not clickable");
        }
    }

    public PageLoadHelper isElementVisible(By by) {
        try {
            new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(by));
            return this;
        } catch (WebDriverException e) {
            throw new Error("Element is not visible");
        }
    }
}
