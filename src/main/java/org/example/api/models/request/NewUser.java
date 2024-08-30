package org.example.api.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.base.BaseResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUser extends BaseResponse {
    String name;
    String email;
    String password;
    String title;
    String birth_date;
    String birth_month;
    String birth_year;
    String firstname;
    String lastname;
    String company;
    String address1;
    String address2;
    String country;
    String zipcode;
    String state;
    String city;
    String mobile_number;
}
