package nl.duckstudios.pintandpillage.dao;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.travels.AttackCombatTravel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelDao {

    private final TravelRepository travelRepository;

    public void insertAttack(AttackCombatTravel travel) {
        this.travelRepository.save(travel);
    }

}
