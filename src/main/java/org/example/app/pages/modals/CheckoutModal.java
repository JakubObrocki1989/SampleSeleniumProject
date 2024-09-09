package org.example.app.pages.modals;

import io.qameta.allure.Step;
import org.example.app.pages.LoginSignupPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutModal extends BasePage {
    @FindBy(xpath = "//div[@class='modal-footer']/button")
    WebElement continueOnCartButton;
    @FindBy(xpath = "//div[@class='modal-body']/p/a")
    WebElement registerLoginLink;

    public CheckoutModal(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @Step("Click register/login link")
    public LoginSignupPage clickRegisterLoginButton() {
        clickElement(registerLoginLink);
        return new LoginSignupPage(driver);
    }


}
