package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.SeriesService;
import dev.citralflo.animelist.services.VoiceActorService;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class CharacterControllerTest {

    @Mock
    SeriesService seriesService;

    @Mock
    CharacterService characterService;

    @Mock
    VoiceActorService voiceActorService;

    CharacterController characterController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        characterController = new CharacterController(seriesService, characterService, voiceActorService);
        mockMvc = MockMvcBuilders.standaloneSetup(characterController).build();
    }

    @Test
    void listCharacters() throws Exception {
        //when
        SeriesCommand seriesCommand = new SeriesCommand();
        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        //given
        mockMvc.perform(get("/series/1/characters"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/character/list"))
                .andExpect(model().attributeExists("series"));

    }

    @Test
    void showCharacter() throws Exception {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();

        CharacterCommand character = new CharacterCommand();
        seriesCommand.setCharacters(List.of(character));

        //when
        when(characterService.findCharacterBySeriesIdAndCharacterId(anyLong(), anyLong())).thenReturn(seriesCommand.getCharacters().get(0));
        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        //given
        mockMvc.perform(get("/series/1/character/1/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/character/view"))
                .andExpect(model().attributeExists("character"));
    }

    @Test
    void updateCharacter() throws Exception {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setCharacters(List.of(new CharacterCommand()));

        //when
        when(characterService.findCharacterBySeriesIdAndCharacterId(anyLong(), anyLong())).thenReturn(seriesCommand.getCharacters().get(0));
        when(voiceActorService.listVoiceActors()).thenReturn(Set.of(new VoiceActorCommand()));
        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        //given
        mockMvc.perform(get("/series/1/character/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/character/form"))
                .andExpect(model().attributeExists("character"))
                .andExpect(model().attributeExists("allVoiceActors"));
    }

    @Test
    void testNewCharacterForm() throws Exception {
        //given
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setCharacters(List.of(new CharacterCommand()));

        //when
        when(characterService.findCharacterBySeriesIdAndCharacterId(anyLong(), anyLong())).thenReturn(seriesCommand.getCharacters().get(0));
        when(voiceActorService.listVoiceActors()).thenReturn(Set.of(new VoiceActorCommand()));
        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        //given
        mockMvc.perform(get("/series/1/character/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/character/form"))
                .andExpect(model().attributeExists("character"))
                .andExpect(model().attributeExists("allVoiceActors"));
    }

    @Test
    void deleteCharacter() throws Exception {
        mockMvc.perform(get("/series/1/character/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/series/1/characters"));

        verify(characterService, times(1)).deleteCharacterBySeriesIdAndCharacterID(anyLong(), anyLong());
    }
}