package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.NoteCommand;
import dev.citralflo.animelist.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteCommandConverter implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note note) {
        if (note == null) {
            return null;
        }
        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setNote(note.getNote());
        return noteCommand;
    }
}
