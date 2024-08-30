package tests.ui;

import base.BaseTests;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class HomeTests extends BaseTests {

    @Test
    @SneakyThrows
    public void test0001_scrollUpByArrowButton() {
        Assert.assertTrue(homePage.isSubscriptionHeaderVisible());
        homePage.clickScrollUpArrow();
        Assert.assertTrue(homePage.isFullFledgedHeaderVisible());
    }

    @Test
    @SneakyThrows
    public void test0002_scrollUpByScroll() {
        Assert.assertTrue(homePage.isSubscriptionHeaderVisible());
        homePage.scrollToTop();
        Assert.assertTrue(homePage.isFullFledgedHeaderVisible());
    }
}
