package com.gilberto009199.stefanini.desafio.product;

import java.math.BigDecimal;

public record ProductResponse(
        Long Id,
        String name,
        String description,
        BigDecimal price
) {}

