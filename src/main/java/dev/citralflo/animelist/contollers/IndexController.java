package dev.citralflo.animelist.contollers;


import dev.citralflo.animelist.repositories.SeriesRepository;
import dev.citralflo.animelist.services.SeriesService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final SeriesRepository seriesRepository;

    public IndexController(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @RequestMapping({"/", "/index", ""})
    public String getIndex(Model model) {

        model.addAttribute("animes", this.seriesRepository.findAll());

        return "index";
    }

}
