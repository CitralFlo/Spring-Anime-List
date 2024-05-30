package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.model.Genre;
import dev.citralflo.animelist.repositories.GenreRepository;
import java.util.Set;

public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Set<Genre> findAll() {
        return genreRepository.findAll();
    }
}
