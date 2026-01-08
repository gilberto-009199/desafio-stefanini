package com.gilberto009199.stefanini.desafio.restaurant;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantRepository repository;

    public RestaurantController(
            RestaurantRepository repository
    ) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantEntity>> get() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return repository.findById(id)
                .map(restaurant ->
                        ResponseEntity.ok(restaurant.toRequest())
                )
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody RestaurantRequest request,
            UriComponentsBuilder uriBuilder
    ) {

        var entity = RestaurantEntity.of(request);

        entity = repository.save(entity);


        URI uri = uriBuilder.path("/restaurant/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(entity.toRequest());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody RestaurantRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        return repository.findById(id)
        .map(restaurant ->{

            restaurant.setName(request.name());

            restaurant.setDescription(request.description());

            restaurant.setCep(request.cep());

            restaurant.setLatitude(request.latitude());

            restaurant.setLongitude(request.longitude());

            repository.save(restaurant);

            return ResponseEntity.ok(restaurant.toRequest());

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
