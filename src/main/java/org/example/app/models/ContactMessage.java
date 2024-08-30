package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactMessage {
    private String name;
    private String email;
    private String subject;
    private String message;
    private String filePath;
}
