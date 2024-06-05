package dev.citralflo.animelist.contollers;


import dev.citralflo.animelist.repositories.SeriesRepository;

import dev.citralflo.animelist.services.GenreService;
import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final SeriesService seriesService;
    private final GenreService genreService;

    public IndexController(SeriesService seriesService, GenreService genreService) {
        this.seriesService = seriesService;
        this.genreService = genreService;
    }

    @RequestMapping({"/", "/index", ""})
    public String getIndex(Model model) {
        log.debug("Controller is working.");

        model.addAttribute("animes", this.seriesService.getSeries());
        model.addAttribute("allGenres", this.genreService.getGenres());

        return "index";
    }

}
