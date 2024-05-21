package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IndexControllerTest {

    @Mock
    SeriesRepository seriesRepository;
    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

         controller = new IndexController(seriesRepository);
    }

    @Test
    void testMockMVC() throws Exception {
         MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

            mockMvc.perform(MockMvcRequestBuilders.get("/"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void getIndex() {

        Set<Series> series = new HashSet<>();
        series.add(new Series());

        Series series1 = new Series();
        series1.setId(1L);
        series.add(series1);

        when(seriesRepository.findAll()).thenReturn(series);

        ArgumentCaptor<Set<Series>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String index = controller.getIndex(model);

        assertEquals("index", index);
        verify(seriesRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("animes"), argumentCaptor.capture());
        Set<Series> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}