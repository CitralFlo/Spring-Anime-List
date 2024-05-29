package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNoteConverter implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {
        if (noteCommand == null) {
            return null;
        }
        final Note note = new Note();
        note.setId(noteCommand.getId());
        note.setNote(noteCommand.getNote());
        return note;
    }
}
