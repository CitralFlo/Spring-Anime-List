package dev.citralflo.animelist.commands;

import dev.citralflo.animelist.model.Rating;
import java.util.ArrayList;
import java.util.List;
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
    private Byte[] image;
    private String url;
    private NoteCommand note;
    private List<Long> characters_id = new ArrayList<>();
    private Rating rating;
    private List<Long> genres_id = new ArrayList<>();

}
