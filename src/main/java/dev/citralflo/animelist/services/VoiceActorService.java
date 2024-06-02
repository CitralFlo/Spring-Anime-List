package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import java.util.Set;

public interface VoiceActorService {

    Set<VoiceActorCommand> listVoiceActors();
}
