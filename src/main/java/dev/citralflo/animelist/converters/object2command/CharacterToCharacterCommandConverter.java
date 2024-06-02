package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.model.Character;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterCommandConverter implements Converter<Character, CharacterCommand> {

    private final VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter;

    public CharacterToCharacterCommandConverter(VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter) {
        this.voiceActorToVoiceActorCommandConverter = voiceActorToVoiceActorCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CharacterCommand convert(Character character) {
        if (character == null) {
            return null;
        }
        final CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setId(character.getId());
        characterCommand.setName(character.getName());
        characterCommand.setImageUrl(character.getImageUrl());

        if (character.getSeries() != null) {
            characterCommand.setSeriesId(character.getSeries().getId());
        }

        if (character.getVoiceActor() != null) {
            characterCommand.setVoiceActor(this.voiceActorToVoiceActorCommandConverter.convert(character.getVoiceActor()));
        }

        return characterCommand;
    }
}
