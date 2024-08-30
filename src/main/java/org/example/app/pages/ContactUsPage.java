package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.ContactMessage;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

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

    @Step("Checking visibility of get in touch header header")
    public boolean isGetInTouchHeaderVisible() {
        takeScreenshot("Get in touch header", driver);
        return isElementVisible(getInTouchHeader);
    }

    @Step("Getting get in touch header text")
    public String getGetInTouchHeaderText() {
        takeScreenshot("Get in touch header", driver);
        return getInTouchHeader.getText();
    }

    @Step("Fill contact us data")
    public ContactUsPage fillData(ContactMessage contactMessage) {
        sendKeys(nameInput, contactMessage.getName());
        sendKeys(emailInput, contactMessage.getEmail());
        sendKeys(subjectInput, contactMessage.getSubject());
        sendKeys(messageTextarea, contactMessage.getMessage());
        sendKeys(uploadFileInput, contactMessage.getFilePath());
        takeScreenshot("Contact us form", driver);
        clickElement(submitButton, true);
        return this;
    }

    @Step("Get success message text")
    public String getSuccessMessageText() {
        waitForPageToLoad();
        takeScreenshot("Success message text", driver);
        return successMessage.getText();
    }
}
