package org.example.app.pages.modals;

import io.qameta.allure.Step;
import org.example.app.pages.CartPage;
import org.example.app.pages.ProductsPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.qameta.allure.Allure.step;
import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class CartModalPage extends BasePage {

    @FindBy(xpath = "//div[@class='modal-footer']/button")
    WebElement continueShoppingButton;
    @FindBy(xpath = "//div[@class='modal-body']/p/a")
    WebElement viewCartLink;

    public CartModalPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ProductsPage clickContinueShopping() {
        step("Click continue shopping", () -> {
            waitForPageToLoad();
            waitForElementToBeClickable(continueShoppingButton);
            highlight(continueShoppingButton);
            takeScreenshot("Continue shopping", driver);
            clickElement(continueShoppingButton);
        });
        return new ProductsPage(driver);
    }

    @Step("View cart")
    public CartPage clickViewCart() {
        step("View cart", () -> {
            waitForPageToLoad();
            waitForElementToBeClickable(viewCartLink);
            highlight(viewCartLink);
            takeScreenshot("View cart", driver);
            clickElement(viewCartLink);

        });
        return new CartPage(driver);
    }
}
