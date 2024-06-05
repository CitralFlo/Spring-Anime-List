package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoteCommandToNoteConverterTest {

    public static final Long ID = 1L;
    public static final String CONTENT = "content";

    NoteCommandToNoteConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new NoteCommandToNoteConverter();
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void convert() {
        //given
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(ID);
        noteCommand.setNote(CONTENT);

        //when
        Note note = converter.convert(noteCommand);

        //then
        assertNotNull(note);
        assertEquals(ID, note.getId());
        assertEquals(CONTENT, note.getNote());
    }
}