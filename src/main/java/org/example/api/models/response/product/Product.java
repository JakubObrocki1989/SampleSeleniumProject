package org.example.api.models.response.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.base.BaseResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends BaseResponse {
    @JsonProperty("brand")
    String brand;
    @JsonProperty("category")
    Category category;
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("price")
    String price;
}
