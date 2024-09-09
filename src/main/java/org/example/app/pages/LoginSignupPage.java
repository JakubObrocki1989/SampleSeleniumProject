package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.qameta.allure.Allure.step;
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

    @Step("Checking visibility of signup header")
    public boolean isSignupHeaderVisible() {
        takeScreenshot("Signup header", driver);
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
        takeScreenshot("Login", driver);
        return this;
    }

    @Step("Login")
    public HomePage clickLogin() {
        highlight(loginButton);
        takeScreenshot("Click login button", driver);
        clickElement(loginButton);
        return new HomePage(driver);
    }

    public LoginSignupPage clickSignup() {
        step("Click signup", () -> {
            highlight(signupButton);
            takeScreenshot("Click signup button", driver);
            clickElement(signupButton);
        });
        return this;
    }

    @Step("Checking visibility of email exist message")
    public boolean isEmailExistMessageVisible() {
        takeScreenshot("Email exist", driver);
        return isElementVisible(signupEmailExistErrorHeader);
    }

    @Step("Getting email exist text")
    public String getEmailExistMessageText() {
        takeScreenshot("Email exist", driver);
        return signupEmailExistErrorHeader.getText();
    }

    @Step("Checking visibility of wrong credential message")
    public boolean isWrongCredentialsMessageVisible() {
        takeScreenshot("Wrong credentials", driver);
        return isElementVisible(wrongCredentialsMessage);
    }

    @Step("Getting wrong credentials text")
    public String getWrongCredentialsMessageText() {
        takeScreenshot("Wrong credentials", driver);
        return wrongCredentialsMessage.getText();
    }
}
