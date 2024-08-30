package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {
    private String name;
    private String email;
    private String review;
}
