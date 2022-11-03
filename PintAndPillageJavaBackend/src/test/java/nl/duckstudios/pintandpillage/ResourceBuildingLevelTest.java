package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import nl.duckstudios.pintandpillage.mocks.MockedResourceBuilding;
import nl.duckstudios.pintandpillage.model.ResourceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@Tag("ResourceBuildingLevel")
public class ResourceBuildingLevelTest {

    @Mock
    private Village mockedVillage;

    private MockedResourceBuilding mockedResourceBuilding;

    @Mock
    private House mockedHouse;

    @Before
    public void setup(){
        this.mockedVillage = new Village();

        this.mockedResourceBuilding = new MockedResourceBuilding("Mocked resource building", ResourceType.Stone, "MockedResourceBuilding");
        this.mockedResourceBuilding.setVillage(this.mockedVillage);
        this.mockedResourceBuilding.setLevel(1);
        this.mockedResourceBuilding.setPosition(new Coord(1, 4));
        this.mockedResourceBuilding.updateBuilding();
        this.mockedResourceBuilding.setUnderConstruction(false);
        this.mockedResourceBuilding.setLastCollected(LocalDateTime.now());

        this.mockedHouse = new House();
        this.mockedHouse.setVillage(mockedVillage);
        this.mockedHouse.setLevel(1);
        this.mockedHouse.setPosition(new Coord(2, 2));
        this.mockedHouse.updateBuilding();
        this.mockedHouse.setUnderConstruction(false);

        this.mockedVillage.createBuilding(this.mockedHouse);
        this.mockedVillage.createBuilding(this.mockedResourceBuilding);

    }

    @Test
    public void should_produceMoreResources_when_levelledUp(){
        // Arrange
        int amountOfResourcesProducedPerHour = this.mockedResourceBuilding.getResourcesPerHour();

        // Act
        this.mockedResourceBuilding.levelUp();

        // To mock the completion of a level up
        this.mockedResourceBuilding.setLevelupFinishedTime(LocalDateTime.now().minusSeconds(5));

        this.mockedResourceBuilding.updateBuildingState();
        int amountOfResourcesProducedPerHourAfterLevelUp = this.mockedResourceBuilding.getResourcesPerHour();

        // Assert
        System.out.println("Nummer voor: " + amountOfResourcesProducedPerHour + ", en erna: " + amountOfResourcesProducedPerHourAfterLevelUp);
        assertTrue(amountOfResourcesProducedPerHour < amountOfResourcesProducedPerHourAfterLevelUp);
    }

}
