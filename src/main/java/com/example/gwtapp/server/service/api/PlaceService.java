package com.example.gwtapp.server.service.api;

import com.example.gwtapp.server.dto.PlaceDto;

import java.util.List;

public interface PlaceService {

    List<PlaceDto> find();

    PlaceDto find(String placeId);

    boolean save(PlaceDto place);

    boolean saveAll(Iterable<PlaceDto> places);

    boolean edit(PlaceDto place);

    boolean delete(String id);

}
