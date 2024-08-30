package org.example.app.pages;

import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//ul[@id='address_delivery']//li")
    List<WebElement> deliverAddressDetails;
    @FindBy(xpath = "//ul[@id='address_invoice']//li")
    List<WebElement> invoiceAddressDetails;
    @FindBy(xpath = "//textarea[@name='message']")
    WebElement descriptionTextArea;
    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getDeliveryAddressDetails() {
        return deliverAddressDetails.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getInvoiceAddressDetails() {
        return invoiceAddressDetails.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public CheckoutPage typeDescription(String text) {
        sendKeys(descriptionTextArea, text);
        return this;
    }

    public PaymentPage clickPlaceOrder() {
        clickElement(placeOrderButton);
        return new PaymentPage(driver);
    }

}
