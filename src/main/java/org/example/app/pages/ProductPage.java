package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.Review;
import org.example.app.pages.modals.CartModalPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-information']/h2")
    WebElement productName;
    @FindBy(xpath = "//div[@class='product-information']/p")
    WebElement productCategory;
    @FindBy(xpath = "//div[@class='product-information']/span/span")
    WebElement productPrice;
    @FindBy(xpath = "(//div[@class='product-information']//p)[2]")
    WebElement productAvailability;
    @FindBy(xpath = "(//div[@class='product-information']//p)[3]")
    WebElement productCondition;
    @FindBy(xpath = "(//div[@class='product-information']//p)[4]")
    WebElement productBrand;
    @FindBy(id = "quantity")
    WebElement quantityInput;
    @FindBy(xpath = "//button[@class='btn btn-default cart']")
    WebElement addToCartButton;
    @FindBy(id = "name")
    WebElement nameInput;
    @FindBy(id = "email")
    WebElement emailInput;
    @FindBy(id = "review")
    WebElement reviewTextarea;
    @FindBy(id = "button-review")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='alert-success alert']")
    WebElement successAlert;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Getting product name")
    public String getProductName() {
        takeScreenshot("Product name", driver);
        return productName.getText();
    }

    @Step("Getting product category")
    public String getProductCategory() {
        takeScreenshot("Product category", driver);
        return productCategory.getText();
    }

    @Step("Getting product price")
    public String getProductPrice() {
        takeScreenshot("Product price", driver);
        return productPrice.getText();
    }

    @Step("Getting product availability")
    public String getProductAvailability() {
        takeScreenshot("Product availability", driver);
        return productAvailability.getText();
    }

    @Step("Getting product condition")
    public String getProductCondition() {
        takeScreenshot("Product condition", driver);
        return productCondition.getText();
    }

    @Step("Getting product brand")
    public String getProductBrand() {
        takeScreenshot("Product brand", driver);
        return productBrand.getText();
    }

    @Step("Set quantity")
    public ProductPage setQuantity(String quantity) {
        sendKeys(quantityInput, quantity);
        takeScreenshot("Quantity", driver);
        return this;
    }

    @Step("Add to cart")
    public CartModalPage addToCart() {
        clickElement(addToCartButton);
        WebElement modal = driver.findElement(By.className("modal-content"));
        return new CartModalPage(driver, modal);
    }

    @Step("Write a review")
    public ProductPage writeAReview(Review review) {
        sendKeys(nameInput, review.getName());
        sendKeys(emailInput, review.getEmail());
        sendKeys(reviewTextarea, review.getReview());
        takeScreenshot("Review", driver);
        clickElement(submitButton);
        return this;
    }

    @Step("Getting review added text")
    public String getReviewAddedText() {
        return successAlert.getText();
    }
}
