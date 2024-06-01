package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.converters.object2command.CharacterToCharacterCommandConverter;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterToCharacterCommandConverter characterToCharacterCommandConverter;
    private final SeriesRepository seriesRepository;

    public CharacterServiceImpl(CharacterToCharacterCommandConverter characterToCharacterCommandConverter, SeriesRepository seriesRepository) {
        this.characterToCharacterCommandConverter = characterToCharacterCommandConverter;
        this.seriesRepository = seriesRepository;
    }

    @Override
    public CharacterCommand findCharacterBySeriesIdAndCharacterId(Long seriesId, Long characterId) {

        Optional<Series> seriesOptional = seriesRepository.findById(seriesId);

        if (seriesOptional.isEmpty()) {
            log.error("Series not found");
        }

        Series series = seriesOptional.get();

        Optional<CharacterCommand> characterCommandOptional = series.getCharacters().stream()
                .filter(character -> character.getId().equals(characterId))
                .map(characterToCharacterCommandConverter::convert)
                .findFirst();

        if (characterCommandOptional.isEmpty()) {
            log.error("Character not found");
        }

        return characterCommandOptional.get();
    }
}
