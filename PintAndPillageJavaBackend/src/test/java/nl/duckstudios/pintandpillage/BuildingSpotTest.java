package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Harbor;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import nl.duckstudios.pintandpillage.entity.buildings.Wall;
import nl.duckstudios.pintandpillage.exceptions.BuildingConditionsNotMetException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Tag("BuildingSpot")
public class BuildingSpotTest {

    @Mock
    Village villageUnderTesting;

    @Before
    public void setup(){
        this.villageUnderTesting = new Village();
    }

    @Test
    public void should_buildHarbour_when_waterTileIsNear(){
        // Arrange
        Harbor harbor = new Harbor();
        harbor.setVillage(this.villageUnderTesting);
        harbor.setLevel(1);
        harbor.setPosition(new Coord(333, 7));
        harbor.updateBuilding();
        harbor.setUnderConstruction(false);

        Harbor harbor2 = new Harbor();
        harbor2.setVillage(this.villageUnderTesting);
        harbor2.setLevel(1);
        harbor2.setPosition(new Coord(9, 13));
        harbor2.updateBuilding();
        harbor2.setUnderConstruction(false);
        harbor2.setBuildingLevelRequiredToLevelup(Collections.emptyMap());

        // Act

        // Assert
        assertThrows(BuildingConditionsNotMetException.class, () -> this.villageUnderTesting.createBuilding(harbor));
        assertDoesNotThrow(() -> this.villageUnderTesting.createBuilding(harbor2));
    }

    @Test
    public void should_buildWall_when_validBuildingPositionHasBeenChosen(){
        // Arrange
        Wall wall = new Wall();
        wall.setVillage(this.villageUnderTesting);
        wall.setLevel(1);
        wall.setPosition(new Coord(0, 0));
        wall.updateBuilding();
        wall.setUnderConstruction(false);

        Wall wall2 = new Wall();
        wall2.setVillage(this.villageUnderTesting);
        wall2.setLevel(1);
        wall2.setPosition(new Coord(3, 4));
        wall2.updateBuilding();
        wall2.setUnderConstruction(false);

        // Act

        // Assert
        assertDoesNotThrow(() -> this.villageUnderTesting.createBuilding(wall));
        assertThrows(BuildingConditionsNotMetException.class, () -> this.villageUnderTesting.createBuilding(wall2));
    }

}
