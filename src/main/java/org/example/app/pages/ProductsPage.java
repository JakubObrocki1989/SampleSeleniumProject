package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.app.models.ProductDetails;
import org.example.app.pages.modals.CartModalPage;
import org.example.core.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.awaitility.Awaitility.await;
import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//div[@class='features_items']/h2")
    WebElement productsHeader;
    @FindBy(xpath = "//div[@class='features_items']/div[@class='col-sm-4']")
    List<WebElement> productsList;
    @FindBy(id = "search_product")
    WebElement searchInput;
    @FindBy(id = "submit_search")
    WebElement submitSearchButton;
    @FindBy(xpath = "//div[@class='panel-group category-products']//a")
    List<WebElement> categoriesList;
    @FindBy(xpath = "//div[@class='brands-name']//a")
    List<WebElement> brandsList;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductsHeaderVisible() {
        return isElementVisible(productsHeader);
    }

    public String getProductsHeaderText() {
        return productsHeader.getText();
    }

    @Step("Click on view product")
    public ProductPage clickOnViewProduct(int productIndex) {
        clickElement(productsList.get(productIndex).findElement(By.xpath(".//div[@class='choose']")));
        return new ProductPage(driver);
    }

    @Step("Get product info")
    public ProductDetails getProductInfo(int productIndex) {
        ProductDetails.ProductDetailsBuilder productDetails = ProductDetails.builder();
        productDetails.name(productsList.get(productIndex).findElement(By.xpath(".//div[@class='productinfo text-center']/p")).getText());
        productDetails.price(productsList.get(productIndex).findElement(By.xpath(".//div[@class='productinfo text-center']/h2")).getText());
        takeScreenshot("Product info", driver);
        return productDetails.build();

    }


    public CartModalPage addProductToCart(int productIndex) {
        step("Add product to cart", () -> {
            hoverOverElement(productsList.get(productIndex));
            clickElement(productsList.get(productIndex).findElement(By.xpath(".//div[@class='product-overlay']//a")));
            takeScreenshot("Add product", driver);
        });
        WebElement modal = driver.findElement(By.className("modal-content"));
        return new CartModalPage(driver, modal);
    }

    @Step("Add all visible products")
    public ProductsPage addAllVisibleProducts() {
        productsList.forEach(o -> {
            hoverOverElement(o);
            clickElement(o.findElement(By.xpath(".//div[@class='product-overlay']//a")));
            WebElement modal = driver.findElement(By.className("modal-content"));
            new CartModalPage(driver, modal).clickContinueShopping();
        });
        return this;
    }

    @Step("Search item")
    public ProductsPage searchItem(String itemName) {
        sendKeys(searchInput, itemName);
        clickElement(submitSearchButton);
        return this;
    }

    @Step("Get products count")
    public int getProductCount() {
        return productsList.size();
    }

    @Step("Open category")
    public ProductsPage openCategory(String categoryName) {
        clickElement(categoriesList.stream().filter(o -> o.getText().equals(categoryName)).findAny().get());
        return this;
    }

    @Step("Open sub-category")
    public ProductsPage openSubCategory(String subCategoryName) {
        await()
                .atMost(Duration.ofSeconds(5))
                .until(() -> {
                    boolean isListVisible;
                    List<WebElement> subCategoriesList = driver.findElements(By.xpath("//div[@class='panel-body']//a"));
                    if (!subCategoriesList.isEmpty()) {
                        isListVisible = true;
                    } else {
                        isListVisible = false;
                    }
                    return isListVisible;
                });
        List<WebElement> subCategoriesList = driver.findElements(By.xpath("//div[@class='panel-body']//a"));
        clickElement(subCategoriesList.stream().filter(o -> o.getText().equals(subCategoryName)).findAny().get());
        return this;
    }

    @Step("Open brand")
    public ProductsPage openBrand(String brandName) {
        clickElement(brandsList.stream().filter(o -> o.getText().contains(brandName)).findAny().get());
        return this;
    }
}
