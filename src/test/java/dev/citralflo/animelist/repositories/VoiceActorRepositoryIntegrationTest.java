package dev.citralflo.animelist.repositories;

import dev.citralflo.animelist.model.VoiceActor;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VoiceActorRepositoryIntegrationTest {

    @Autowired
    VoiceActorRepository voiceActorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByName() {
        Optional<VoiceActor> voiceActorOptional = voiceActorRepository.findByName("Miyamura, Yuko");

        assertEquals("Miyamura, Yuko", voiceActorOptional.get().getName());
    }

    @Test
    void findByNameKENN() {
        Optional<VoiceActor> voiceActorOptional = voiceActorRepository.findByName("KENN");

        assertEquals("KENN", voiceActorOptional.get().getName());
    }
}