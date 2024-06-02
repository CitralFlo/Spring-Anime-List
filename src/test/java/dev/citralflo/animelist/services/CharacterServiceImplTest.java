package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.converters.command2object.CharacterCommandToCharacterConverter;
import dev.citralflo.animelist.converters.command2object.VoiceActorCommandToVoiceActorConverter;
import dev.citralflo.animelist.converters.object2command.CharacterToCharacterCommandConverter;
import dev.citralflo.animelist.converters.object2command.VoiceActorToVoiceActorCommandConverter;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class CharacterServiceImplTest {

    private final VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter = new VoiceActorToVoiceActorCommandConverter();

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private CharacterService characterService;

    private CharacterToCharacterCommandConverter characterToCharacterCommandConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        characterToCharacterCommandConverter = new CharacterToCharacterCommandConverter(voiceActorToVoiceActorCommandConverter);
    }

    @Test
    void findCharacterBySeriesIdAndCharacterId() {
        //given
        Long seriesId = 1L;
        Long characterId = 1L;

        Series series = new Series();
        series.setId(seriesId);

        Character character = new Character();
        character.setId(characterId);

        series.addCharacter(character);
        this.seriesRepository.save(series);

        //when
        CharacterCommand characterCommand = characterToCharacterCommandConverter.convert(character);

        this.characterService.saveCharacterCommand(characterCommand);

        this.characterService.findCharacterBySeriesIdAndCharacterId(seriesId, characterId);

        //then
        assertEquals(characterId, characterCommand.getId());
    }

    @Test
    void saveCharacterCommand() {
        //given
        Long seriesId = 1L;
        Long characterId = 1L;

        Series series = new Series();
        series.setId(seriesId);

        Character character = new Character();
        character.setId(characterId);

        series.addCharacter(character);
        this.seriesRepository.save(series);

        //when
        CharacterCommand characterCommand = characterToCharacterCommandConverter.convert(character);

        this.characterService.saveCharacterCommand(characterCommand);

        //then
        assertEquals(characterId, characterCommand.getId());
    }
}