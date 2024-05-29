package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.VoiceActor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class VoiceActorCommandToVoiceActorConverter implements Converter<VoiceActorCommand, VoiceActor> {

    @Synchronized
    @Nullable
    @Override
    public VoiceActor convert(VoiceActorCommand voiceActorCommand) {
        if (voiceActorCommand == null) {
            return null;
        }
        final VoiceActor voiceActor = new VoiceActor();
        voiceActor.setId(voiceActorCommand.getId());
        voiceActor.setName(voiceActorCommand.getName());
        voiceActor.setImageUrl(voiceActorCommand.getImageUrl());


        return voiceActor;
    }
}
