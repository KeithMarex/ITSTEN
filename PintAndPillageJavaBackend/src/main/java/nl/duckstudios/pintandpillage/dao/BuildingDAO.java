package nl.duckstudios.pintandpillage.dao;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.buildings.Building;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class BuildingDAO {

    private final BuildingRepository buildingRepository;

    public Building getBuilding(long id) throws EntityNotFoundException {
        return this.buildingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
