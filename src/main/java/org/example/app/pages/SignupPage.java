package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.RegisterUser;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class SignupPage extends BasePage {

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement enterAccountInformationHeader;
    @FindBy(xpath = "//input[@type='radio']")
    List<WebElement> genderRadios;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInput;
    @FindBy(xpath = "//select[@data-qa='days']")
    WebElement daysSelect;
    @FindBy(xpath = "//select[@data-qa='months']")
    WebElement monthsSelect;
    @FindBy(xpath = "//select[@data-qa='years']")
    WebElement yearsSelect;
    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;
    @FindBy(id = "optin")
    WebElement optinCheckbox;
    @FindBy(xpath = "//input[@data-qa='first_name']")
    WebElement firstNameInput;
    @FindBy(xpath = "//input[@data-qa='last_name']")
    WebElement lastNameInput;
    @FindBy(xpath = "//input[@data-qa='company']")
    WebElement companyInput;
    @FindBy(xpath = "//input[@data-qa='address']")
    WebElement addressInput;
    @FindBy(xpath = "//input[@data-qa='address2']")
    WebElement address2Input;
    @FindBy(xpath = "//select[@data-qa='country']")
    WebElement countrySelect;
    @FindBy(xpath = "//input[@data-qa='state']")
    WebElement stateInput;
    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement cityInput;
    @FindBy(xpath = "//input[@data-qa='zipcode']")
    WebElement zipcodeInput;
    @FindBy(xpath = "//input[@data-qa='mobile_number']")
    WebElement mobileNumberInput;
    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountButton;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEnterAccountInformationHeaderVisible() {
        return isElementVisible(enterAccountInformationHeader);
    }

    public String getEnterAccountInformationHeaderText() {
        return enterAccountInformationHeader.getText();
    }

    @Step("Fill signup details")
    public AccountCreatedPage fillSignUpDetails(RegisterUser registerUser) {
        genderRadios.stream().filter(o -> o.getAttribute("value").equals(registerUser.getGender())).findFirst().get().click();
        sendKeys(passwordInput, registerUser.getPassword());
        selectByText(daysSelect, registerUser.getDay());
        selectByText(monthsSelect, registerUser.getMonth());
        selectByText(yearsSelect, registerUser.getYear());
        selectCheckbox(newsletterCheckbox, registerUser.isSignUpForNewsletter());
        selectCheckbox(optinCheckbox, registerUser.isReceiveSpecialOffers());
        sendKeys(firstNameInput, registerUser.getFirstName());
        sendKeys(lastNameInput, registerUser.getLastName());
        sendKeys(companyInput, registerUser.getCompany());
        sendKeys(addressInput, registerUser.getAddress());
        sendKeys(address2Input, registerUser.getAddress2());
        selectByText(countrySelect, registerUser.getCountry());
        sendKeys(stateInput, registerUser.getState());
        sendKeys(cityInput, registerUser.getCity());
        sendKeys(zipcodeInput, registerUser.getZipcode());
        sendKeys(mobileNumberInput, registerUser.getMobile());
        takeScreenshot("Signup data", driver);
        clickElement(createAccountButton);
        return new AccountCreatedPage(driver);
    }


}
