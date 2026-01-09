package com.gilberto009199.stefanini.desafio.restaurant;


import com.gilberto009199.stefanini.desafio.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String cep;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductEntity> listProductsEntity;

    public static RestaurantEntity of(RestaurantRequest request) {
        return RestaurantEntity
                .builder()

                .name(request.name())
                .description(request.description())
                .cep(request.cep())
                .latitude(request.latitude())
                .longitude(request.longitude())

                .build();

    }

    public RestaurantResponse toResponse() {
        return new RestaurantResponse(
            id,
            name,
            description,
            cep,
            latitude,
            longitude
        );
    }


}
