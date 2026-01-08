package com.gilberto009199.stefanini.desafio.restaurant;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tbl_restaurant")
@Data
@Builder
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String cep;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

}
