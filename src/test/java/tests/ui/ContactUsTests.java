package tests.ui;

import base.BaseTests;
import org.example.app.factories.ContactMessageFactory;
import org.example.app.models.ContactMessage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ContactUsTests extends BaseTests {

    ContactMessageFactory contactMessageFactory = new ContactMessageFactory();
    ContactMessage contactMessage = contactMessageFactory.getContactUsMessage();

    @Test
    public void test0001_messageShouldBeSentSuccessfully() {
        Assert.assertTrue(homePage.isLogoVisible());
        homePage.clickMenuOption("Contact us");
        Assert.assertTrue("Get in touch header should be visible, but was not.", contactUsPage.isGetInTouchHeaderVisible());
        Assert.assertEquals("Header should has: GET IN TOUCH but was:" + contactUsPage.getGetInTouchHeaderText(), "GET IN TOUCH", contactUsPage.getGetInTouchHeaderText());
        Assert.assertEquals("Message should has: 'Success! Your details have been submitted successfully.' but was " + contactUsPage.getSuccessMessageText(), "Success! Your details have been submitted successfully.", contactUsPage.fillData(contactMessage).getSuccessMessageText());
        homePage.clickMenuOption("Home");
    }
}
