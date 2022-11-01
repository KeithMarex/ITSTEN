package nl.duckstudios.pintandpillage.service;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.dao.UserDAO;
import nl.duckstudios.pintandpillage.entity.User;
import nl.duckstudios.pintandpillage.model.UserHighscore;
import nl.duckstudios.pintandpillage.model.WorldVillage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HighscoreService {

    private final UserDAO userDAO;
    private final VillageService villageService;

    public List<UserHighscore> getHighscore() {
        List<UserHighscore> highscores = new ArrayList<>();
        var villages = this.villageService.getWorldVillages();
        for (WorldVillage village : villages) {
            Optional<User> user = this.userDAO.findUsernameById(village.userId);
            if (user.isPresent()) {
                UserHighscore newHighscore = new UserHighscore(user.get().getUsername(), village.points);
                Optional<UserHighscore> existingScore = highscores.stream().filter(h -> h.username.equals(user.get().getUsername())).findFirst();
                if (existingScore.isPresent()) {
                    existingScore.get().totalPoints += newHighscore.totalPoints;
                    continue;
                }
                highscores.add(newHighscore);
            }

        }
        return highscores;
    }
}
