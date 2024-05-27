package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.services.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class SeriesControllerTest {

    @Mock
    SeriesService seriesService;

    SeriesController controller;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        controller = new SeriesController(seriesService);
    }

    @Test
    void showById() throws Exception {
        Series series = new Series();
        series.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(seriesService.getSeriesById(1L)).thenReturn(series);

        mockMvc.perform(get("/series/view/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("series/view"))
                .andExpect(model().attributeExists("series"));
    }
}