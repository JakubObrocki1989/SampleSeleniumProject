package org.example.api.endpoints;

import io.restassured.RestAssured;
import org.example.api.base.BaseEndpoint;
import org.example.api.models.response.product.Product;

import java.util.List;

public class ProductsEndpoint extends BaseEndpoint {
    private final String endpoint = "productsList";

    public List<Product> getProducts() {
        return RestAssured.given().spec(requestSpecBuilder.build()).get(endpoint).then().assertThat().statusCode(200).extract().body().jsonPath().getList("products", Product.class);
    }
}
