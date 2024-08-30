package org.example.app.factories;

import org.example.app.models.ContactMessage;
import org.example.core.base.BaseFactory;

import java.io.File;

public class ContactMessageFactory extends BaseFactory {

    public ContactMessage getContactUsMessage() {
        ContactMessage.ContactMessageBuilder contactMessage = ContactMessage.builder();
        contactMessage
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .subject(faker.commerce().productName())
                .message(faker.name().nameWithMiddle())
                .filePath(new File("src/test/resources/ContactUsAttachment.txt").getAbsolutePath());
        return contactMessage.build();
    }
}
