package com.example.gwtapp.server.service.batch.writer;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.domain.repository.PlaceRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaceRepositoryWriter implements ItemWriter<Place> {

    @Autowired
    private PlaceRepository repository;

    @Override
    public void write(List<? extends Place> items) throws Exception {
        repository.saveAll(items);
    }
}
