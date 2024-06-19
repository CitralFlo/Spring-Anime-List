package dev.citralflo.animelist.model;

import jakarta.persistence.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Lob
    private Byte[] image;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "series", orphanRemoval = true)
    @ToString.Exclude
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

    public void addCharacter(Character character) {
        characters.add(character);
        character.setSeries(this);
    }

    public void setImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = url.getFile();
        Path tempFile = Files.createTempFile("temp", fileName.substring(fileName.lastIndexOf(".")));
        try (InputStream in = url.openStream()) {
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        File file = tempFile.toFile();
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Byte[] byteObjects = new Byte[fileContent.length];
        int i=0;
        for(byte b: fileContent)
            byteObjects[i++] = b;  // Autoboxing.
        this.setImage(byteObjects);
        Files.delete(tempFile);  // Delete the temporary file

    }

}
