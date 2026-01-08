package com.gilberto009199.stefanini.desafio.restaurant;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Entity
@Table(name = "tbl_restaurant")
@Data
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

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

    public RestaurantResponse toRequest() {
        return new RestaurantResponse(
            Id,
            name,
            description,
            cep,
            latitude,
            longitude
        );
    }


}
