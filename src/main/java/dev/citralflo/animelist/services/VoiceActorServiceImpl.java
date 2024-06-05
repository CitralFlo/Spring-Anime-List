package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.converters.command2object.VoiceActorCommandToVoiceActorConverter;
import dev.citralflo.animelist.converters.object2command.VoiceActorToVoiceActorCommandConverter;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.repositories.VoiceActorRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VoiceActorServiceImpl implements VoiceActorService {

    private final VoiceActorRepository voiceActorRepository;
    private final VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter;
    private final VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter;

    public VoiceActorServiceImpl(VoiceActorRepository voiceActorRepository, VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter, VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter) {
        this.voiceActorRepository = voiceActorRepository;
        this.voiceActorToVoiceActorCommandConverter = voiceActorToVoiceActorCommandConverter;
        this.voiceActorCommandToVoiceActorConverter = voiceActorCommandToVoiceActorConverter;
    }

    @Override
    public Map<Long, VoiceActorCommand> listVoiceActors() {
        Map<Long, VoiceActorCommand> voiceActors = new HashMap<>();
        voiceActorRepository.findAll().forEach(voiceActor -> voiceActors.put(voiceActor.getId(), voiceActorToVoiceActorCommandConverter.convert(voiceActor)));

        return voiceActors;
    }

    @Override
    public VoiceActorCommand saveVoiceActorCommand(VoiceActorCommand command) {
        return voiceActorToVoiceActorCommandConverter.convert(voiceActorRepository.save(voiceActorCommandToVoiceActorConverter.convert(command)));
    }

    @Override
    public VoiceActorCommand getVoiceActorCommandById(Long id) {
        VoiceActor voiceActor = this.voiceActorRepository.findById(id).orElse(null);

        if (voiceActor == null) {
            return null;
        }

        return voiceActorToVoiceActorCommandConverter.convert(voiceActor);
    }

    @Override
    public VoiceActor getVoiceActorById(Long voiceActorId) {
        return voiceActorRepository.findById(voiceActorId).orElse(null);
    }
}
