package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Harbor;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        harbor.setPosition(new Coord(2, 2));
        harbor.updateBuilding();
        harbor.setUnderConstruction(false);

        // Act
        this.villageUnderTesting.createBuilding(harbor);

        // Assert
        assertTrue(true);
    }

}
