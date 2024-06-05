package dev.citralflo.animelist.services;

import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.converters.command2object.VoiceActorCommandToVoiceActorConverter;
import dev.citralflo.animelist.converters.object2command.VoiceActorToVoiceActorCommandConverter;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.repositories.VoiceActorRepository;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VoiceActorServiceImplTest {

    VoiceActorToVoiceActorCommandConverter voiceActorToVoiceActorCommandConverter = new VoiceActorToVoiceActorCommandConverter();
    VoiceActorCommandToVoiceActorConverter voiceActorCommandToVoiceActorConverter = new VoiceActorCommandToVoiceActorConverter();
    VoiceActorService voiceActorService;

    @Mock
    VoiceActorRepository voiceActorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        voiceActorService = new VoiceActorServiceImpl(voiceActorRepository, voiceActorToVoiceActorCommandConverter, voiceActorCommandToVoiceActorConverter);
    }

    @Test
    void listVoiceActors() {
        // given
        Set<VoiceActor> voiceActors = new HashSet<>();
        VoiceActor voiceActor = new VoiceActor();
        voiceActor.setId(1L);
        voiceActors.add(voiceActor);

        VoiceActor voiceActor2 = new VoiceActor();
        voiceActor2.setId(2L);
        voiceActors.add(voiceActor2);

        when(voiceActorRepository.findAll()).thenReturn(voiceActors);

        // when
        Map<Long, VoiceActorCommand> voiceActorCommands = voiceActorService.listVoiceActors();

        // then
        assertEquals(2, voiceActorCommands.size());
        verify(voiceActorRepository, times(1)).findAll();
    }
}