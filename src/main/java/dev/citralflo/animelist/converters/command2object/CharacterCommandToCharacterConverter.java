package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CharacterCommandToCharacterConverter implements Converter<CharacterCommand, Character> {

    private final VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter;

    public CharacterCommandToCharacterConverter( VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter) {
        this.voiceActorCommandToVoiceActorConverter = voiceActorCommandToVoiceActorConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Character convert(CharacterCommand characterCommand) {
        if (characterCommand == null) {
            return null;
        }
        final Character character = new Character();
        if (characterCommand.getSeriesId() != null) {
            Series series = new Series();
            series.setId(characterCommand.getSeriesId());
            character.setSeries(series);
            series.addCharacter(character);
        }

        character.setId(characterCommand.getId());
        character.setName(characterCommand.getName());
        character.setImageUrl(characterCommand.getImageUrl());

        if (characterCommand.getVoiceActor() != null) {
            character.setVoiceActor(this.voiceActorCommandToVoiceActorConverter.convert(characterCommand.getVoiceActor()));
        }

        return character;
    }

}

