package com.example.gwtapp.server.domain.model;

public class PlaceTestBuilder {

    public static Place build(String id) {
        return Place.builder()
                .id(id)
                .cityName("Minsk")
                .name("name" + id)
                .address("address" + id)
                .description("description" + id)
                .build();
    }
}
