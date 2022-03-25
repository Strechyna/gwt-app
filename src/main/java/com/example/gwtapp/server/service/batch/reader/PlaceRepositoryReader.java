package com.example.gwtapp.server.service.batch.reader;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.domain.repository.PlaceRepository;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.concurrent.CopyOnWriteArrayList;

public class PlaceRepositoryReader extends AbstractPagingItemReader<Place> {

    @Autowired
    private PlaceRepository repository;

    @Override
    protected void doReadPage() {
        Pageable pageable = PageRequest.of(getPage(), getPageSize(), Sort.by("id"));
        Page<Place> pageResults = repository.findAll(pageable);

        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }

        if (!pageResults.getContent().isEmpty()) {
            results.addAll(pageResults.getContent());
        }
    }

    @Override
    protected void doJumpToPage(int itemIndex) {
    }
}
