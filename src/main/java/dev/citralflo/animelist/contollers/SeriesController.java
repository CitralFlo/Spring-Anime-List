package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesRepository) {
        this.seriesService = seriesRepository;
    }

    @RequestMapping("/series/{id}/view")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("series", seriesService.getSeriesById(Long.valueOf(id)));

        return "series/view";
    }

    @RequestMapping("/series/new")
    public String newSeries(Model model) {
        model.addAttribute("series", new SeriesCommand());

        return "series/form";
    }

    @RequestMapping("series/{id}/update")
    public String updateSeries(@PathVariable String id, Model model) {
        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(id)));

        return "series/form";
    }

    @GetMapping
    @RequestMapping("/series/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id: " + id);

        seriesService.deleteSeriesById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("series/save")
    public String saveOrUpdateSeries(@ModelAttribute SeriesCommand command) {
        SeriesCommand savedCommand = seriesService.saveSeriesCommand(command);

        return "redirect:/series/" + savedCommand.getId().longValue() + "/view";
    }
}
