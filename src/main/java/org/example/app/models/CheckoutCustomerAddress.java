package org.example.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CheckoutCustomerAddress {
    private String genderFirstLastName;
    private String company;
    private String address;
    private String address2;
    private String cityStatePostalCode;
    private String country;
    private String mobile;

    public CheckoutCustomerAddress(List<String> data) {
        this.genderFirstLastName = data.get(1);
        this.company = data.get(2);
        this.address = data.get(3);
        this.address2 = data.get(4);
        this.cityStatePostalCode = data.get(5);
        this.country = data.get(6);
        this.mobile = data.get(7);
    }
}
