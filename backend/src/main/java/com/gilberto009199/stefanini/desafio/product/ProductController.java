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


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable Long id

    ) {
        return repository.findById(id)
            .map(product -> ResponseEntity.ok(product.toResponse()))
                .orElseGet(() -> ResponseEntity.notFound().build());
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


    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody ProductRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        return repository.findById(id)
                .map(product ->{

                    product.setName(request.name());

                    product.setDescription(request.description());

                    product.setPrice(request.price());

                    repository.save(product);

                    return ResponseEntity.ok(product.toResponse());

                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        return repository.findById(id)
                .map(entity -> {

                    repository.delete(entity);

                    return ResponseEntity.noContent().build();

                })
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }
}