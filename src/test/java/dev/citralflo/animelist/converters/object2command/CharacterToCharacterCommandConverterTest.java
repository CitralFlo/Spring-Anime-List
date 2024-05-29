package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.VoiceActor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterToCharacterCommandConverterTest {

    public static final Long ID = 1L;
    public static final Long VA_ID = 2L;
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";

    CharacterToCharacterCommandConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new CharacterToCharacterCommandConverter(new VoiceActorToVoiceActorCommandConverter());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Character()));
    }

    @Test
    void convert() {
        //given
        Character character = new Character();
        character.setId(ID);
        character.setName(NAME);
        character.setImageUrl(IMAGE_URL);
        VoiceActor voiceActor = new VoiceActor();
        voiceActor.setId(VA_ID);
        character.setVoiceActor(voiceActor);

        //when
        CharacterCommand characterCommand = converter.convert(character);

        //then
        assertNotNull(characterCommand);
        assertNotNull(characterCommand.getVoiceActor());
        assertEquals(ID, characterCommand.getId());
        assertEquals(NAME, characterCommand.getName());
        assertEquals(IMAGE_URL, characterCommand.getImageUrl());
        assertEquals(VA_ID, characterCommand.getVoiceActor().getId());
    }

    @Test
    void convertWithNullVA() {
        //given
        Character character = new Character();
        character.setId(ID);
        character.setName(NAME);
        character.setImageUrl(IMAGE_URL);

        //when
        CharacterCommand characterCommand = converter.convert(character);

        //then
        assertNotNull(characterCommand);
        assertNull(characterCommand.getVoiceActor());
        assertEquals(ID, characterCommand.getId());
        assertEquals(NAME, characterCommand.getName());
        assertEquals(IMAGE_URL, characterCommand.getImageUrl());
    }
}