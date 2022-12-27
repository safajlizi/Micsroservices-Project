package com.Makeupsale.productservice.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.*;


import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductId")
    private String id;
    private String name;
    private String description;
    private BigDecimal price;


}
