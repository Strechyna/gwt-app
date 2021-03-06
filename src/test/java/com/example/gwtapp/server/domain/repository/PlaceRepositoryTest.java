package com.example.gwtapp.server.domain.repository;

import com.example.gwtapp.server.domain.model.Place;
import com.example.gwtapp.server.domain.model.PlaceTestBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlaceRepository repository;

    @Test
    public void findAllWithEmptyResultTest() {
        List<Place> tutorials = repository.findAll();

        assertThat(tutorials).isEmpty();
    }

    @Test
    public void findAllTest() {
        Place place = PlaceTestBuilder.build("1");
        entityManager.persist(place);

        List<Place> tutorials = repository.findAll();

        assertThat(tutorials).hasSize(1).contains(place);
    }

}
