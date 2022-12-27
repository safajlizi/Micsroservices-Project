package com.Makeupsale.productservice.data_transfer_objects;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ProductRequest {
    private String name;

    private String description;
    private BigDecimal price;
}
