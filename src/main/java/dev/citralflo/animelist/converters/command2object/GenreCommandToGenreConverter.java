package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreCommandToGenreConverter implements Converter<GenreCommand, Genre> {

    @Synchronized
    @Nullable
    @Override
    public Genre convert(GenreCommand genreCommand) {
        if (genreCommand == null) {
            return null;
        }
        final Genre genre = new Genre();
        genre.setId(genreCommand.getId());
        genre.setName(genreCommand.getName());
        return genre;
    }
}
