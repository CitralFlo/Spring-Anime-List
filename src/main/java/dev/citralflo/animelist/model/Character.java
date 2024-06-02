package dev.citralflo.animelist.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.DETACH)
    private VoiceActor voiceActor;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Series series;

    public Character(String name, String imageUrl, VoiceActor voiceActor, Series series) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.voiceActor = voiceActor;
        this.series = series;
    }

    public Character(String name, String imageUrl, VoiceActor voiceActor) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.voiceActor = voiceActor;
    }

    public Character() {

    }

}
