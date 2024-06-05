package dev.citralflo.animelist.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;


@Data
@Entity
public class VoiceActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;

    @OneToMany(mappedBy = "voiceActor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Character> characters = new HashSet<>();

    public VoiceActor(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public VoiceActor() {

    }

}
