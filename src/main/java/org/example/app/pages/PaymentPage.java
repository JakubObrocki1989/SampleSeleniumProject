package org.example.app.pages;

import org.example.app.models.CreditCardDetails;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    WebElement nameOnCardInput;
    @FindBy(xpath = "//input[@data-qa='card-number']")
    WebElement cardNumberInput;
    @FindBy(xpath = "//input[@data-qa='cvc']")
    WebElement cvcInput;
    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    WebElement expiryMonthInput;
    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    WebElement expiryYearInput;
    @FindBy(id = "submit")
    WebElement placeAndConfirmButton;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage fillCardDetail(CreditCardDetails creditCardDetails) {
        sendKeys(nameOnCardInput, creditCardDetails.getCardOwner());
        sendKeys(cardNumberInput, creditCardDetails.getCardNumber());
        sendKeys(cvcInput, creditCardDetails.getCvcNumber());
        sendKeys(expiryMonthInput, creditCardDetails.getExpiryMonth());
        sendKeys(expiryYearInput, creditCardDetails.getExpiryYear());
        return this;
    }

    public PaymentDonePage clickPlaceAndConfirmButton() {
        clickElement(placeAndConfirmButton);
        return new PaymentDonePage(driver);
    }
}
