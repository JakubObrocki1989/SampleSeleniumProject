package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterUser {
    private String username;
    private String email;
    private String gender;
    private String password;
    private String day;
    private String month;
    private String year;
    private boolean signUpForNewsletter;
    private boolean receiveSpecialOffers;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobile;
}
