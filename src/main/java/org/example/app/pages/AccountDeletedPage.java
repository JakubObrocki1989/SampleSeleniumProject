package org.example.app.pages;

import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends BasePage {
    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    WebElement accountDeletedHeader;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountDeletedHeaderVisible() {
        return isElementVisible(accountDeletedHeader);
    }

    public String getAccountDeletedHeaderText() {
        return accountDeletedHeader.getText();
    }

    public HomePage clickContinueButton() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
