package nl.duckstudios.pintandpillage.controller;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.User;
import nl.duckstudios.pintandpillage.entity.WorldMap;
import nl.duckstudios.pintandpillage.model.SettleableSpots;
import nl.duckstudios.pintandpillage.model.UserHighscore;
import nl.duckstudios.pintandpillage.service.AuthenticationService;
import nl.duckstudios.pintandpillage.service.HighscoreService;
import nl.duckstudios.pintandpillage.service.VillageService;
import nl.duckstudios.pintandpillage.service.WorldService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/world")
@RequiredArgsConstructor
public class WorldController {

    private final WorldService worldService;

    private final AuthenticationService authenticationService;

    private final HighscoreService highscoreService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public WorldMap getWorld() {
        return this.worldService.getWorldMap();
    }

    @RequestMapping(value = "settlespots/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SettleableSpots getSettleableSpots(@PathVariable long id) throws EntityNotFoundException {
        User user = this.authenticationService.getAuthenticatedUser();

        return this.worldService.getSettleableSpots(id, user);
    }

    @RequestMapping(value = "highscore", method = RequestMethod.GET)
    @ResponseBody
    public List<UserHighscore> getHighscore() {
        return this.highscoreService.getHighscore();
    }
}
