package base;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.example.api.factories.DataFactory;
import org.example.app.models.RegisterUser;
import org.example.app.pages.*;
import org.example.core.config.Configuration;
import org.example.core.driver.DriverFactory;
import org.example.core.helpers.FileHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Slf4j
public abstract class BaseTests {
    protected Faker faker = new Faker();
    protected FileHandler fileHandler = new FileHandler();

    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginSignupPage loginSignupPage;
    protected SignupPage signupPage;
    protected AccountCreatedPage accountCreatedPage;
    protected AccountDeletedPage accountDeletedPage;
    protected ContactUsPage contactUsPage;
    protected ProductsPage productsPage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected PaymentPage paymentPage;
    protected TestCasesPage testCasesPage;
    protected PaymentDonePage paymentDonePage;
    protected SoftAssertions softAssertions = new SoftAssertions();


    static public void createUserToLogin() {
        DataFactory.createAutomationTestsUser();
    }

    static public void createUserToLogin(RegisterUser registerUser) {
        DataFactory.createAutomationTestsUser(registerUser);
    }

    public void clearDownloadsFolder() {
        fileHandler.deleteFiles(Configuration.getDownloadPath());
    }

    public void assertIsNotEmpty(String value, String descritption) {
        softAssertions.assertThat(value).as(descritption).isNotEmpty();
    }

    public void assertEquals(String expected, String message, String current) {
        softAssertions.assertThat(current).as(message).isEqualTo(expected);
    }

    public void assertEquals(int expected, String message, int current) {
        softAssertions.assertThat(current).as(message).isEqualTo(expected);
    }

    public void assertAll() {
        softAssertions.assertAll();
        softAssertions = new SoftAssertions();
    }

    @BeforeEach
    public void setupTest() {
        driver = DriverFactory.create();
        driver.get(Configuration.getEnvironment().getAppUrl());
        homePage = new HomePage(driver);
        loginSignupPage = new LoginSignupPage(driver);
        signupPage = new SignupPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        accountDeletedPage = new AccountDeletedPage(driver);
        contactUsPage = new ContactUsPage(driver);
        productsPage = new ProductsPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
        testCasesPage = new TestCasesPage(driver);
        paymentDonePage = new PaymentDonePage(driver);
    }

    @AfterEach
    void teardown() {
        DriverFactory.closeDriver();
    }
}
