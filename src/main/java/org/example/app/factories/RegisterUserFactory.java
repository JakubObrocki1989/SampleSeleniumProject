package org.example.app.factories;

import org.example.app.models.RegisterUser;
import org.example.core.base.BaseFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RegisterUserFactory extends BaseFactory {
    public RegisterUser getRandomUser() {
        List<String> genders = Arrays.asList("Mr", "Mrs");
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "September", "October", "November", "December");
        List<String> countries = Arrays.asList("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore");
        Random rand = new Random();
        RegisterUser.RegisterUserBuilder registerUser = RegisterUser.builder();
        registerUser
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .gender(genders.get(rand.nextInt(genders.size())))
                .password(faker.internet().password())
                .day(String.valueOf(faker.number().numberBetween(1, 31)))
                .month(months.get(rand.nextInt(months.size())))
                .year(String.valueOf(faker.number().numberBetween(1900, 2024)))
                .signUpForNewsletter(true)
                .receiveSpecialOffers(true)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .company(faker.company().name())
                .address(faker.address().streetAddress())
                .address2(faker.address().buildingNumber())
                .country(countries.get(rand.nextInt(countries.size())))
                .state(faker.address().state())
                .city(faker.address().city())
                .zipcode(faker.address().zipCode())
                .mobile(faker.phoneNumber().subscriberNumber(9));
        return registerUser.build();
    }
}
