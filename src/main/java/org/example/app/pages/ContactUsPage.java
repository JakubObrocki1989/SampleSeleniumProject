package org.example.app.pages;

import org.example.app.models.ContactMessage;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {

    @FindBy(xpath = "//div[@class='contact-form']//h2")
    WebElement getInTouchHeader;
    @FindBy(xpath = "//input[@data-qa='name']")
    WebElement nameInput;
    @FindBy(xpath = "//input[@data-qa='email']")
    WebElement emailInput;
    @FindBy(xpath = "//input[@data-qa='subject']")
    WebElement subjectInput;
    @FindBy(xpath = "//textarea[@data-qa='message']")
    WebElement messageTextarea;
    @FindBy(xpath = "//input[@name='upload_file']")
    WebElement uploadFileInput;
    @FindBy(xpath = "//input[@data-qa='submit-button']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='status alert alert-success']")
    WebElement successMessage;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGetInTouchHeaderVisible() {
        return isElementVisible(getInTouchHeader);
    }

    public String getGetInTouchHeaderText() {
        return getInTouchHeader.getText();
    }

    public ContactUsPage fillData(ContactMessage contactMessage) {
        sendKeys(nameInput, contactMessage.getName());
        sendKeys(emailInput, contactMessage.getEmail());
        sendKeys(subjectInput, contactMessage.getSubject());
        sendKeys(messageTextarea, contactMessage.getMessage());
        sendKeys(uploadFileInput, contactMessage.getFilePath());
        clickElement(submitButton, true);
        return this;
    }

    public String getSuccessMessageText() {
        waitForPageToLoad();
        return successMessage.getText();
    }
}
