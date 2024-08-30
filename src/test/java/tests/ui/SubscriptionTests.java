package tests.ui;

import base.BaseTests;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SubscriptionTests extends BaseTests {


    @Test
    public void test0001_subscribeOnHomePage() {
        Assert.assertEquals("Subscription header should has text: SUBSCRIPTION, but was: " + homePage.getSubscriptionHeaderText(),
                "SUBSCRIPTION", homePage.getSubscriptionHeaderText());
        homePage.subscribe(faker.internet().emailAddress());
        Assert.assertEquals("Subscription message should be 'You have been successfully subscribed!' but was: " + homePage.getSubscriptionSuccessText(),
                "You have been successfully subscribed!", homePage.getSubscriptionSuccessText());
    }

    @Test
    public void test0002_subscribeOnCartPage() {
        homePage.clickMenuOption("Cart");
        Assert.assertEquals("Subscription header should has text: SUBSCRIPTION, but was: " + homePage.getSubscriptionHeaderText(),
                "SUBSCRIPTION", homePage.getSubscriptionHeaderText());
        homePage.subscribe(faker.internet().emailAddress());
        Assert.assertEquals("Subscription message should be 'You have been successfully subscribed!' but was: " + homePage.getSubscriptionSuccessText(),
                "You have been successfully subscribed!", homePage.getSubscriptionSuccessText());
    }
}
