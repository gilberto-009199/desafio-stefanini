package com.gilberto009199.stefanini.desafio.product;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price
) {}

