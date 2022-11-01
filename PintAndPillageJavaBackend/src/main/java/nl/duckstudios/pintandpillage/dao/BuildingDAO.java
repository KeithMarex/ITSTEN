package nl.duckstudios.pintandpillage.dao;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.entity.buildings.Building;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildingDAO {

    private final BuildingRepository buildingRepository;

    public Building getBuilding(long id){
        return this.buildingRepository.findById(id);
    }
}
