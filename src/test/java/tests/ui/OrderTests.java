package tests.ui;

import base.BaseTests;
import org.example.app.factories.CreditCardDetailsFactory;
import org.example.app.factories.RegisterUserFactory;
import org.example.app.models.CheckoutCustomerAddress;
import org.example.app.models.CreditCardDetails;
import org.example.app.models.RegisterUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class OrderTests extends BaseTests {
    RegisterUserFactory registerUserFactory = new RegisterUserFactory();
    CreditCardDetailsFactory creditCardDetailsFactory = new CreditCardDetailsFactory();
    RegisterUser registerUser = registerUserFactory.getRandomUser();
    CreditCardDetails creditCardDetails = creditCardDetailsFactory.getRandomCreditCardDetails();

    @Test
    public void test0001_placeOrderRegisterWhileCheckout() {
        homePage.clickMenuOption(" Products");
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckoutModal()
                .clickRegisterLoginButton()
                .fillSignUp(registerUser.getUsername(), registerUser.getEmail())
                .clickSignup();
        Assert.assertTrue("Account created header should be visible, but was not",
                signupPage.fillSignUpDetails(registerUser).isAccountCreatedHeaderVisible());
        Assert.assertTrue("Logged in as " + registerUser.getUsername() + " should be visible, but was not",
                accountCreatedPage.clickContinueButton().isMenuOptionVisible("Logged in as " + registerUser.getUsername()));
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckout()
                .typeDescription(faker.commerce().promotionCode());
        CheckoutCustomerAddress checkout = new CheckoutCustomerAddress(checkoutPage.getDeliveryAddressDetails());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", checkout.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", checkout.getCompany());
        assertEquals(registerUser.getAddress(), "Address", checkout.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", checkout.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", checkout.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", checkout.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", checkout.getMobile());
        assertAll();
        Assert.assertEquals("Success message should be 'Congratulations! Your order has been confirmed!!'",
                "Congratulations! Your order has been confirmed!",
                checkoutPage
                        .clickPlaceOrder()
                        .fillCardDetail(creditCardDetails)
                        .clickPlaceAndConfirmButton()
                        .getSuccessMessageText());
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0002_placeOrderRegisterBeforeCheckout() {
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillSignUp(registerUser.getUsername(), registerUser.getEmail())
                .clickSignup();
        Assert.assertTrue("Enter account information header should be visible, but was not", signupPage.isEnterAccountInformationHeaderVisible());
        Assert.assertEquals("Header should has: ENTER ACCOUNT INFORMATION but was:" + signupPage.getEnterAccountInformationHeaderText(), "ENTER ACCOUNT INFORMATION", signupPage.getEnterAccountInformationHeaderText());
        Assert.assertTrue("Account created header should be visible, but was not",
                signupPage.fillSignUpDetails(registerUser).isAccountCreatedHeaderVisible());
        Assert.assertEquals("Account created message should has: ACCOUNT CREATED! but was: " + accountCreatedPage.getAccountCreatedHeaderText(), "ACCOUNT CREATED!", accountCreatedPage.getAccountCreatedHeaderText());
        Assert.assertTrue("Logged in as " + registerUser.getUsername() + " should be visible, but was not",
                accountCreatedPage.clickContinueButton().isMenuOptionVisible("Logged in as " + registerUser.getUsername()));
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckout()
                .typeDescription(faker.commerce().promotionCode());
        CheckoutCustomerAddress checkout = new CheckoutCustomerAddress(checkoutPage.getDeliveryAddressDetails());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", checkout.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", checkout.getCompany());
        assertEquals(registerUser.getAddress(), "Address", checkout.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", checkout.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", checkout.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", checkout.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", checkout.getMobile());
        assertAll();
        Assert.assertEquals("Success message should be 'Congratulations! Your order has been confirmed!!'",
                "Congratulations! Your order has been confirmed!",
                checkoutPage
                        .clickPlaceOrder()
                        .fillCardDetail(creditCardDetails)
                        .clickPlaceAndConfirmButton()
                        .getSuccessMessageText());
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0003_loginBeforeCheckout() {
        createUserToLogin(registerUser);
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(registerUser.getEmail(), registerUser.getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + registerUser.getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + registerUser.getFirstName() + " " + registerUser.getLastName()));
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckout()
                .typeDescription(faker.commerce().promotionCode());
        CheckoutCustomerAddress checkout = new CheckoutCustomerAddress(checkoutPage.getDeliveryAddressDetails());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", checkout.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", checkout.getCompany());
        assertEquals(registerUser.getAddress(), "Address", checkout.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", checkout.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", checkout.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", checkout.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", checkout.getMobile());
        assertAll();
        Assert.assertEquals("Success message should be 'Congratulations! Your order has been confirmed!!'",
                "Congratulations! Your order has been confirmed!",
                checkoutPage
                        .clickPlaceOrder()
                        .fillCardDetail(creditCardDetails)
                        .clickPlaceAndConfirmButton()
                        .getSuccessMessageText());
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0004_removeProductsFromCart() {
        homePage.clickMenuOption(" Products");
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        Assert.assertEquals("Product list size should be: 2 but was: " + cartPage.getProductsCount(), 2, cartPage.getProductsCount());
        cartPage.deleteProduct();
        Assert.assertEquals("Product list size should be: 1 but was: " + cartPage.getProductsCount(), 1, cartPage.getProductsCount());
        cartPage.deleteProduct();
        Assert.assertEquals("Product list size should be: 0 but was: " + cartPage.getProductsCount(), 0, cartPage.getProductsCount());
    }

    @Test
    public void test0005_checkBillingAddress() {
        createUserToLogin(registerUser);
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(registerUser.getEmail(), registerUser.getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + registerUser.getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + registerUser.getFirstName() + " " + registerUser.getLastName()));
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckout();
        CheckoutCustomerAddress checkout = new CheckoutCustomerAddress(checkoutPage.getDeliveryAddressDetails());
        CheckoutCustomerAddress invoice = new CheckoutCustomerAddress(checkoutPage.getInvoiceAddressDetails());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", checkout.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", checkout.getCompany());
        assertEquals(registerUser.getAddress(), "Address", checkout.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", checkout.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", checkout.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", checkout.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", checkout.getMobile());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", invoice.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", invoice.getCompany());
        assertEquals(registerUser.getAddress(), "Address", invoice.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", invoice.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", invoice.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", invoice.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", invoice.getMobile());
        assertAll();
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0006_invoiceShouldBeDownloaded() {
        clearDownloadsFolder();
        createUserToLogin(registerUser);
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(registerUser.getEmail(), registerUser.getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + registerUser.getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + registerUser.getFirstName() + " " + registerUser.getLastName()));
        productsPage
                .addProductToCart(1)
                .clickContinueShopping()
                .addProductToCart(2)
                .clickContinueShopping();
        homePage.clickMenuOption("Cart");
        cartPage
                .proceedToCheckout()
                .typeDescription(faker.commerce().promotionCode());
        CheckoutCustomerAddress checkout = new CheckoutCustomerAddress(checkoutPage.getDeliveryAddressDetails());
        assertEquals(registerUser.getGender() + ". " + registerUser.getFirstName() + " " + registerUser.getLastName(), "Gender / firstname / lastname", checkout.getGenderFirstLastName());
        assertEquals(registerUser.getCompany(), "Company", checkout.getCompany());
        assertEquals(registerUser.getAddress(), "Address", checkout.getAddress());
        assertEquals(registerUser.getAddress2(), "Address2", checkout.getAddress2());
        assertEquals(registerUser.getCity() + " " + registerUser.getState() + " " + registerUser.getZipcode(), "City / state / zipcode", checkout.getCityStatePostalCode());
        assertEquals(registerUser.getCountry(), "Country", checkout.getCountry());
        assertEquals(registerUser.getMobile(), "Mobile", checkout.getMobile());
        assertAll();
        Assert.assertEquals("Success message should be 'Congratulations! Your order has been confirmed!!'",
                "Congratulations! Your order has been confirmed!",
                checkoutPage
                        .clickPlaceOrder()
                        .fillCardDetail(creditCardDetails)
                        .clickPlaceAndConfirmButton()
                        .downloadInvoice()
                        .getSuccessMessageText());
        String invoiceContent = fileHandler.readFile("invoice.txt");
        Assert.assertTrue(invoiceContent.contains(registerUser.getFirstName()));
        Assert.assertTrue(invoiceContent.contains(registerUser.getLastName()));
        paymentDonePage.clickContinueButton().clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }
}
