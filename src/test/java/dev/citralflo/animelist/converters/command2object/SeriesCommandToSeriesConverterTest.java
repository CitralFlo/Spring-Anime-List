package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Rating;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.GenreService;
import dev.citralflo.animelist.services.VoiceActorService;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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

    @Mock
    VoiceActorService VoiceActorService;

    @Mock
    CharacterService characterService;

    @Mock
    GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.converter = new SeriesCommandToSeriesConverter(
            new CharacterCommandToCharacterConverter(new VoiceActorCommandToVoiceActorConverter(), VoiceActorService),
            new NoteCommandToNoteConverter(),
            characterService,
            genreService
        );
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
}