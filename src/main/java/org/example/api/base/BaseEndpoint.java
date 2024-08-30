package org.example.api.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.example.core.config.Configuration;
import org.example.core.models.config.Environment;

public class BaseEndpoint {

    Environment configuration = Configuration.getEnvironment();
    protected ObjectMapper om = new ObjectMapper();

    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .setBaseUri(configuration.getApiUrl());
}
