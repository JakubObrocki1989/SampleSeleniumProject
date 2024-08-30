package tests.ui;

import base.BaseTests;
import lombok.extern.slf4j.Slf4j;
import org.example.app.factories.ReviewFactory;
import org.example.app.models.CartItem;
import org.example.app.models.ProductDetails;
import org.example.app.models.Review;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class ProductsTests extends BaseTests {
    ReviewFactory reviewFactory = new ReviewFactory();
    Review review = reviewFactory.getRandomReview();

    @Test
    public void test0001_openAndVerifyProductDetail() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption(" Products");
        productsPage.clickOnViewProduct(0);
        assertIsNotEmpty(productPage.getProductName(), "Product name");
        assertIsNotEmpty(productPage.getProductCategory(), "Product category");
        assertIsNotEmpty(productPage.getProductPrice(), "Product price");
        assertIsNotEmpty(productPage.getProductAvailability(), "Product availability");
        assertIsNotEmpty(productPage.getProductCondition(), "Product condition");
        assertIsNotEmpty(productPage.getProductBrand(), "Product brand");
        assertAll();
    }

    @Test
    public void test0002_searchProduct() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption(" Products");
        Assert.assertEquals("There should be 1 product but was: " + productsPage.getProductCount(), 1, productsPage.searchItem("Sleeveless Dress").getProductCount());
    }

    @Test
    public void test0003_addProductsToCart() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption(" Products");
        List<ProductDetails> productInfo = List.of(productsPage.getProductInfo(1), productsPage.getProductInfo(2));
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickViewCart();
        List<CartItem> cartItems = cartPage.getCartItemList();
        Assert.assertEquals("Product list and cart item list should have the same size", productInfo.size(), cartItems.size());
        for (int i = 0; i < productInfo.size(); i++) {
            assertEquals(productInfo.get(i).getName(), "Product name", cartItems.get(i).getName());
            assertEquals(productInfo.get(i).getPrice(), "Item price", cartItems.get(i).getPrice());
            assertEquals(Integer.parseInt(productInfo.get(i).getPrice().replace("Rs. ", "")), "Item total price", Integer.parseInt(cartItems.get(i).getPrice().replace("Rs. ", "")) * Integer.parseInt(cartItems.get(i).getQuantity()));
        }
        assertAll();
    }

    @Test
    public void test0004_checkProductQuantityInCart() {
        homePage.clickMenuOption(" Products");
        String currentQuantity = productsPage
                .clickOnViewProduct(3)
                .setQuantity("4")
                .addToCart()
                .clickViewCart()
                .getCartItemList().get(0).getQuantity();
        Assert.assertEquals("Chosen quantity should be 4 but was: " + cartPage.getCartItemList().get(0).getQuantity(), "4", currentQuantity);
    }

    @Test
    public void test0005_checkSubCategoryHeaders() {
        homePage.clickMenuOption(" Products");
        productsPage.openCategory("WOMEN").openSubCategory("TOPS");
        Assert.assertEquals("Producs header should be: 'WOMEN - TOPS PRODUCTS' but was: " + productsPage.getProductsHeaderText(),
                "WOMEN - TOPS PRODUCTS", productsPage.getProductsHeaderText());
        productsPage.openCategory("MEN").openSubCategory("JEANS");
        Assert.assertEquals("Producs header should be: 'Men - Jeans Products' but was: " + productsPage.getProductsHeaderText(),
                "MEN - JEANS PRODUCTS", productsPage.getProductsHeaderText());
    }

    @Test
    public void test0006_checkBrandHeaders() {
        homePage.clickMenuOption(" Products");
        productsPage.openBrand("POLO");
        Assert.assertEquals("Producs header should be: 'BRAND - POLO PRODUCTS' but was: " + productsPage.getProductsHeaderText(),
                "BRAND - POLO PRODUCTS", productsPage.getProductsHeaderText());
        productsPage.openBrand("BABYHUG");
        Assert.assertEquals("Producs header should be: 'Brand - Babyhug Products' but was: " + productsPage.getProductsHeaderText(),
                "BRAND - BABYHUG PRODUCTS", productsPage.getProductsHeaderText());
    }

    @Test
    public void test0007_writeReviewForProduct() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption(" Products");
        productsPage.clickOnViewProduct(0).writeAReview(review);
        Assert.assertEquals("Alert message should be: 'Thank you for your review.', but was: " + productPage.getReviewAddedText(),
                "Thank you for your review.",
                productPage.getReviewAddedText());
    }
}
