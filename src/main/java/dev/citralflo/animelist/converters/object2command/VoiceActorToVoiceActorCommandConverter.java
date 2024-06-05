package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.VoiceActor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class VoiceActorToVoiceActorCommandConverter implements Converter<VoiceActor, VoiceActorCommand> {

    @Synchronized
    @Override
    public VoiceActorCommand convert(VoiceActor voiceActor) {
        final VoiceActorCommand voiceActorCommand = new VoiceActorCommand();
        voiceActorCommand.setId(voiceActor.getId());
        voiceActorCommand.setName(voiceActor.getName());
        voiceActorCommand.setImageUrl(voiceActor.getImageUrl());
        return voiceActorCommand;
    }
}
