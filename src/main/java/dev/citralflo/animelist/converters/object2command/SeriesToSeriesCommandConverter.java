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
    private final CharacterToCharacterCommandConverter characterToCharacterCommandConverter;
    private final GenreToGenreCommandConverter genreToGenreCommandConverter;

    public SeriesToSeriesCommandConverter(NoteToNoteCommandConverter noteToNoteCommandConverter, CharacterToCharacterCommandConverter characterToCharacterCommandConverter, GenreToGenreCommandConverter genreToGenreCommandConverter) {
        this.noteToNoteCommandConverter = noteToNoteCommandConverter;
        this.characterToCharacterCommandConverter = characterToCharacterCommandConverter;
        this.genreToGenreCommandConverter = genreToGenreCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public SeriesCommand convert(Series series) {
        if (series == null) {
            return null;
        }
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
                character -> seriesCommand.getCharacters().add(
                    this.characterToCharacterCommandConverter.convert(character)
                )
            );
        }

        if (series.getGenres() != null && !series.getGenres().isEmpty()) {
            series.getGenres().forEach(
                genre -> seriesCommand.getGenres().add(
                    this.genreToGenreCommandConverter.convert(genre)
                )
            );

        }

        return seriesCommand;
    }
}
