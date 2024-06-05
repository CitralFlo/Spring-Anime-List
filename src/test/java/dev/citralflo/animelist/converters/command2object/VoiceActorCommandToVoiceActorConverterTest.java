package dev.citralflo.animelist.converters.command2object;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.VoiceActor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoiceActorCommandToVoiceActorConverterTest {

    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";

    VoiceActorCommandToVoiceActorConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new VoiceActorCommandToVoiceActorConverter();
    }


    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new VoiceActorCommand()));
    }

    @Test
    void convert() {
        //given
        VoiceActorCommand voiceActorCommand = new VoiceActorCommand();
        voiceActorCommand.setId(ID);
        voiceActorCommand.setName(NAME);
        voiceActorCommand.setImageUrl(IMAGE_URL);

        //when
        VoiceActor voiceActor = converter.convert(voiceActorCommand);



        //then
        assertEquals(ID, voiceActor.getId());
        assertEquals(NAME, voiceActor.getName());
        assertEquals(IMAGE_URL, voiceActor.getImageUrl());
    }
}