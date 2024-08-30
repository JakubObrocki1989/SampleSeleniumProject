package org.example.api.endpoints;

import org.example.api.base.AppEndpoint;
import org.example.api.models.response.product.Product;

public class ProductsListEndpoint extends AppEndpoint<Product> {
    private static final String endpoint = "productsList";

    public ProductsListEndpoint() {
        super(Product.class, endpoint);
    }


}
