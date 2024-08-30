package org.example.api.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.core.config.Configuration;
import org.example.core.models.config.Environment;

import static io.restassured.config.EncoderConfig.encoderConfig;

public abstract class AppEndpoint<T extends BaseResponse> extends Endpoint<T> {
    Environment configuration = Configuration.getEnvironment();

    public AppEndpoint(Class<T> model, String endpoint) {
        super(model, endpoint);
        if (!endpoint.equals("createAccount")) {
            requestSpecification.contentType(ContentType.JSON).baseUri(configuration.getApiUrl());
        }
        requestSpecification.config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT))).baseUri(configuration.getApiUrl());
    }
}
