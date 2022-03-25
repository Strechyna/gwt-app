package com.example.gwtapp.server.service.api;

import com.example.gwtapp.server.dto.PlaceDto;

import java.util.List;

public interface GooglePlacesApiService {

    List<PlaceDto> nearbySearch(String location);

    PlaceDto get(String placeId);

}
