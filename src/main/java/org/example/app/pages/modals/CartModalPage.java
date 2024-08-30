package org.example.app.pages.modals;

import org.example.app.pages.CartPage;
import org.example.app.pages.ProductsPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartModalPage extends BasePage {

    @FindBy(xpath = "//div[@class='modal-footer']/button")
    WebElement continueShoppingButton;
    @FindBy(xpath = "//div[@class='modal-body']/p/a")
    WebElement viewCartLink;

    public CartModalPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ProductsPage clickContinueShopping() {
        waitForPageToLoad();
        clickElement(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CartPage clickViewCart() {
        waitForPageToLoad();
        clickElement(viewCartLink);
        return new CartPage(driver);
    }
}
