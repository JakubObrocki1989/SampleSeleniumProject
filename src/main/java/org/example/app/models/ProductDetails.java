package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDetails {
    private String name;
    private String price;

}
