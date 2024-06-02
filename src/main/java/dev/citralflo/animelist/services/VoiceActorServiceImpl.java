package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.converters.object2command.VoiceActorToVoiceActorCommandConverter;
import dev.citralflo.animelist.repositories.VoiceActorRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VoiceActorServiceImpl implements VoiceActorService {

    private final VoiceActorRepository voiceActorRepository;
    private final VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter;

    public VoiceActorServiceImpl(VoiceActorRepository voiceActorRepository, VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter) {
        this.voiceActorRepository = voiceActorRepository;
        this.voiceActorToVoiceActorCommandConverter = voiceActorToVoiceActorCommandConverter;
    }

    @Override
    public Set<VoiceActorCommand> listVoiceActors() {

        Set<VoiceActorCommand> voiceActors = new HashSet<>();

        voiceActorRepository.findAll().forEach(voiceActor -> voiceActors.add(voiceActorToVoiceActorCommandConverter.convert(voiceActor)));

        return voiceActors;

    }
}
