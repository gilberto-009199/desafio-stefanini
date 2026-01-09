package com.gilberto009199.stefanini.desafio.restaurant;

public record RestaurantResponse(
        Long id,
        String name,
        String description,
        String cep,
        Float latitude,
        Float longitude
) {



}
