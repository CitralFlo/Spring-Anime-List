package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreToGenreCommandConverter implements Converter<Genre, GenreCommand> {

    @Synchronized
    @Override
    public GenreCommand convert(Genre genre) {
        final GenreCommand genreCommand = new GenreCommand();
        genreCommand.setId(genre.getId());
        genreCommand.setName(genre.getName());
        return genreCommand;
    }
}
