package com.gilberto009199.stefanini.desafio.product;


import com.gilberto009199.stefanini.desafio.restaurant.RestaurantEntity;
import com.gilberto009199.stefanini.desafio.restaurant.RestaurantRequest;
import com.gilberto009199.stefanini.desafio.restaurant.RestaurantResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private RestaurantEntity restaurant;



    public static ProductEntity of(ProductRequest request) {
        return ProductEntity
                .builder()

                .name(request.name())
                .description(request.description())
                .price(request.price())

                .build();
    }

    public ProductResponse toResponse() {
        return new ProductResponse(
                id,
                name,
                description,
                price
        );
    }

}
