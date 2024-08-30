package tests.ui;

import base.BaseTests;
import org.example.app.models.CartItem;
import org.example.app.models.ProductDetails;
import org.example.core.config.Configuration;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CartTests extends BaseTests {

    @Test
    public void test0001_addProductsCheckCartLoginAndCheckCartAgain() {
        createUserToLogin();
        homePage.clickMenuOption(" Products");
        Assert.assertEquals("There should be 2 product but was: " + productsPage.getProductCount(), 2, productsPage.searchItem("Top for women").getProductCount());
        productsPage.addAllVisibleProducts();
        homePage.clickMenuOption("Cart");
        Assert.assertEquals("Product list size should be: 2 but was: " + cartPage.getProductsCount(), 2, cartPage.getProductsCount());
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(Configuration.getUsers("automationuser").getEmail(), Configuration.getUsers("automationuser").getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + Configuration.getUsers("automationuser").getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + Configuration.getUsers("automationuser").getUsername()));
        homePage.clickMenuOption("Cart");
        Assert.assertEquals("Product list size should be: 2 but was: " + cartPage.getProductsCount(), 2, cartPage.getProductsCount());
    }

    @Test
    public void test0002_addRecomendedItemsToCart() {
        ProductDetails productDetails = homePage.getProductDetails();
        homePage.addRecomenndedItemToCart().clickViewCart();
        CartItem cartItem = cartPage.getCartItemList().stream().findFirst().get();
        assertEquals(productDetails.getName(), "Product name", cartItem.getName());
        assertEquals(productDetails.getPrice(), "Item price", cartItem.getPrice());
        assertEquals(Integer.parseInt(productDetails.getPrice().replace("Rs. ", "")), "Item total price", Integer.parseInt(cartItem.getPrice().replace("Rs. ", "")) * Integer.parseInt(cartItem.getQuantity()));
        assertAll();
    }
}
