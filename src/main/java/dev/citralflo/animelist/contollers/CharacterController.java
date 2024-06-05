package dev.citralflo.animelist.contollers;

import dev.citralflo.animelist.commands.CharacterCommand;
import dev.citralflo.animelist.commands.VoiceActorCommand;
import dev.citralflo.animelist.services.CharacterService;
import dev.citralflo.animelist.services.SeriesService;
import dev.citralflo.animelist.services.VoiceActorService;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CharacterController {

    private final SeriesService seriesService;
    private final CharacterService characterService;
    private final VoiceActorService voiceActorService;

    public CharacterController(SeriesService seriesService, CharacterService characterService, VoiceActorService voiceActorService) {
        this.seriesService = seriesService;
        this.characterService = characterService;
        this.voiceActorService = voiceActorService;
    }

    @GetMapping("/series/{seriesId}/characters")
    public String listCharacters(@PathVariable String seriesId, Model model) {
        log.debug("Getting character list " + seriesId);

        List<CharacterCommand> characterCommands = characterService.listCharactersBySeriesId(Long.valueOf(seriesId));

        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(seriesId)));
        model.addAttribute("characters", characterCommands);
        model.addAttribute("allVoiceActors", voiceActorService.listVoiceActors());

        return "series/character/list";
    }

    @GetMapping("/series/{seriesId}/character/{characterId}/view")
    public String showCharacter(@PathVariable String seriesId, @PathVariable String characterId, Model model) {
        log.debug("Getting character" + characterId + " for series" + seriesId);

        CharacterCommand characterCommand = characterService.findCharacterBySeriesIdAndCharacterId(Long.valueOf(seriesId), Long.valueOf(characterId));


        model.addAttribute("character", characterCommand);
        model.addAttribute("series", seriesService.getSeriesCommandById(Long.valueOf(seriesId)));
        model.addAttribute("voiceActor", voiceActorService.getVoiceActorCommandById(characterCommand.getVoiceActorId()));

        return "series/character/view";
    }

    @GetMapping("/series/{seriesId}/character/{characterId}/update")
    public String updateCharacter(@PathVariable String seriesId, @PathVariable String characterId, Model model) {
        log.debug("Updating character" + characterId + " for series" + seriesId);

        model.addAttribute("character", characterService.findCharacterBySeriesIdAndCharacterId(Long.valueOf(seriesId), Long.valueOf(characterId)));
        model.addAttribute("allVoiceActors", voiceActorService.listVoiceActors());

        return "series/character/form";
    }

    @PostMapping("/character/save")
    public String saveOrUpdate(@ModelAttribute CharacterCommand command) {
        CharacterCommand savedCommand = characterService.saveCharacterCommand(command);

        return "redirect:/series/" + command.getSeriesId() + "/character/" + savedCommand.getId() + "/view";
    }

    @GetMapping("/series/{seriesId}/character/new")
    public String newCharacter(@PathVariable String seriesId, Model model) {
        log.debug("Creating new character for series" + seriesId);

        CharacterCommand characterCommand = new CharacterCommand();
        characterCommand.setSeriesId(Long.valueOf(seriesId));

        model.addAttribute("character", characterCommand);
        model.addAttribute("allVoiceActors", voiceActorService.listVoiceActors());

        return "series/character/form";
    }

    @GetMapping("/series/{seriesId}/character/{characterId}/delete")
    public String deleteCharacter(@PathVariable String seriesId, @PathVariable String characterId) {
        log.debug("Deleting character" + characterId + " for series" + seriesId);

        characterService.deleteCharacterBySeriesIdAndCharacterID(Long.valueOf(seriesId), Long.valueOf(characterId));

        return "redirect:/series/" + seriesId + "/characters";
    }

}
