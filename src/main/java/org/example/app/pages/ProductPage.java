package org.example.app.pages;

import org.example.app.models.Review;
import org.example.app.pages.modals.CartModalPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getProductName() {
        return productName.getText();
    }

    public String getProductCategory() {
        return productCategory.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductAvailability() {
        return productAvailability.getText();
    }

    public String getProductCondition() {
        return productCondition.getText();
    }

    public String getProductBrand() {
        return productBrand.getText();
    }


    public ProductPage setQuantity(String quantity) {
        sendKeys(quantityInput, quantity);
        return this;
    }

    public CartModalPage addToCart() {
        clickElement(addToCartButton);
        WebElement modal = driver.findElement(By.className("modal-content"));
        return new CartModalPage(driver, modal);
    }

    public ProductPage writeAReview(Review review) {
        sendKeys(nameInput, review.getName());
        sendKeys(emailInput, review.getEmail());
        sendKeys(reviewTextarea, review.getReview());
        clickElement(submitButton);
        return this;
    }

    public String getReviewAddedText() {
        return successAlert.getText();
    }
}
