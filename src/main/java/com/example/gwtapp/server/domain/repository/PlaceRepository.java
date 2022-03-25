package com.example.gwtapp.server.domain.repository;

import com.example.gwtapp.server.domain.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
}
