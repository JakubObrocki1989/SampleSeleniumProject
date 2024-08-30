package org.example.core.base;

import org.example.core.utils.BrowserActions;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BasePage extends BrowserActions {
    protected SearchContext context;

    public BasePage(WebDriver driver) {
        super(driver);
        context = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement element) {
        super(driver);
        context = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }
}
