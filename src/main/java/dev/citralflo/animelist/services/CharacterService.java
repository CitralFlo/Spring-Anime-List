package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.CharacterCommand;

public interface CharacterService {

    CharacterCommand findCharacterBySeriesIdAndCharacterId(Long seriesId, Long characterId);
}
