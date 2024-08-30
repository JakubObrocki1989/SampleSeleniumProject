package org.example.app.pages;

import org.example.core.base.BasePage;
import org.example.core.helpers.FileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentDonePage extends BasePage {
    @FindBy(xpath = "//h2[@data-qa='order-placed']/following-sibling::p")
    WebElement orderPlacedText;
    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement downloadInvoiceButton;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessageText() {
        waitForElementToBeDisplayed(orderPlacedText);
        highlight(orderPlacedText);
        return orderPlacedText.getText();
    }

    public PaymentDonePage downloadInvoice() {
        waitForElementToBeClickable(downloadInvoiceButton);
        clickElement(downloadInvoiceButton);
        FileHandler.waitForDownload("invoice.txt");
        return this;
    }

    public HomePage clickContinueButton() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
