package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoteToNoteCommandConverterTest {

    public static final Long ID = 1L;
    public static final String CONTENT = "content";

    NoteToNoteCommandConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new NoteToNoteCommandConverter();
    }

    @Test
    void convert() {
        //given
        Note note = new Note();
        note.setId(ID);
        note.setNote(CONTENT);

        //when
        NoteCommand noteCommand = converter.convert(note);

        //then
        assertNotNull(noteCommand);
        assertEquals(ID, noteCommand.getId());
        assertEquals(CONTENT, noteCommand.getNote());
    }
}