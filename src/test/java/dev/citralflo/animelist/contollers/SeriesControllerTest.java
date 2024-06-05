package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.model.Genre;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.services.GenreService;
import dev.citralflo.animelist.services.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class SeriesControllerTest {

    @Mock
    SeriesService seriesService;

    @Mock
    GenreService genreService;

    SeriesController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        controller = new SeriesController(seriesService, genreService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetSeries() throws Exception {

        Series series = new Series();
        series.setId(1L);

        when(seriesService.getSeriesById(1L)).thenReturn(series);

        mockMvc.perform(get("/series/1/view"))
            .andExpect(status().isOk())
            .andExpect(view().name("series/view"))
            .andExpect(model().attributeExists("series"));
    }

    @Test
    void showById() throws Exception {
        Series series = new Series();

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Action");


        series.getGenres().add(genre);
        series.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(seriesService.getSeriesById(1L)).thenReturn(series);

        //check if genre name is passed

        assertEquals("Action", series.getGenres().iterator().next().getName());

        mockMvc.perform(get("/series/1/view"))
            .andExpect(status().isOk())
            .andExpect(view().name("series/view"))
            .andExpect(model().attributeExists("series"));
    }


    @Test
    void testNewSeries() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/series/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("series/new"))
            .andExpect(model().attributeExists("series"));
    }

    @Test
    void testPostNewSeriesForm() throws Exception {
        SeriesCommand command = new SeriesCommand();
        command.setId(2L);

        when(seriesService.saveSeriesCommand(any())).thenReturn(command);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/series/save")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //           .param("id", "")
                //          .param("title", "title")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/series/2/view"));
    }

    @Test
    void testDeleteAction() throws Exception {
        mockMvc.perform(get("/series/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

        verify(seriesService).deleteSeriesById(1L);
    }
}