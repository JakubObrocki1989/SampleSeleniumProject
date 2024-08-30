package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class LoginSignupPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement nameInput;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement mailInput;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signupButton;
    @FindBy(xpath = "//div[@class='signup-form']/h2")
    WebElement signupHeader;
    @FindBy(xpath = "//div[@class='signup-form']//p")
    WebElement signupEmailExistErrorHeader;
    @FindBy(xpath = "//input[@data-qa='login-email']")
    WebElement loginEmailInput;
    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement loginPasswordInput;
    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginButton;
    @FindBy(xpath = "//div[@class='login-form']//p")
    WebElement wrongCredentialsMessage;

    public LoginSignupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignupHeaderVisible() {
        return isElementVisible(signupHeader);
    }

    @Step("Fill signup")
    public LoginSignupPage fillSignUp(String name, String email) {
        sendKeys(nameInput, name);
        sendKeys(mailInput, email);
        takeScreenshot("Initial signup data", driver);
        return this;
    }

    @Step("Fill login")
    public LoginSignupPage fillLogin(String email, String password) {
        sendKeys(loginEmailInput, email);
        sendKeys(loginPasswordInput, password);
        return this;
    }

    @Step("Login")
    public HomePage clickLogin() {
        clickElement(loginButton);
        return new HomePage(driver);
    }

    public LoginSignupPage clickSignup() {
        clickElement(signupButton);
        return this;
    }

    public boolean isEmailExistMessageVisible() {
        return isElementVisible(signupEmailExistErrorHeader);
    }

    public String getEmailExistMessageText() {
        return signupEmailExistErrorHeader.getText();
    }

    public boolean isWrongCredentialsMessageVisible() {
        return isElementVisible(wrongCredentialsMessage);
    }

    public String getWrongCredentialsMessageText() {
        return wrongCredentialsMessage.getText();
    }
}
