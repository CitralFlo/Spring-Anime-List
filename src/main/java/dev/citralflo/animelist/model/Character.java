package dev.citralflo.animelist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;

    @ManyToOne
    private VoiceActor voiceActor;

    @ManyToOne
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

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
