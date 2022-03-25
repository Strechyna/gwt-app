package com.example.gwtapp.server.domain.mapper;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.dto.PlaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceMapper extends CommonEntityMapper<Place, PlaceDto> {
    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);
}
