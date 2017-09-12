package com.selenium.test.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.util.concurrent.TimeUnit;
import static com.selenium.test.webtestsbase.WebDriverFactory.getDriver;

/**
 * Custom Loadable Component
 *
 * Created by sargis on 4/3/16.
 */
public abstract class LoadableComponent<T extends LoadableComponent<T>> {
    private WebDriver driver;

    private static final int LOAD_TIMEOUT = 30;
    private static final int REFRESH_RATE = 2;

    @SuppressWarnings("unchecked")
    public T get() {
        try {
            isLoaded();
            return (T) this;
        } catch (Error e) {
            // This is the extra line of code
            System.out.println("Error encountered during page load: " + e.getMessage());
            load();
        }

        isLoaded();

        return (T) this;
    }

    protected abstract void load();

    protected abstract void isLoaded() throws Error;

    protected void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        Wait wait = new FluentWait(getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

        wait.until(pageLoadCondition);
    }

}