package tests.ui;

import base.BaseTests;
import org.example.core.config.Configuration;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTests {

    @BeforeEach
    public void init() {
        createUserToLogin();
    }

    @Test
    public void test0001_loginWithCorrectCredentialsAndDeleteAccount() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(Configuration.getUsers("automationuser").getEmail(), Configuration.getUsers("automationuser").getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + Configuration.getUsers("automationuser").getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + Configuration.getUsers("automationuser").getUsername()));
        homePage.clickMenuOption("Delete Account");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedHeaderVisible());
        Assert.assertEquals("Account deleted message should has: ACCOUNT DELETED!' but was: " + accountDeletedPage.getAccountDeletedHeaderText(), "ACCOUNT DELETED!", accountDeletedPage.getAccountDeletedHeaderText());
        accountDeletedPage.clickContinueButton();
    }

    @Test
    public void test0002_loginWithIncorrectCredentials() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(Configuration.getUsers("wrongcredentialsuser").getEmail(), Configuration.getUsers("wrongcredentialsuser").getPassword())
                .clickLogin();
        Assert.assertTrue("Your email or password is incorrect! message should be visible, but was not",
                loginSignupPage.isWrongCredentialsMessageVisible());
        Assert.assertEquals("Your email or password is incorrect! message should has 'Your email or password is incorrect! message should' but was: " + loginSignupPage.getWrongCredentialsMessageText(), "Your email or password is incorrect!", loginSignupPage.getWrongCredentialsMessageText());
    }

    @Test
    public void test0003_loginWithCorrectCredentialsAndLogout() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption("Signup / Login");
        Assert.assertTrue("Login/Signup header should be visible, but was not.", loginSignupPage.isSignupHeaderVisible());
        loginSignupPage
                .fillLogin(Configuration.getUsers("automationuser").getEmail(), Configuration.getUsers("automationuser").getPassword())
                .clickLogin();
        Assert.assertTrue("Logged in as " + Configuration.getUsers("automationuser").getUsername() + " should be visible, but was not",
                homePage.isMenuOptionVisible("Logged in as " + Configuration.getUsers("automationuser").getUsername()));
        homePage.clickMenuOption("Logout");
    }
}
