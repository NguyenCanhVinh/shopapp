package com.example.shopapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    @JsonProperty("order_id")
    @Min(value = 1, message = "Order Id must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "Product id must be >0")
    private Long productId;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "number_of_products must be > 0")
    private int numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 1, message = "total_money must be > 0")
    private int totalMoney;

    private String color;
}
