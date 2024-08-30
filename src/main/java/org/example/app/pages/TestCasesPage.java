package org.example.app.pages;

import io.qameta.allure.Step;
import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.example.core.utils.ScreenshotTaker.takeScreenshot;

public class TestCasesPage extends BasePage {

    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement testCasesHeader;

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checking visibility of test cases header")
    public boolean isTestCasesHeaderVisible() {
        takeScreenshot("Test cases header", driver);
        return isElementVisible(testCasesHeader);
    }

    @Step("Getting text of test cases header")
    public String getTestCasesHeaderText() {
        takeScreenshot("Test cases header", driver);
        return testCasesHeader.getText();
    }
}
