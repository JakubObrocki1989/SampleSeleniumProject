package org.example.app.factories;

import org.example.app.models.CreditCardDetails;
import org.example.core.base.BaseFactory;

public class CreditCardDetailsFactory extends BaseFactory {
    public CreditCardDetails getRandomCreditCardDetails() {
        CreditCardDetails.CreditCardDetailsBuilder creditCardDetails = CreditCardDetails.builder();
        creditCardDetails.cardOwner(faker.name().firstName());
        creditCardDetails.cardNumber(faker.business().creditCardNumber());
        creditCardDetails.cvcNumber(String.valueOf(faker.number().numberBetween(100, 999)));
        creditCardDetails.expiryMonth(String.valueOf(faker.number().numberBetween(1, 12)));
        creditCardDetails.expiryYear(String.valueOf(faker.number().numberBetween(2024, 2030)));
        return creditCardDetails.build();
    }
}
