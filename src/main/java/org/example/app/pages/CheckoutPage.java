package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

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

    @Step("Get deliver address details")
    public List<String> getDeliveryAddressDetails() {
        takeScreenshot("Deliver address", driver);
        return deliverAddressDetails.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Get invoice address details")
    public List<String> getInvoiceAddressDetails() {
        takeScreenshot("Invoice address", driver);
        return invoiceAddressDetails.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Step("Type description")
    public CheckoutPage typeDescription(String text) {
        sendKeys(descriptionTextArea, text);
        takeScreenshot("Description", driver);
        return this;
    }

    @Step("Place order")
    public PaymentPage clickPlaceOrder() {
        clickElement(placeOrderButton);
        return new PaymentPage(driver);
    }

}
