package com.example.gwtapp.server.service.batch.mapper;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.service.helper.PlaceCsvHeader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

public class PlaceCsvFieldSetMapper implements FieldSetMapper<Place> {

    @Override
    public Place mapFieldSet(FieldSet fieldSet) {
        return Place.builder()
                .id(fieldSet.readString(PlaceCsvHeader.ID))
                .cityName(PlaceCsvHeader.CITY_NAME)
                .name(PlaceCsvHeader.NAME)
                .address(PlaceCsvHeader.ADDRESS)
                .description(PlaceCsvHeader.DESCRIPTION)
                .build();
    }
}
