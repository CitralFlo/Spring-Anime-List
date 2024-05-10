package dev.citralflo.animelist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class VoiceActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;

    @OneToMany
    private Set<Character> characters = new HashSet<>();

    public VoiceActor(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public VoiceActor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}
