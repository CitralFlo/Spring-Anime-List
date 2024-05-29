package dev.citralflo.animelist.commands;

import dev.citralflo.animelist.model.Rating;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SeriesCommand {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String url;
    private NoteCommand note;
    private Set<CharacterCommand> characters = new HashSet<>();;
    private Rating rating;
    private Set<GenreCommand> genres = new HashSet<>();

}
