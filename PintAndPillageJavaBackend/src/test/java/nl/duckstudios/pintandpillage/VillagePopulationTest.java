package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import nl.duckstudios.pintandpillage.mocks.MockedVikingHouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test: Bouw huizen om de populatie binnen mijn dorp te vergroten.
@ExtendWith(MockitoExtension.class)
@Tag("VillagePopulation")
public class VillagePopulationTest {

    @Mock
    Village villageUnderTesting;

    @Mock
    House mockedHouse;

    @Before
    public void setup(){
        this.villageUnderTesting = new Village();
    }

    private House createNewHouse(Coord coord){
        House house = new House();
        house.setVillage(villageUnderTesting);
        house.setLevel(1);
        house.setPosition(coord);
        house.updateBuilding();
        house.setUnderConstruction(false);

        return house;
    }

    private MockedVikingHouse createNewVikingHouse(Coord coord){
        MockedVikingHouse mockedVikingHouse = new MockedVikingHouse();
        mockedVikingHouse.setVillage(villageUnderTesting);
        mockedVikingHouse.setLevel(1);
        mockedVikingHouse.setPosition(coord);
        mockedVikingHouse.updateBuilding();
        mockedVikingHouse.setUnderConstruction(false);

        return mockedVikingHouse;
    }

    private int numberOfAvailableBuildingTiles() {
        return Arrays.stream(this.villageUnderTesting.getValidBuildPositions()).toList().stream()
                .filter(loc -> this.villageUnderTesting.getBuildings().stream().noneMatch(building -> building.getPosition().getX() == loc.position.getX() && building.getPosition().getY() == loc.position.getY())).toList().size();
    }


    @Test
    public void Should_IncreasePopulation_When_HouseHasBeenBuilt(){
        // Act
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 2)));
        int numberOfPopulationBeforeHouseBeingBuilt = this.villageUnderTesting.getPopulation();

        // Arrange
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 4)));

        int numberOfPopulationAfterHouseBeingBuilt = this.villageUnderTesting.getPopulation();

        // Assert
        assertTrue(numberOfPopulationAfterHouseBeingBuilt > numberOfPopulationBeforeHouseBeingBuilt);
    }

    @Test
    public void should_amountOfAvailableTilesDecrease_when_newHouseIsBuilt(){
        // Arrange
        int numberOfAvailableBuildingTiles = numberOfAvailableBuildingTiles();

        // Act
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(11, 2)));

        int numberOfAvailableBuildingTilesAfterHouseCreation = numberOfAvailableBuildingTiles();

        // Assert
        assertTrue(numberOfAvailableBuildingTilesAfterHouseCreation < numberOfAvailableBuildingTiles);
    }

    @Test
    public void should_notReducePopulationCosts_when_buildingVikingsHouse(){
        // Arrange
        int initialPopulation = this.villageUnderTesting.getPopulationInUse();
        int numberOfAvailableBuildingTiles = numberOfAvailableBuildingTiles();

        // Act
        MockedVikingHouse vikingHouse = createNewVikingHouse(new Coord(1,4));
        this.villageUnderTesting.createBuilding(vikingHouse);

        int populationAfterBuildingVikingHouse = this.villageUnderTesting.getPopulationInUse();
        int numberOfAvailableBuildingTilesAfterVillageHouseCreation = numberOfAvailableBuildingTiles();

        // Assert
        assertTrue(numberOfAvailableBuildingTiles > numberOfAvailableBuildingTilesAfterVillageHouseCreation);
        assertEquals(initialPopulation, populationAfterBuildingVikingHouse);
    }
}