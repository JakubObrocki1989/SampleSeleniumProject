package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.example.core.helpers.FileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

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

    @Step("Getting success message")
    public String getSuccessMessageText() {
        waitForElementToBeDisplayed(orderPlacedText);
        highlight(orderPlacedText);
        takeScreenshot("Order placed", driver);
        return orderPlacedText.getText();
    }

    @Step("Download invoice")
    public PaymentDonePage downloadInvoice() {
        waitForElementToBeClickable(downloadInvoiceButton);
        clickElement(downloadInvoiceButton);
        FileHandler.waitForDownload("invoice.txt");
        return this;
    }

    @Step("Click continue button")
    public HomePage clickContinueButton() {
        clickElement(continueButton);
        return new HomePage(driver);
    }
}
