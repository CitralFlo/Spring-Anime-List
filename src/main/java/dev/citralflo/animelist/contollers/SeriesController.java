package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.GenreService;
import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SeriesController {

    private final SeriesService seriesService;
    private final GenreService genreService;

    public SeriesController(SeriesService seriesRepository, GenreService genreService) {
        this.seriesService = seriesRepository;
        this.genreService = genreService;
    }

    @GetMapping("/series/{id}/view")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("series", seriesService.getSeriesById(Long.valueOf(id)));

        return "series/view";
    }

    @GetMapping("/series/new")
    public String newSeries(Model model) {
        model.addAttribute("series", new SeriesCommand());

        model.addAttribute("allGenres", genreService.getGenres());

        return "series/new";
    }

    @GetMapping("series/{id}/update")
    public String updateSeries(@PathVariable String id, Model model) {
        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(id)));
        model.addAttribute("allGenres", genreService.getGenres());

        return "series/update";
    }

    @GetMapping("/series/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("Deleting id: " + id);

        seriesService.deleteSeriesById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("/series/save")
    public String saveOrUpdateSeries(@ModelAttribute SeriesCommand seriesCommand) {

        if (seriesCommand.getId() != null) {
            seriesCommand.setCharacters_id(seriesService.getSeriesCommandById(seriesCommand.getId()).getCharacters_id());
        }

        // Save or update the series
        SeriesCommand savedSeriesCommand = seriesService.saveSeriesCommand(seriesCommand);
        return "redirect:/series/" + savedSeriesCommand.getId() + "/view";
    }
}
