package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.GenreCommand;
import dev.citralflo.animelist.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenreToGenreCommandConverterTest {

    public static final Long ID = 1L;
    public static final String NAME = "name";

    GenreToGenreCommandConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new GenreToGenreCommandConverter();
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Genre()));
    }

    @Test
    void convert() throws Exception{
        //given
        Genre genre = new Genre();
        genre.setId(ID);
        genre.setName(NAME);

        //when
        GenreCommand genreCommand = converter.convert(genre);

        //then
        assertNotNull(genreCommand);
        assertEquals(ID, genreCommand.getId());
        assertEquals(NAME, genreCommand.getName());
    }

}