package org.example.api.models.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.api.base.BaseResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseResponse {
    @JsonProperty("products")
    List<Product> products;
    @JsonProperty("responseCode")
    int responseCode;
}
