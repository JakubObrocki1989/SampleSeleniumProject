package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.ProductDetails;
import org.example.app.pages.modals.CartModalPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class HomePage extends BasePage {

    @FindBy(className = "logo")
    WebElement logo;
    @FindBy(css = "div[class='shop-menu pull-right'] a")
    List<WebElement> shopMenu;
    @FindBy(xpath = "//div[@class='footer-widget']//h2")
    WebElement subscriptionHeader;
    @FindBy(id = "susbscribe_email")
    WebElement subscribeEmailInput;
    @FindBy(id = "subscribe")
    WebElement subscribeButton;
    @FindBy(id = "success-subscribe")
    WebElement successSubscribeMessage;
    @FindBy(xpath = "//div[@class='carousel-inner']//div[@class='single-products']")
    List<WebElement> recommendedItems;
    @FindBy(xpath = "//div[@class='single-products']//h2")
    List<WebElement> recommendedItemPrice;
    @FindBy(xpath = "//div[@class='single-products']//p")
    List<WebElement> recommendedItemName;
    @FindBy(xpath = "//div[@class='carousel-inner']//div[@class='single-products']//a[@class='btn btn-default add-to-cart']")
    List<WebElement> recommendedItemAddToCartButton;
    @FindBy(xpath = "//div[@class='carousel-inner']//h2")
    WebElement fullFledgedHeader;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoVisible() {
        waitForPageToLoad();
        return logo.isDisplayed();
    }

    @Step("Clicking menu option")
    public HomePage clickMenuOption(String menuOption) {
        WebElement menuButton = shopMenu.stream().filter(o -> o.getText().contains(menuOption)).findFirst().get();
        highlight(menuButton);
        takeScreenshot("Menu option", driver);
        clickElement(menuButton);
        return this;
    }

    public boolean isMenuOptionVisible(String menuOption) {
        return shopMenu.stream().filter(o -> o.getText().equals(menuOption)).findFirst().get().isDisplayed();
    }

    public String getSubscriptionHeaderText() {
        return subscriptionHeader.getText();
    }

    public HomePage subscribe(String email) {
        sendKeys(subscribeEmailInput, email);
        clickElement(subscribeButton);
        return this;
    }

    public String getSubscriptionSuccessText() {
        return successSubscribeMessage.getText();
    }

    public ProductDetails getProductDetails() {
        ProductDetails.ProductDetailsBuilder productDetails = ProductDetails.builder();
        productDetails.name(recommendedItems.stream().filter(WebElement::isDisplayed).findFirst().get().findElement(By.xpath(".//p")).getText());
        productDetails.price(recommendedItems.stream().filter(WebElement::isDisplayed).findFirst().get().findElement(By.xpath(".//h2")).getText());
        highlight(recommendedItems.stream().filter(WebElement::isDisplayed).findFirst().get().findElement(By.xpath(".//p")));
        highlight(recommendedItems.stream().filter(WebElement::isDisplayed).findFirst().get().findElement(By.xpath(".//h2")));
        return productDetails.build();
    }

    public CartModalPage addRecomenndedItemToCart() {
        clickElement(recommendedItems.stream().filter(WebElement::isDisplayed).findFirst().get().findElement(By.xpath(".//a[@class='btn btn-default add-to-cart']")));
        WebElement modal = driver.findElement(By.className("modal-content"));
        return new CartModalPage(driver, modal);
    }

    public boolean isSubscriptionHeaderVisible() {
        scrollToElement(subscriptionHeader);
        return isElementVisible(subscriptionHeader);
    }

    public boolean isFullFledgedHeaderVisible() {
        return isElementVisible(fullFledgedHeader);
    }

    public HomePage clickScrollUpArrow() {
        WebElement element = driver.findElement(By.id("scrollUp"));
        waitForElementToBeClickable(element);
        clickElement(element);
        return this;
    }

    public HomePage scrollToTop() {
        scrollToElement(fullFledgedHeader);
        return this;
    }
}
