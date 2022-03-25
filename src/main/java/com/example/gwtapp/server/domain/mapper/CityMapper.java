package com.example.gwtapp.server.domain.mapper;

import com.example.gwtapp.server.domain.model.City;
import com.example.gwtapp.server.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends CommonEntityMapper<City, CityDto> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
