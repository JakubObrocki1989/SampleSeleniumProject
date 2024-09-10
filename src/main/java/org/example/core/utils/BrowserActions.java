package org.example.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.core.config.Configuration;
import org.example.core.models.config.BrowserConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BrowserActions {
    private static final BrowserConfig config = Configuration.getBrowserConfig();
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected JavascriptExecutor jse;
    Actions actions;

    public BrowserActions(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.getWebElement().getTimeout()), Duration.ofMillis(config.getWebElement().getPolling()));
        this.actions = new Actions(driver);
        this.jse = (JavascriptExecutor) driver;
    }

    public void waitForPageToLoad() {
        wait.until(webDriver -> jse.executeScript("return document.readyState").equals("complete"));
    }

    public void highlight(WebElement element) {
        jse.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void clickElement(WebElement element) {
        clickElement(element, false);
    }

    public void clickElement(WebElement element, boolean isAlertExpected) {
        waitForElementToBeClickable(element);
        log.debug("Trying to click element at {} [size: {}]", element.getLocation().toString(), element.getSize());
        element.click();
        if (isAlertExpected) {
            acceptAlert();
        }
        waitForPageToLoad();
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sendKeys(WebElement element, String value) {
        log.info("Sending text {} to element {}", value, element.toString());
        if (value == null) {
            log.info("Value is empty, skipping.");
        }
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementVisible(WebElement element) {
        log.info("Checking visibility of {}", element.toString());
        waitForElementToBeDisplayed(element);
        return element.isDisplayed();
    }

    public void selectByText(WebElement element, String text) {
        clickElement(element);
        List<WebElement> selectOptions = element.findElements(By.xpath("./option"));
        clickElement(selectOptions.stream().filter(o -> o.getText().equals(text)).findFirst().get());
    }

    public void selectCheckbox(WebElement element, boolean value) {
        if (element.isSelected() != value) {
            element.click();
        }
    }

    private boolean isAlertPresent() {
        try {
            return wait.withTimeout(Duration.ofSeconds(5))
                    .ignoring(NoAlertPresentException.class)
                    .until(driver -> {
                        driver.switchTo().alert();
                        return true;
                    });
        } catch (TimeoutException | NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void scrollToElement(WebElement element) {
        actions.scrollToElement(element).perform();
    }
}
