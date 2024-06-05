package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.services.VoiceActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class CharacterCommandToCharacterConverterTest {

    public static final Long ID = 1L;
    public static final Long VA_ID = 2L;
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";

    CharacterCommandToCharacterConverter converter;

    @Mock
    VoiceActorService voiceActorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.converter = new CharacterCommandToCharacterConverter(new VoiceActorCommandToVoiceActorConverter(), voiceActorService);
    }


    @Test
    void convert() throws Exception{
        //given
        CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setId(ID);
        characterCommand.setName(NAME);
        characterCommand.setImageUrl(IMAGE_URL);
        characterCommand.setVoiceActorId(VA_ID);

        //when
        Character character = converter.convert(characterCommand);

        //then
        assertNotNull(character);
        assertEquals(ID, character.getId());
        assertEquals(NAME, character.getName());
        assertEquals(IMAGE_URL, character.getImageUrl());
    }

    @Test
    void convertWithNullVA() throws Exception {
        //given
        CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setId(ID);
        characterCommand.setName(NAME);
        characterCommand.setImageUrl(IMAGE_URL);

        //when
        Character character = converter.convert(characterCommand);

        //then
        assertNotNull(character);
        assertNull(character.getVoiceActor());
        assertEquals(ID, character.getId());
        assertEquals(NAME, character.getName());
        assertEquals(IMAGE_URL, character.getImageUrl());

    }
}