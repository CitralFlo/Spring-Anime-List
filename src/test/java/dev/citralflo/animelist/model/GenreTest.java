package dev.citralflo.animelist.model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenreTest {
    Genre genre;

    @BeforeEach
    public void setUp() {
         genre = new Genre();
    }

    @Test
    void getId() {
        Long idValue = 1L;

        genre.setId(idValue);

        assertEquals(idValue, genre.getId());
    }

    @Test
    void getName() {
    }

    @Test
    void getSeries() {
    }
}