package org.example.app.pages;

import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends BasePage {
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreatedHeader;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreatedHeaderVisible() {
        return isElementVisible(accountCreatedHeader);
    }

    public String getAccountCreatedHeaderText() {
        return accountCreatedHeader.getText();
    }

    public HomePage clickContinueButton() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
