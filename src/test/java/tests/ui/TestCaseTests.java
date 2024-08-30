package tests.ui;

import base.BaseTests;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestCaseTests extends BaseTests {

    @Test
    public void test0001_testCasePageShouldBeOpened() {
        homePage.clickMenuOption("Test Cases");
        Assert.assertTrue("Test cases header should be visible, but was not.", testCasesPage.isTestCasesHeaderVisible());
        Assert.assertEquals("Test cases header should be 'TEST CASES' but was: " + testCasesPage.getTestCasesHeaderText(), "TEST CASES", testCasesPage.getTestCasesHeaderText());
    }
}
