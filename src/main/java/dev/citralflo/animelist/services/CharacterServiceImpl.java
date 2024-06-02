package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.converters.command2object.CharacterCommandToCharacterConverter;
import dev.citralflo.animelist.converters.object2command.CharacterToCharacterCommandConverter;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterToCharacterCommandConverter characterToCharacterCommandConverter;
    private final SeriesRepository seriesRepository;
    private final CharacterCommandToCharacterConverter characterCommandToCharacterConverter;


    public CharacterServiceImpl(CharacterToCharacterCommandConverter characterToCharacterCommandConverter, SeriesRepository seriesRepository, CharacterCommandToCharacterConverter characterCommandToCharacterConverter) {
        this.characterToCharacterCommandConverter = characterToCharacterCommandConverter;
        this.seriesRepository = seriesRepository;
        this.characterCommandToCharacterConverter = characterCommandToCharacterConverter;
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

    @Override
    public CharacterCommand saveCharacterCommand(CharacterCommand characterCommand) {
        Optional<Series> seriesOptional = seriesRepository.findById(characterCommand.getSeriesId());

        if (seriesOptional.isEmpty()) {
            log.error("Series not found");
            return new CharacterCommand();
        }

        Series series = seriesOptional.get();
        Optional<Character> characterOptional = series.getCharacters().stream()
            .filter(character -> character.getId().equals(characterCommand.getId()))
            .findFirst();

        if (characterOptional.isPresent()) {
            Character character = characterOptional.get();
            character.setName(characterCommand.getName());
            character.setImageUrl(characterCommand.getImageUrl());
            character.setVoiceActor(characterCommandToCharacterConverter.convert(characterCommand).getVoiceActor());
        }
        else {
            Character character = characterCommandToCharacterConverter.convert(characterCommand);
            series.addCharacter(character);
        }

        Series savedSeries = seriesRepository.save(series);

        Optional<Character> savedCharacterOptional = savedSeries.getCharacters().stream()
            .filter(character -> character.getName().equals(characterCommand.getName()))
            .filter(character -> character.getImageUrl().equals(characterCommand.getImageUrl()))
            .findFirst();

        if (savedCharacterOptional.isEmpty()) {
            log.error("Character not found");
            return new CharacterCommand();
        }

        return characterToCharacterCommandConverter.convert(savedCharacterOptional.get());
    }

    @Override
    public void deleteCharacterBySeriesIdAndCharacterID(Long seriesId, Long characterId) {
        Optional<Series> seriesOptional = seriesRepository.findById(seriesId);

        if (seriesOptional.isPresent()) {
            Series series = seriesOptional.get();
            log.debug("Series found");

            Optional<Character> characterOptional = series.getCharacters().stream()
                .filter(character -> character.getId().equals(characterId))
                .findFirst();

            if (characterOptional.isPresent()) {
                Character character = characterOptional.get();
                log.debug("Character found");
                series.getCharacters().remove(character);
                seriesRepository.save(series);

                log.debug("Character deleted");
            }
            else {
                log.error("Character not found");
            }
        }
        else {
            log.error("Series not found");
        }
    }

}
