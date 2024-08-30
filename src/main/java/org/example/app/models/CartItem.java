package org.example.app.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItem {
    private String name;
    private String price;
    private String quantity;
    private String totalPrice;
}
