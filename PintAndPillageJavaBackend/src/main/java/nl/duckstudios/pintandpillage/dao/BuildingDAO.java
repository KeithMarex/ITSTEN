package nl.duckstudios.pintandpillage.dao;

import nl.duckstudios.pintandpillage.entity.buildings.Building;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class BuildingDAO {

    private final BuildingRepository buildingRepository;

    public BuildingDAO(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public Building getBuilding(long id) throws EntityNotFoundException {
        return this.buildingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
