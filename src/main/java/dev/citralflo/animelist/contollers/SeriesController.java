package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/series/new")
    public String newSeries(Model model) {
        model.addAttribute("series", new SeriesCommand());

        return "series/form";
    }

    @PostMapping
    @RequestMapping("series/save")
    public String saveOrUpdate(@ModelAttribute SeriesCommand command) {
        SeriesCommand savedCommand = seriesService.saveSeriesCommand(command);

        return "redirect:/series/view/" + savedCommand.getId();
    }
}
