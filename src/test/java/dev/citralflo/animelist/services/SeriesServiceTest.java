package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SeriesServiceTest {

    SeriesService seriesService;
    @Mock
    dev.citralflo.animelist.repositories.SeriesRepository seriesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        seriesService = new SeriesServiceImpl(seriesRepository);
    }

    @Test
    void getSeriesById() {
        Series anime = new Series();
        anime.setId(1L);

        when(seriesRepository.findById(1L)).thenReturn(java.util.Optional.of(anime));

        Series series = seriesService.getSeriesById(1L);

        assertEquals(1L, series.getId());
        verify(seriesRepository, times(1)).findById(1L);
    }


    @Test
    void getSeries() {
        Series anime = new Series();
        HashSet<Series> seriesData = new HashSet<>();
        seriesData.add(anime);

        when(seriesRepository.findAll()).thenReturn(seriesData);



        Set<Series> series = seriesService.getSeries();

        assertEquals(1, series.size());
        verify(seriesRepository, times(1)).findAll();
    }
}