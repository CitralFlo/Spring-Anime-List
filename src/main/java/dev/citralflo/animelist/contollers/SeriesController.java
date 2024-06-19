package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.SeriesCommand;
import dev.citralflo.animelist.services.GenreService;
import dev.citralflo.animelist.services.SeriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    private static final String SESSION_LAST_SERIES_ID = "lastVisitedSeriesId";

    public SeriesController(SeriesService seriesService, GenreService genreService) {
        this.seriesService = seriesService;
        this.genreService = genreService;
    }

    @GetMapping("/series/{id}/view")
    public String showById(@PathVariable String id, Model model, HttpServletRequest request) {
        model.addAttribute("series", seriesService.getSeriesById(Long.valueOf(id)));

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_LAST_SERIES_ID, id);

        return "series/view";
    }

    @GetMapping("/series/new")
    public String newSeries(Model model) {
        model.addAttribute("series", new SeriesCommand());
        model.addAttribute("allGenres", genreService.getGenres());

        return "series/new";
    }

    @GetMapping({"/series/{id}/update", "/series/{id}/edit"})
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
            seriesCommand.setImage(seriesService.getSeriesCommandById(seriesCommand.getId()).getImage());
        }

        // Save or update the series
        SeriesCommand savedSeriesCommand = seriesService.saveSeriesCommand(seriesCommand);
        return "redirect:/series/" + savedSeriesCommand.getId() + "/view";
    }

    @GetMapping("/series/random")
    public String randomSeries(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lastVisited = (String) session.getAttribute(SESSION_LAST_SERIES_ID);

        int count = seriesService.getSeries().size();

        int randomId;

        if (count == 0) {
            return "redirect:/";
        }

        if (count == 1) {
            return "redirect:/series/1/view";
        }

        if (lastVisited != null) {
            do {
                randomId = (int) (Math.random() * count) + 1;
            } while (String.valueOf(randomId).equals(lastVisited) && seriesService.getSeriesById((long) randomId) != null);
        } else {
            randomId = (int) (Math.random() * count) + 1;
        }

        session.setAttribute(SESSION_LAST_SERIES_ID, String.valueOf(randomId));

        return "redirect:/series/" + randomId + "/view";
    }
}
