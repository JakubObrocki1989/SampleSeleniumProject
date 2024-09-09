package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class AccountCreatedPage extends BasePage {
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreatedHeader;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checking visibility of account created header")
    public boolean isAccountCreatedHeaderVisible() {
        takeScreenshot("Account created header", driver);
        return isElementVisible(accountCreatedHeader);
    }

    @Step("Getting account created header text")
    public String getAccountCreatedHeaderText() {
        takeScreenshot("Account created header", driver);
        return accountCreatedHeader.getText();
    }

    @Step("Click continue button")
    public HomePage clickContinueButton() {
        highlight(continueButton);
        takeScreenshot("Clicking continue button", driver);
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
