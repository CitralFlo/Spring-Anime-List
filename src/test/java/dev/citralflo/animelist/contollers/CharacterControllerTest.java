package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.SeriesService;
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

    CharacterController characterController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        characterController = new CharacterController(seriesService, characterService);
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
        mockMvc.perform(get("/series/1/character/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/character/show"))
                .andExpect(model().attributeExists("character"));
    }
}