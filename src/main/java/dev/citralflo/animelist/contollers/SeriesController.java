package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesRepository) {
        this.seriesService = seriesRepository;
    }

    @RequestMapping("/series/view/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("series", seriesService.getSeriesById(Long.valueOf(id)));

        return "series/view";
    }


}
