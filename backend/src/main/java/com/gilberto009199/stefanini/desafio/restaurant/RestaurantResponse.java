package com.gilberto009199.stefanini.desafio.restaurant;

public record RestaurantResponse(
        Long Id,
        String name,
        String description,
        String cep,
        Float latitude,
        Float longitude
) {}
