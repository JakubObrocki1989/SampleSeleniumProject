package org.example.api.base;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class Endpoint<T extends BaseResponse> {
    protected final RequestSpecification requestSpecification;
    private final Class<T> responseModel;
    protected ValidatableResponse response;
    private String endpoint;

    public Endpoint() {
        responseModel = null;
        requestSpecification = getRequestSpecification();
    }

    public Endpoint(Class<T> model, String endpoint) {
        responseModel = model;
        requestSpecification = getRequestSpecification();
        this.endpoint = endpoint;
    }

    public Endpoint<T> get() {
        response = requestSpecification.when().get(endpoint).then().log().all();
        return this;
    }

    public Endpoint<T> post() {
        response = requestSpecification.when().post(endpoint).then().log().all();
        return this;
    }

    public T result() {
        return response
                .extract()
                .response()
                .body()
                .as(responseModel);
    }

    public List<T> results(String path) {
        return response
                .extract()
                .body()
                .jsonPath()
                .getList(path, responseModel);
    }

    public Endpoint<T> header(String name, String value) {
        requestSpecification.header(name, value);
        return this;
    }

    private RequestSpecification getRequestSpecification() {
        return given()
                .relaxedHTTPSValidation()
                .log().all();
    }
}
