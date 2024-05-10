package dev.citralflo.animelist.contollers;


import dev.citralflo.animelist.services.SeriesService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final SeriesService seriesService;

    public IndexController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @RequestMapping({"/", "/index", ""})
    public String getIndex(Model model) {

        model.addAttribute("animes", this.seriesService.getSeries());

        return "index";
    }

}
