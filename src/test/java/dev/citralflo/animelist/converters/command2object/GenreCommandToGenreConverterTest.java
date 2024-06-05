package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenreCommandToGenreConverterTest {

    public static final Long ID = 1L;
    public static final String NAME = "name";

    GenreCommandToGenreConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new GenreCommandToGenreConverter();
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new GenreCommand()));
    }

    @Test
    void convert() {
        //given
        GenreCommand genreCommand = new GenreCommand();
        genreCommand.setId(ID);
        genreCommand.setName(NAME);

        //when
        Genre genre = converter.convert(genreCommand);

        //then
        assertNotNull(genre);
        assertEquals(ID, genre.getId());
        assertEquals(NAME, genre.getName());
    }

}