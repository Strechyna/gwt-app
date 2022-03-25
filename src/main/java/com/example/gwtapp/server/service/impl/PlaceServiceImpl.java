package com.example.gwtapp.server.service.impl;

import com.example.gwtapp.server.domain.mapper.PlaceMapper;
import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.domain.repository.PlaceRepository;
import com.example.gwtapp.server.dto.PlaceDto;
import com.example.gwtapp.server.service.api.PlaceService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository repository;

    @Override
    public List<PlaceDto> find() {
        return null;
    }

    @Override
    public PlaceDto find(String placeId) {
        return null;
    }

    @Override
    public boolean save(PlaceDto place) {
        return false;
    }

    @Override
    public boolean saveAll(Iterable<PlaceDto> places) {
        List<Place> savedPlaces = repository.saveAll(PlaceMapper.INSTANCE.toEntityList(places));
        return CollectionUtils.isNotEmpty(savedPlaces);
    }

    @Override
    public boolean edit(PlaceDto place) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
