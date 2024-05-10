package dev.citralflo.animelist.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
    private Set<Character> characters = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    @ManyToMany
    @JoinTable(
            name = "series_genre",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
        note.setSeries(this);
    }

    public Series addCharacter(Character character) {
        characters.add(character);
        character.setSeries(this);
        return this;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
