package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class AccountDeletedPage extends BasePage {
    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    WebElement accountDeletedHeader;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checking visibility of account deleted header")
    public boolean isAccountDeletedHeaderVisible() {
        takeScreenshot("Account deleted header", driver);
        return isElementVisible(accountDeletedHeader);
    }

    @Step("Getting account deleted header text")
    public String getAccountDeletedHeaderText() {
        takeScreenshot("Account created header", driver);
        return accountDeletedHeader.getText();
    }

    @Step("Click continue button")
    public HomePage clickContinueButton() {
        highlight(continueButton);
        takeScreenshot("Clicking continue button", driver);
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
