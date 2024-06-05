package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.VoiceActor;
import java.util.Map;
import java.util.Set;

public interface VoiceActorService {

    Map<Long, VoiceActorCommand> listVoiceActors();

    VoiceActorCommand saveVoiceActorCommand(VoiceActorCommand command);

    VoiceActorCommand getVoiceActorCommandById(Long id);

    VoiceActor getVoiceActorById(Long voiceActorId);
}
