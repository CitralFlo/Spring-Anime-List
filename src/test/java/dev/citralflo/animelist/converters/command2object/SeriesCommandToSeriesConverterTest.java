package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Rating;
import dev.citralflo.animelist.model.Series;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeriesCommandToSeriesConverterTest {

    public static final Long ID = 1L;
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final Rating RATING = Rating.THRILLING;
    public static final String IMAGE_URL = "image url";
    public static final String SERIES_URL = "series url";

    public static final Long NOTE_ID = 2L;
    public static final String NOTE_CONTENT = "note content";

    public static final Long CHARACTER_ID = 3L;
    public static final String CHARACTER_NAME = "character name";

    public static final Long VA_ID = 4L;
    public static final String VA_NAME = "va name";

    public static final Long GENRE_ID = 5L;
    public static final String GENRE_NAME = "genre name";

    SeriesCommandToSeriesConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new SeriesCommandToSeriesConverter(
            new CharacterCommandToCharacterConverter(new VoiceActorCommandToVoiceActorConverter()),
            new GenreCommandToGenreConverter(),
            new NoteCommandToNoteConverter()
        );
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new SeriesCommand()));
    }

    @Test
    void convert() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());
    }

    @Test
    void convertWithEmptyNote() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertNull(series.getNote());
    }

    @Test
    void convertWithNote() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(NOTE_ID);
        noteCommand.setNote(NOTE_CONTENT);

        seriesCommand.setNote(noteCommand);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertNotNull(series.getNote());
        assertEquals(NOTE_ID, series.getNote().getId());
        assertEquals(NOTE_CONTENT, series.getNote().getNote());
    }

    @Test
    void convertWithEmptyCharacters() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertTrue(series.getCharacters().isEmpty());
    }

    @Test
    void convertWithCharacters() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setId(CHARACTER_ID);
        characterCommand.setName(CHARACTER_NAME);

        VoiceActorCommand voiceActorCommand = new VoiceActorCommand();
        voiceActorCommand.setId(VA_ID);
        voiceActorCommand.setName(VA_NAME);

        characterCommand.setVoiceActor(voiceActorCommand);

        seriesCommand.getCharacters().add(characterCommand);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertFalse(series.getCharacters().isEmpty());
        assertEquals(1, series.getCharacters().size());

        Set<Character> characters = series.getCharacters();

        characters.forEach(
            character -> {
                assertNotNull(character);
                assertEquals(CHARACTER_ID, character.getId());
                assertEquals(CHARACTER_NAME, character.getName());

                assertNotNull(character.getVoiceActor());
                assertEquals(VA_ID, character.getVoiceActor().getId());
                assertEquals(VA_NAME, character.getVoiceActor().getName());
            }
        );
    }

    @Test
    void convertWithEmptyGenres() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertTrue(series.getGenres().isEmpty());
    }

    @Test
    void convertWithGenres() {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(ID);
        seriesCommand.setTitle(TITLE);
        seriesCommand.setDescription(DESCRIPTION);
        seriesCommand.setRating(RATING);
        seriesCommand.setImageUrl(IMAGE_URL);
        seriesCommand.setUrl(SERIES_URL);

        GenreCommand genreCommand = new GenreCommand();
        genreCommand.setId(GENRE_ID);
        genreCommand.setName(GENRE_NAME);

        seriesCommand.getGenres().add(genreCommand);

        //when
        Series series = converter.convert(seriesCommand);

        //then
        assertNotNull(series);
        assertEquals(ID, series.getId());
        assertEquals(TITLE, series.getTitle());
        assertEquals(DESCRIPTION, series.getDescription());
        assertEquals(RATING, series.getRating());
        assertEquals(IMAGE_URL, series.getImageUrl());
        assertEquals(SERIES_URL, series.getUrl());

        assertFalse(series.getGenres().isEmpty());
        assertEquals(1, series.getGenres().size());

        assertEquals(GENRE_ID, series.getGenres().iterator().next().getId());
        assertEquals(GENRE_NAME, series.getGenres().iterator().next().getName());
    }

}