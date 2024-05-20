package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.repositories.SeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void getIndex() {



        String index = controller.getIndex(model);

        assertEquals("index", index);
        verify(seriesRepository, times(1)).findAll();
        verify(model, times(1)).addAttribute("animes", seriesRepository.findAll());
    }
}