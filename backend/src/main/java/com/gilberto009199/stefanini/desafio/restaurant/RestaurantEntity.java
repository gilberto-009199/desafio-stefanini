package com.gilberto009199.stefanini.desafio.restaurant;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantEntity {

    private Long Id;
    private String name;
    private String description;
    private String cep;
    private Float latitude;
    private Float longitude;



}
