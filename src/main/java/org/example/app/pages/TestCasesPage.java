package org.example.app.pages;

import org.example.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestCasesPage extends BasePage {

    @FindBy(xpath = "//h2[@class='title text-center']")
    WebElement testCasesHeader;

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTestCasesHeaderVisible() {
        return isElementVisible(testCasesHeader);
    }

    public String getTestCasesHeaderText() {
        return testCasesHeader.getText();
    }
}
