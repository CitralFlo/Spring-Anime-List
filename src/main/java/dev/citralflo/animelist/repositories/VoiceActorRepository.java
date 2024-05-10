package dev.citralflo.animelist.repositories;

import dev.citralflo.animelist.model.VoiceActor;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface VoiceActorRepository extends CrudRepository<VoiceActor, Long> {

    Optional<VoiceActor> findByName(String name);
}
