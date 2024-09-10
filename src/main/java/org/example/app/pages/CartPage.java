package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.CartItem;
import org.example.app.pages.modals.CheckoutModalPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.await;
import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class CartPage extends BasePage {

    List<CartItem> cartItemList = new ArrayList<>();

    @FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr")
    List<WebElement> cartItems;
    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    List<WebElement> deleteProductButtonList;

    HomePage homePage = new HomePage(driver);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get cart items list")
    public List<CartItem> getCartItemList() {
        CartItem.CartItemBuilder cartItemsList = CartItem.builder();
        for (WebElement element : cartItems) {
            cartItemsList.name(element.findElement(By.xpath(".//td[@class='cart_description']/h4/a")).getText());
            cartItemsList.price(element.findElement(By.xpath(".//td[@class='cart_price']/p")).getText());
            cartItemsList.quantity(element.findElement(By.xpath(".//td[@class='cart_quantity']/button")).getText());
            cartItemsList.totalPrice(element.findElement(By.xpath(".//td[@class='cart_total']/p")).getText());
            cartItemList.add(cartItemsList.build());
        }
        takeScreenshot("Cart items", driver);
        return cartItemList;
    }

    @Step("Proceed to checkout modal")
    public CheckoutModalPage proceedToCheckoutModal() {
        clickElement(proceedToCheckoutButton);
        WebElement modal = driver.findElement(By.className("modal-content"));
        return new CheckoutModalPage(driver, modal);
    }

    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        clickElement(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }

    @Step("Get products count")
    public int getProductsCount() {
        List<WebElement> cartItems = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
        takeScreenshot("Products count", driver);
        return cartItems.size();
    }

    @Step("Delete product")
    public CartPage deleteProduct() {
        int listSize = getProductsCount();
        highlight(deleteProductButtonList.get(0));
        takeScreenshot("Delete product", driver);
        clickElement(deleteProductButtonList.get(0));
        await()
                .atMost(Duration.ofSeconds(5))
                .until(() -> {
                    boolean isSizeChanged;
                    if (getProductsCount() < listSize) {
                        isSizeChanged = true;
                    } else {
                        isSizeChanged = false;
                    }
                    return isSizeChanged;
                });
        return this;
    }
}
