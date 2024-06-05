package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.model.Genre;
import java.util.Set;

public interface GenreService {

    Set<GenreCommand> getGenres();

    Genre getGenreById(Long id);

    GenreCommand saveGenreCommand(GenreCommand command);

    GenreCommand getGenreCommandById(Long id);

    void deleteGenreById(Long id);
}
