package dev.citralflo.animelist.services;

import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.repositories.SeriesRepository;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ImageServiceImplTest {

    @Mock
    SeriesRepository seriesRepository;

    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        imageService = new ImageServiceImpl(seriesRepository);
    }

    @Test
    void saveImageFile() throws IOException {
        //given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework".getBytes());

        Series series = new Series();
        series.setId(id);
        when(seriesRepository.findById(anyLong())).thenReturn(java.util.Optional.of(series));

        ArgumentCaptor<Series> seriesArgumentCaptor = ArgumentCaptor.forClass(Series.class);

        //when
        imageService.saveImageFile(id, multipartFile);

        //then
        verify(seriesRepository, times(1)).save(seriesArgumentCaptor.capture());
        Series savedSeries = seriesArgumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedSeries.getImage().length);
    }
}