package org.example.api.factories;

import org.example.api.endpoints.SignupEndpoint;
import org.example.app.models.RegisterUser;

public class DataFactory {
    static SignupEndpoint signupEndpoint = new SignupEndpoint();

    public static void createAutomationTestsUser() {
        signupEndpoint.postSignUp();
    }

    public static void createAutomationTestsUser(RegisterUser registerUser) {
        signupEndpoint.postSignUp(registerUser);
    }
}
