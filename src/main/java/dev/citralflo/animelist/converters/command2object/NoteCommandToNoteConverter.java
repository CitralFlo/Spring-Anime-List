package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNoteConverter implements Converter<NoteCommand, Note> {

    @Synchronized
    @Override
    public Note convert(NoteCommand noteCommand) {
        final Note note = new Note();
        note.setId(noteCommand.getId());
        note.setNote(noteCommand.getNote());
        return note;
    }
}
