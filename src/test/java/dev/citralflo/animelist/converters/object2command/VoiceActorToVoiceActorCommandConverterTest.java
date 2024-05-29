package dev.citralflo.animelist.converters.object2command;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.VoiceActor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoiceActorToVoiceActorCommandConverterTest {

    public static final Long ID = 1L;
    public static final String NAME = "name";

    VoiceActorToVoiceActorCommandConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new VoiceActorToVoiceActorCommandConverter();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new VoiceActor()));
    }

    @Test
    void convert() {
        //given
        VoiceActor voiceActor = new VoiceActor();
        voiceActor.setId(ID);
        voiceActor.setName(NAME);

        //when
        VoiceActorCommand voiceActorCommand = converter.convert(voiceActor);

        //then
        assertNotNull(voiceActorCommand);
        assertEquals(ID, voiceActorCommand.getId());
        assertEquals(NAME, voiceActorCommand.getName());
    }
}