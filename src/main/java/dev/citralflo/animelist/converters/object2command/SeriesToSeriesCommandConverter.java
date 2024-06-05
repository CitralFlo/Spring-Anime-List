package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Series;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SeriesToSeriesCommandConverter implements Converter<Series, SeriesCommand> {

    private final NoteToNoteCommandConverter noteToNoteCommandConverter;

    public SeriesToSeriesCommandConverter(NoteToNoteCommandConverter noteToNoteCommandConverter, CharacterToCharacterCommandConverter characterToCharacterCommandConverter, GenreToGenreCommandConverter genreToGenreCommandConverter) {
        this.noteToNoteCommandConverter = noteToNoteCommandConverter;
    }

    @Synchronized
    @Override
    public SeriesCommand convert(Series series) {
        final SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(series.getId());
        seriesCommand.setTitle(series.getTitle());
        seriesCommand.setDescription(series.getDescription());
        seriesCommand.setRating(series.getRating());
        seriesCommand.setUrl(series.getUrl());
        seriesCommand.setImageUrl(series.getImageUrl());

        if (series.getNote() != null) {
            seriesCommand.setNote(this.noteToNoteCommandConverter.convert(series.getNote()));
        }

        if (series.getCharacters() != null && !series.getCharacters().isEmpty()) {
            series.getCharacters().forEach(
                character -> seriesCommand.getCharacters_id().add(
                    character.getId()
                )
            );
        }

        if (series.getGenres() != null && !series.getGenres().isEmpty()) {
            series.getGenres().forEach(
                genre -> seriesCommand.getGenres_id().add(genre.getId())
            );

        }

        return seriesCommand;
    }
}
