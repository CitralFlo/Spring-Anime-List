package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.converters.command2object.GenreCommandToGenreConverter;
import dev.citralflo.animelist.converters.object2command.GenreToGenreCommandConverter;
import dev.citralflo.animelist.model.Genre;
import dev.citralflo.animelist.repositories.GenreRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreToGenreCommandConverter genreToGenreCommandConverter;
    private final GenreCommandToGenreConverter genreCommandToGenreConverter;

    public GenreServiceImpl(
        GenreRepository genreRepository,
        GenreToGenreCommandConverter genreToGenreCommandConverter,
        GenreCommandToGenreConverter genreCommandToGenreConverter
    ) {
        this.genreRepository = genreRepository;
        this.genreToGenreCommandConverter = genreToGenreCommandConverter;
        this.genreCommandToGenreConverter = genreCommandToGenreConverter;
    }

    @Override
    public Set<GenreCommand> getGenres() {
        Set<GenreCommand> genres = new HashSet<>();
        this.genreRepository.findAll().forEach(
            genre -> genres.add(this.genreToGenreCommandConverter.convert(genre))
        );

        return genres;
    }

    @Override
    public Genre getGenreById(Long id) {
        return this.genreRepository.findById(id).orElse(null);
    }

    @Override
    public GenreCommand saveGenreCommand(GenreCommand command) {
        Genre converted = this.genreCommandToGenreConverter.convert(command);

        Genre saved = this.genreRepository.save(converted);

        return this.genreToGenreCommandConverter.convert(saved);

    }

    @Override
    public GenreCommand getGenreCommandById(Long id) {
        return this.genreToGenreCommandConverter.convert(this.genreRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteGenreById(Long id) {
        this.genreRepository.deleteById(id);
    }
}
