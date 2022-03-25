package com.example.gwtapp.server.domain.mapper;

import java.util.List;

public interface CommonEntityMapper<E, D> {

    E toEntity(D dto);

    List<E> toEntityList(Iterable<D> dtoList);

    D fromEntity(E entity);

    List<D> fromEntityList(Iterable<E> entityList);
}
