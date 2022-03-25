package com.example.gwtapp.server.service.batch.extractor;

import com.example.gwtapp.server.domain.model.Place;
import org.springframework.batch.item.file.transform.FieldExtractor;

import java.util.ArrayList;
import java.util.List;

public class PlaceCsvFieldExtractor implements FieldExtractor<Place> {

    @Override
    public Object[] extract(Place item) {
        List<Object> values = new ArrayList<>(5);
        values.add(item.getId());
        values.add(item.getCityName());
        values.add(item.getName());
        values.add(item.getAddress());
        values.add(item.getDescription());
        return values.toArray();
    }
}
