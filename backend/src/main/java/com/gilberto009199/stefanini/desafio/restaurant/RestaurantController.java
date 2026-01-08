package com.gilberto009199.stefanini.desafio.restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private RestaurantRepository repository;

    public RestaurantController(
            RestaurantRepository repository
    ){
        this.repository = repository;
    }


    @GetMapping
    public ResponseEntity<?> get(){
        return  ResponseEntity.ok(repository.findAll());
    }

}
