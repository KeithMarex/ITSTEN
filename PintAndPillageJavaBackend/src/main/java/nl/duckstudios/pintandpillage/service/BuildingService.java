package nl.duckstudios.pintandpillage.service;

import lombok.RequiredArgsConstructor;
import nl.duckstudios.pintandpillage.dao.BuildingDAO;
import nl.duckstudios.pintandpillage.entity.buildings.Building;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingDAO buildingDAO;

    public Building getBuilding(long id){
        return this.buildingDAO.getBuilding(id);
    }
}
