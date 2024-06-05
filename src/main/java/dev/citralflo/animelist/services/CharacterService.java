package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.CharacterCommand;
import java.util.List;

public interface CharacterService {

    CharacterCommand findCharacterBySeriesIdAndCharacterId(Long seriesId, Long characterId);

    CharacterCommand saveCharacterCommand(CharacterCommand characterCommand);

    void deleteCharacterBySeriesIdAndCharacterID(Long seriesId, Long characterId);

    List<CharacterCommand> listCharactersBySeriesId(Long seriesId);
}
