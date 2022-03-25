package com.example.gwtapp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto implements Serializable {

    private String id;

    private String cityName;

    private String name;

    private String address;

    private String description;

}
