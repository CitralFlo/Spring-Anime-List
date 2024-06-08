package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.ImageService;
import dev.citralflo.animelist.services.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    SeriesService seriesService;

    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        imageController = new ImageController(imageService, seriesService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void getImageForm() throws Exception {
        // given
        Long id = 1L;
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(id);

        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        // when
        mockMvc.perform(get("/series/1/image"))
            .andExpect(status().isOk())
            .andExpect(view().name("imageForm"))
            .andExpect(model().attributeExists("series"));

        verify(seriesService, times(1)).getSeriesCommandById(anyLong());
    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain", "Spring Framework".getBytes());

        mockMvc.perform(multipart("/series/1/image").file(multipartFile))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/series/1/view"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    void renderImageFromDB() throws Exception {
        // given
        Long id = 1L;
        SeriesCommand seriesCommand = new SeriesCommand();
        seriesCommand.setId(id);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        seriesCommand.setImage(bytesBoxed);

        when(seriesService.getSeriesCommandById(anyLong())).thenReturn(seriesCommand);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/series/1/seriesimage"))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);
    }
}