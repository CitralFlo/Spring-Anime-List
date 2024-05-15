package dev.citralflo.animelist.contollers;


import dev.citralflo.animelist.repositories.SeriesRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final SeriesRepository seriesRepository;

    public IndexController(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @RequestMapping({"/", "/index", ""})
    public String getIndex(Model model) {
        log.debug("Controller is working.");

        model.addAttribute("animes", this.seriesRepository.findAll());

        return "index";
    }

}
