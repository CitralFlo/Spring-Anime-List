package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CharacterController {

    private final SeriesService seriesService;
    private final CharacterService characterService;

    public CharacterController(SeriesService seriesService, CharacterService characterService) {
        this.seriesService = seriesService;
        this.characterService = characterService;
    }

    @GetMapping
    @RequestMapping("/series/{seriesId}/characters")
    public String listCharacters(@PathVariable String seriesId, Model model) {
        log.debug("Getting character list" + seriesId);

        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(seriesId)));

        return "series/character/list";
    }

    @GetMapping
    @RequestMapping("/series/{seriesId}/character/{characterId}/view")
    public String showCharacter(@PathVariable String seriesId, @PathVariable String characterId, Model model) {
        log.debug("Getting character" + characterId + " for series" + seriesId);

        model.addAttribute("character", characterService.findCharacterBySeriesIdAndCharacterId(Long.valueOf(seriesId), Long.valueOf(characterId)));
        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(seriesId)));

        return "series/character/view";
    }
}
