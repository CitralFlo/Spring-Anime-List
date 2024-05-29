package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Note;
import dev.citralflo.animelist.model.Series;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SeriesCommandToSeriesConverter implements Converter<SeriesCommand, Series> {

    private final CharacterCommandToCharacterConverter characterCommandToCharacterConverter;
    private final GenreCommandToGenreConverter genreCommandToGenreConverter;
    private final NoteCommandToNoteConverter noteCommandToNoteConverter;

    public SeriesCommandToSeriesConverter(CharacterCommandToCharacterConverter characterCommandToCharacterConverter, GenreCommandToGenreConverter genreCommandToGenreConverter, NoteCommandToNoteConverter noteCommandToNoteConverter) {
        this.characterCommandToCharacterConverter = characterCommandToCharacterConverter;
        this.genreCommandToGenreConverter = genreCommandToGenreConverter;
        this.noteCommandToNoteConverter = noteCommandToNoteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Series convert(SeriesCommand seriesCommand) {
        if (seriesCommand == null) {
            return null;
        }
        final Series series = new Series();
        series.setId(seriesCommand.getId());
        series.setTitle(seriesCommand.getTitle());
        series.setDescription(seriesCommand.getDescription());
        series.setUrl(seriesCommand.getUrl());
        series.setImageUrl(seriesCommand.getImageUrl());

        if (seriesCommand.getCharacters() != null && !seriesCommand.getCharacters().isEmpty()) {
            seriesCommand.getCharacters().forEach(
                characterCommand -> series.getCharacters().add(
                    this.characterCommandToCharacterConverter.convert(characterCommand)
                )
            );
        }

        series.setRating(seriesCommand.getRating());

        if (seriesCommand.getGenres() != null && !seriesCommand.getGenres().isEmpty()) {
            seriesCommand.getGenres().forEach(
                genreCommand -> series.getGenres().add(
                    this.genreCommandToGenreConverter.convert(genreCommand)
                )
            );
        }

        if (seriesCommand.getNote() != null) {
            Note note = noteCommandToNoteConverter.convert(seriesCommand.getNote());
            note.setSeries(series);
            series.setNote(note);
        }

        return series;
    }
}
