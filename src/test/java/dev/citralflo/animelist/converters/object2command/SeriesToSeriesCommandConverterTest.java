package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Note;
import dev.citralflo.animelist.model.Rating;
import dev.citralflo.animelist.model.Series;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeriesToSeriesCommandConverterTest {

    public static final Long ID = 1L;
    public static final String TITLE = "title";
    public static final String IMAGE_URL = "imageUrl";
    public static final String DESCRIPTION = "description";
    public static final Rating RATING = Rating.OKAY;
    public static final String SERIES_URL = "seriesUrl";

    public static final Long NOTE_ID = 2L;
    public static final String NOTE_CONTENT = "noteContent";

    public static final Long CHARACTER_ID = 3L;
    public static final String CHARACTER_NAME = "characterName";

    public static final Long VA_ID = 4L;
    public static final String VA_NAME = "vaName";

    SeriesToSeriesCommandConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new SeriesToSeriesCommandConverter(
            new NoteToNoteCommandConverter(),
            new CharacterToCharacterCommandConverter(new VoiceActorToVoiceActorCommandConverter()),
            new GenreToGenreCommandConverter()
        );
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Series()));
    }

    @Test
    void convert() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertEquals(ID, seriesCommand.getId());
        assertEquals(TITLE, seriesCommand.getTitle());
        assertEquals(IMAGE_URL, seriesCommand.getImageUrl());
        assertEquals(DESCRIPTION, seriesCommand.getDescription());
        assertEquals(RATING, seriesCommand.getRating());
        assertEquals(SERIES_URL, seriesCommand.getUrl());
    }

    @Test
    void convertWithEmptyNote() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);
        series.setNote(
            new Note()
        );

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getNote());
        assertNull(seriesCommand.getNote().getId());
    }

    @Test
    void convertWithNote() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);
        Note note = new Note();
        note.setId(NOTE_ID);
        note.setNote(NOTE_CONTENT);
        series.setNote(note);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getNote());
        assertEquals(NOTE_ID, seriesCommand.getNote().getId());
        assertEquals(NOTE_CONTENT, seriesCommand.getNote().getNote());
    }

    @Test
    void convertWithEmptyCharacter() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getCharacters());
        assertEquals(0, seriesCommand.getCharacters().size());
    }

    @Test
    void convertWithCharacter() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);
        dev.citralflo.animelist.model.Character character = new dev.citralflo.animelist.model.Character();
        character.setId(CHARACTER_ID);
        character.setName(CHARACTER_NAME);
        series.getCharacters().add(character);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getCharacters());
        assertEquals(1, seriesCommand.getCharacters().size());

        List<CharacterCommand> characters = seriesCommand.getCharacters();
        assertNotNull(characters);
        assertEquals(1, characters.size());
        characters.forEach(
            characterCommand -> {
                assertNotNull(characterCommand);
                assertEquals(CHARACTER_ID, characterCommand.getId());
                assertEquals(CHARACTER_NAME, characterCommand.getName());
            }
        );
    }

    @Test
    void convertWithEmptyCharacterAndVA() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getCharacters());
        assertEquals(0, seriesCommand.getCharacters().size());
    }

    @Test
    void convertWithCharacterAndVA() {
        //given
        Series series = new Series();
        series.setId(ID);
        series.setTitle(TITLE);
        series.setImageUrl(IMAGE_URL);
        series.setDescription(DESCRIPTION);
        series.setRating(RATING);
        series.setUrl(SERIES_URL);
        dev.citralflo.animelist.model.Character character = new dev.citralflo.animelist.model.Character();
        character.setId(CHARACTER_ID);
        character.setName(CHARACTER_NAME);
        dev.citralflo.animelist.model.VoiceActor voiceActor = new dev.citralflo.animelist.model.VoiceActor();
        voiceActor.setId(VA_ID);
        voiceActor.setName(VA_NAME);
        character.setVoiceActor(voiceActor);
        series.getCharacters().add(character);

        //when
        SeriesCommand seriesCommand = converter.convert(series);

        //then
        assertNotNull(seriesCommand);
        assertNotNull(seriesCommand.getCharacters());
        assertEquals(1, seriesCommand.getCharacters().size());

        List<CharacterCommand> characters = seriesCommand.getCharacters();
        assertNotNull(characters);
        assertEquals(1, characters.size());
        characters.forEach(
            characterCommand -> {
                assertNotNull(characterCommand);
                assertEquals(CHARACTER_ID, characterCommand.getId());
                assertEquals(CHARACTER_NAME, characterCommand.getName());
                assertNotNull(characterCommand.getVoiceActor());
                assertEquals(VA_ID, characterCommand.getVoiceActor().getId());
                assertEquals(VA_NAME, characterCommand.getVoiceActor().getName());
            }
        );
    }

}