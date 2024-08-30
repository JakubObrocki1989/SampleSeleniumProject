package tests.ui;

import base.BaseTests;
import org.example.app.factories.RegisterUserFactory;
import org.example.app.models.RegisterUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class RegisterTests extends BaseTests {
    RegisterUserFactory registerUserFactory = new RegisterUserFactory();
    RegisterUser registerUser = registerUserFactory.getRandomUser();

    @Test
    public void test0001_userShouldBeRegistered() {
        Assert.assertTrue(homePage.isLogoVisible());
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
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0002_userShouldNotBeAbleToRegisterUsingExistingEmail() {
        createUserToLogin();
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillSignUp("test", "automation-ui@sampledomain.com")
                .clickSignup();
        Assert.assertTrue("Enter account information header should be visible, but was not", loginSignupPage.isEmailExistMessageVisible());
        Assert.assertEquals("Email already exist message should has : Email Address already exist! but was:" + loginSignupPage.getEmailExistMessageText(), "Email Address already exist!", loginSignupPage.getEmailExistMessageText());

    }
}
