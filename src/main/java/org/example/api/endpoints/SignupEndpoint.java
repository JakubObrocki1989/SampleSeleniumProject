package org.example.api.endpoints;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.example.app.models.RegisterUser;
import org.example.core.config.Configuration;
import org.example.core.models.config.Environment;

public class SignupEndpoint {
    private static final String endpoint = "createAccount";
    Environment configuration = Configuration.getEnvironment();

    public SignupEndpoint() {
    }

    @SneakyThrows
    public void postSignUp() {
        RestAssured
                .given()
                .contentType("multipart/form-data")
                .baseUri(configuration.getApiUrl())
                .multiPart("name", "test")
                .multiPart("email", "automation-ui@sampledomain.com")
                .multiPart("password", "pass1723788968")
                .multiPart("title", "Mr")
                .multiPart("birth_date", "1")
                .multiPart("birth_month", "1")
                .multiPart("birth_year", "2000")
                .multiPart("firstname", "test")
                .multiPart("lastname", "last")
                .multiPart("company", "ocmp")
                .multiPart("address1", "1134 Columbia Road")
                .multiPart("address2", "most")
                .multiPart("country", "United States")
                .multiPart("zipcode", "23452")
                .multiPart("state", "texas")
                .multiPart("city", "dalas")
                .multiPart("mobile_number", "123123123")
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(200);
    }

    @SneakyThrows
    public void postSignUp(RegisterUser registerUser) {
        RestAssured
                .given()
                .contentType("multipart/form-data")
                .baseUri(configuration.getApiUrl())
                .multiPart("name", registerUser.getFirstName() + " " + registerUser.getLastName())
                .multiPart("email", registerUser.getEmail())
                .multiPart("password", registerUser.getPassword())
                .multiPart("title", registerUser.getGender())
                .multiPart("birth_date", registerUser.getDay())
                .multiPart("birth_month", registerUser.getMonth())
                .multiPart("birth_year", registerUser.getYear())
                .multiPart("firstname", registerUser.getFirstName())
                .multiPart("lastname", registerUser.getLastName())
                .multiPart("company", registerUser.getCompany())
                .multiPart("address1", registerUser.getAddress())
                .multiPart("address2", registerUser.getAddress2())
                .multiPart("country", registerUser.getCountry())
                .multiPart("zipcode", registerUser.getZipcode())
                .multiPart("state", registerUser.getState())
                .multiPart("city", registerUser.getCity())
                .multiPart("mobile_number", registerUser.getMobile())
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(200);
    }
}
