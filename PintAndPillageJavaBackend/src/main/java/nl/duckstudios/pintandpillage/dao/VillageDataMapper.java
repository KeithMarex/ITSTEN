package nl.duckstudios.pintandpillage.dao;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Building;
import nl.duckstudios.pintandpillage.model.WorldVillage;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VillageDataMapper {

    private final VillageDAO villageDAO;

    public Village getVillage(long id) throws EntityNotFoundException {
        Village village = villageDAO.getVillage(id).stream()
            .filter(v -> Optional.ofNullable(v).isPresent())
            .findFirst().orElseThrow(EntityNotFoundException::new);

        village.updateVillageState();

        return this.villageDAO.save(village);
    }

    public List<Village> getVillages(long id) {
        return villageDAO.getVillages(id);
    }

    public Village createBuilding(Village village, Building building) {
        building.setVillage(village);
        building.levelUp();
        village.createBuilding(building);
        village.updateVillageState();
        return villageDAO.save(village);
    }

    public void update(Village village) {
        this.villageDAO.save(village);
    }

    public List<WorldVillage> getWorldVillages() {
        List<Village> allVillages = this.villageDAO.getVillages();
        List<WorldVillage> worldVillages = new ArrayList<>();
        for (Village village : allVillages) {
            worldVillages.add(new WorldVillage(
                    village.getVillageId(),
                    village.getUser().getUsername(),
                    new Coord(village.getPositionX(), village.getPositionY()),
                    village.getName(),
                    Math.toIntExact(village.getUser().getId()),
                    village.getVillagePoints()
            ));
        }

        return worldVillages;
    }
}
