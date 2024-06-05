package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Genre;
import dev.citralflo.animelist.model.Note;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.GenreService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeriesCommandToSeriesConverter implements Converter<SeriesCommand, Series> {

    private final CharacterCommandToCharacterConverter characterCommandToCharacterConverter;
    private final NoteCommandToNoteConverter noteCommandToNoteConverter;
    private final CharacterService characterService;
    private final GenreService genreService;

    @Autowired
    public SeriesCommandToSeriesConverter(CharacterCommandToCharacterConverter characterCommandToCharacterConverter,
                                          NoteCommandToNoteConverter noteCommandToNoteConverter,
                                          CharacterService characterService,
                                          GenreService genreService
    ) {
        this.characterCommandToCharacterConverter = characterCommandToCharacterConverter;
        this.noteCommandToNoteConverter = noteCommandToNoteConverter;
        this.characterService = characterService;
        this.genreService = genreService;
    }

    @Synchronized
    @Override
    public Series convert(SeriesCommand seriesCommand) {
        final Series series = new Series();
        series.setId(seriesCommand.getId());
        series.setTitle(seriesCommand.getTitle());
        series.setDescription(seriesCommand.getDescription());
        series.setUrl(seriesCommand.getUrl());
        series.setImageUrl(seriesCommand.getImageUrl());

        if (seriesCommand.getCharacters_id() != null && !seriesCommand.getCharacters_id().isEmpty()) {
            seriesCommand.getCharacters_id().forEach(
                characterId -> {
                    CharacterCommand characterCommand = this.characterService.findCharacterBySeriesIdAndCharacterId(seriesCommand.getId(), characterId);
                    Character character = characterCommandToCharacterConverter.convert(characterCommand);
                    series.addCharacter(character);
                }
            );
        }

        series.setRating(seriesCommand.getRating());

        if (seriesCommand.getGenres_id() != null && !seriesCommand.getGenres_id().isEmpty()) {
            seriesCommand.getGenres_id().forEach(genreCommand -> {
                Genre genreById = genreService.getGenreById(genreCommand);
                series.getGenres().add(genreById);
            });
        }

        if (seriesCommand.getNote() != null) {
            Note note = noteCommandToNoteConverter.convert(seriesCommand.getNote());
            series.setNote(note);
        }

        return series;
    }
}
