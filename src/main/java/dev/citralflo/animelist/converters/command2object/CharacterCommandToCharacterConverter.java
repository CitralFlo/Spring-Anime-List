package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.services.VoiceActorService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CharacterCommandToCharacterConverter implements Converter<CharacterCommand, Character> {

    private final VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter;
    private final VoiceActorService voiceActorService;

    public CharacterCommandToCharacterConverter(VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter,
                                                VoiceActorService voiceActorService) {
        this.voiceActorCommandToVoiceActorConverter = voiceActorCommandToVoiceActorConverter;
        this.voiceActorService = voiceActorService;
    }

    @Synchronized
    @Override
    public Character convert(CharacterCommand characterCommand) {

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

        if (characterCommand.getVoiceActorId() != null) {
            character.setVoiceActor(voiceActorService.getVoiceActorById(characterCommand.getVoiceActorId()));
        }

        return character;
    }

}

