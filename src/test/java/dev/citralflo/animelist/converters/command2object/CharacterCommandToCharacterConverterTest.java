package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.model.VoiceActor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterCommandToCharacterConverterTest {

    public static final Long ID = 1L;
    public static final Long VA_ID = 2L;
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";

    CharacterCommandToCharacterConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new CharacterCommandToCharacterConverter(new VoiceActorCommandToVoiceActorConverter());
    }

    @Test
    void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new CharacterCommand()));
    }

    @Test
    void convert() throws Exception{
        //given
        CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setId(ID);
        characterCommand.setName(NAME);
        characterCommand.setImageUrl(IMAGE_URL);
        VoiceActorCommand voiceActorCommand = new VoiceActorCommand();
        voiceActorCommand.setId(VA_ID);
        characterCommand.setVoiceActor(voiceActorCommand);

        //when
        Character character = converter.convert(characterCommand);

        //then
        assertNotNull(character);
        assertNotNull(character.getVoiceActor());
        assertEquals(ID, character.getId());
        assertEquals(NAME, character.getName());
        assertEquals(IMAGE_URL, character.getImageUrl());
        assertEquals(VA_ID, character.getVoiceActor().getId());
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