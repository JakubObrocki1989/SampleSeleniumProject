package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreditCardDetails {
    private String cardOwner;
    private String cardNumber;
    private String cvcNumber;
    private String expiryMonth;
    private String expiryYear;
}
