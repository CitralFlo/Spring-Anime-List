package dev.citralflo.animelist.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    private String url;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
    private Set<Character> characters = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "series_genre",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();


    public void setNote(Note note) {
        this.note = note;
        note.setSeries(this);
    }

    public Series addCharacter(Character character) {
        characters.add(character);
        character.setSeries(this);
        return this;
    }}
