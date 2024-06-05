package dev.citralflo.animelist.repositories;

import dev.citralflo.animelist.model.Genre;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
