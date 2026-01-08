package com.gilberto009199.stefanini.desafio.restaurant;

public record RestaurantRequest(
        String name,
        String description,
        String cep,
        Float latitude,
        Float longitude
) {
}
