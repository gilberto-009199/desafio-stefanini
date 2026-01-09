package com.gilberto009199.stefanini.desafio.product;


import com.gilberto009199.stefanini.desafio.restaurant.RestaurantEntity;
import com.gilberto009199.stefanini.desafio.restaurant.RestaurantRepository;
import com.gilberto009199.stefanini.desafio.restaurant.RestaurantRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("restaurant/{idRestaurant}/product")
public class ProductController {

    private final ProductRepository repository;
    private final RestaurantRepository restaurantRepository;

    public ProductController(
            ProductRepository repository,
            RestaurantRepository restaurantRepository
    ) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> get(
            @PathVariable Long idRestaurant
    ) {
        return ResponseEntity.ok(repository.findAllByRestaurantId(idRestaurant));
    }

    @PostMapping
    public ResponseEntity<?> save(
            @PathVariable Long idRestaurant,
            @RequestBody ProductRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var entity = ProductEntity.of(request);

        var restaurantEntity = restaurantRepository.findById(idRestaurant).get();

        entity.setRestaurant(restaurantEntity);

        entity = repository.save(entity);

        URI uri = uriBuilder.path("/restaurant/{idRestaurant}/product/{id}")
                .buildAndExpand(idRestaurant, entity.getId())
                .toUri();

        return ResponseEntity.created(uri).body(entity.toResponse());
    }

}