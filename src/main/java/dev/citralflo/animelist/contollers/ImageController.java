package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.ImageService;
import dev.citralflo.animelist.services.SeriesService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final SeriesService seriesService;

    public ImageController(ImageService imageService, SeriesService seriesService) {
        this.imageService = imageService;
        this.seriesService = seriesService;
    }

    @GetMapping("series/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model) {

        model.addAttribute("series", seriesService.getSeriesCommandById(id));
        return "imageForm";
    }

    @PostMapping("series/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imageFile") MultipartFile file) {

        imageService.saveImageFile(id, file);

        return "redirect:/series/" + id + "/view";
    }

    @GetMapping("series/{id}/seriesimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
        SeriesCommand seriesCommand = seriesService.getSeriesCommandById(id);

        byte[] byteArray = new byte[seriesCommand.getImage().length];

        int i = 0;

        for (Byte wrappedByte : seriesCommand.getImage()) {
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());
    }
}
