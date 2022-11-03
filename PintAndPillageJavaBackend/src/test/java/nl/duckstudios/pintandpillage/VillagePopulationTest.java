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
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 5)));

        int numberOfPopulationBeforeHouseBeingBuilt = this.villageUnderTesting.getPopulation();


        // Assert
        System.out.println("Nummer voor: " + numberOfPopulationBeforeHouseBeingBuilt + ", en erna: " + numberOfPopulaitionAfterANewHouseHasBeenBuilt);
        assertTrue(numberOfPopulaitionAfterANewHouseHasBeenBuilt > numberOfPopulationBeforeHouseBeingBuilt);
    }

    @Test
    public void Should_NotIncreasePopulation_When_HouseHasBeenBuiltOnInvalidBuildingPosition(){}

//    @Test
//    public void Should_NotIncreasePopulation_When_UpdateMethodHasNotBeenCalled(){
//        // Arrange
//        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 5)));
//        int numberOfPopulationBeforeHouseBeingBuilt = this.villageUnderTesting.getPopulation();
//
//        // Act
//        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 2)));
//        int numberOfPopulaitionAfterANewHouseHasBeenBuilt = this.villageUnderTesting.getPopulation();
//
//        // Assert
//        System.out.println("Nummer voor: " + numberOfPopulationBeforeHouseBeingBuilt + ", en erna: " + numberOfPopulaitionAfterANewHouseHasBeenBuilt);
//        assertEquals(numberOfPopulaitionAfterANewHouseHasBeenBuilt, numberOfPopulationBeforeHouseBeingBuilt);
//    }

    // Oftewel, de back-end heeft helemaal geen check of er uberhaupt wel een build gebouwd mag worden daar
    @Test
    public void should_amountOfAvailableTilesDecrease_when_newHouseIsBuilt(){
        // Arrange
        int numberOfAvailableBuildingTiles = numberOfAvailableBuildingTiles();

        // Act
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(11, 2)));

        int numberOfAvailableBuildingTilesAfterHouseCreation = numberOfAvailableBuildingTiles();

        // Assert
        System.out.println("Nummer voor: " + numberOfAvailableBuildingTiles + ", en erna: " + numberOfAvailableBuildingTilesAfterHouseCreation);
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
        System.out.println("Nummer voor: " + initialPopulation + ", en erna: " + populationAfterBuildingVikingHouse);
        System.out.println("Nummer voor: " + numberOfAvailableBuildingTiles + ", en erna: " + numberOfAvailableBuildingTilesAfterVillageHouseCreation);
        assertTrue(numberOfAvailableBuildingTiles > numberOfAvailableBuildingTilesAfterVillageHouseCreation);
        assertEquals(initialPopulation, populationAfterBuildingVikingHouse);
    }

//    @Test
//    void should_IncreaseProductionResourceProduction_WhenBuild() {
//        this.setupLumberYardUnderTesting();
//        // Arrange
//        int expectedResourcesPerHour = 532; // 500 extra because that is the starting value
//
//        // Act
//
//        // reset to an hour back, so we can collect an hour of resources
//        LocalDateTime hourSystemTimeBack = LocalDateTime.now().minusHours(1);
//        this.mockedResourceBuildingUnderTesting.setLastCollected(hourSystemTimeBack);
//        this.mockedResourceBuildingUnderTesting.collectResources();
//
//        // Assert
//        Map<String, Integer> currentResources =  this.villageMock.getVillageResources();
//        int actualResources = currentResources.get(MockedResourceBuilding.resourceName);
//
//        assertThat(actualResources, is(expectedResourcesPerHour));
//    }
//
//    @Test
//    void should_IncreaseProductionResourceProduction_WhenWaitingMultipleHours() {
//        // Arrange
//        this.setupLumberYardUnderTesting();
//        int expectedResourcesPerHour = 596; // 500 extra because that is the starting value
//
//        // Act
//
//        // collect 3 hours of resources
//        LocalDateTime hourSystemTimeBack = LocalDateTime.now().minusHours(3);
//        this.mockedResourceBuildingUnderTesting.setLastCollected(hourSystemTimeBack);
//        this.mockedResourceBuildingUnderTesting.collectResources();
//
//        // Assert
//        Map<String, Integer> currentResources =  this.villageMock.getVillageResources();
//        int actualResources = currentResources.get(MockedResourceBuilding.resourceName);
//
//        assertThat(actualResources, is(expectedResourcesPerHour));
//    }
//
//    @Test
//    void should_IncreaseProductionWoodProduction_WhenWaitingMultipleHoursWithHigherLevel() {
//        // Arrange
//        this.setupLumberYardUnderTesting();
//
//        int buildingLevel = 10;
//        this.mockedResourceBuildingUnderTesting.setLevel(buildingLevel); // upgrade to level 10
//        this.mockedResourceBuildingUnderTesting.updateBuilding();
//        this.mockedResourceBuildingUnderTesting.setUnderConstruction(false);
//        int expectedResourcesPerHour = 1130; // 500 extra because that is the starting value
//
//        // Act
//
//        // collect 3 hours of resources
//        LocalDateTime hourSystemTimeBack = LocalDateTime.now().minusHours(3);
//        this.mockedResourceBuildingUnderTesting.setLastCollected(hourSystemTimeBack);
//        this.mockedResourceBuildingUnderTesting.collectResources();
//
//        // Assert
//        Map<String, Integer> currentResources =  this.villageMock.getVillageResources();
//        int actualResources = currentResources.get(MockedResourceBuilding.resourceName);
//
//        assertThat(actualResources, is(expectedResourcesPerHour));
//    }
//
//    @Test
//    void should_IncreaseProductionWoodProduction_WithMultipleLumberyards() {
//        // Arrange
//        this.setupLumberYardUnderTesting();
//        int expectedResourcesPerHour = 788; // 500 extra because that is the starting value
//
//        // setup 2 more lumberyards to increase production
//        ResourceBuilding secondMockedResourceBuilding = this.setupMockedResourceBuilding(new Coord(2, 2));
//        ResourceBuilding thirdMockedResourceBuilding = this.setupMockedResourceBuilding(new Coord(9, 4));
//
//        // Act
//
//        // collect 3 hours of resources
//        LocalDateTime hourSystemTimeBack = LocalDateTime.now().minusHours(3);
//        this.mockedResourceBuildingUnderTesting.setLastCollected(hourSystemTimeBack);
//        this.mockedResourceBuildingUnderTesting.collectResources();
//
//        secondMockedResourceBuilding.setLastCollected(hourSystemTimeBack);
//        secondMockedResourceBuilding.collectResources();
//
//        thirdMockedResourceBuilding.setLastCollected(hourSystemTimeBack);
//        thirdMockedResourceBuilding.collectResources();
//
//        // Assert
//        Map<String, Integer> currentResources = this.villageMock.getVillageResources();
//        int actualResources = currentResources.get(MockedResourceBuilding.resourceName);
//
//        assertThat(actualResources, is(expectedResourcesPerHour));
//    }
//
//    @Test
//    void should_NotIncreaseWoodProduction_WhenNotBuild() {
//        this.villageMock = new Village();
//        int expectedResourcesPerHour = 500; // default wood
//
//        // Act
//
//        // collect 3 hours of resources, even though  we don't have any resources
//        this.villageMock.updateVillageState();
//
//        // Assert
//        Map<String, Integer> currentResources = this.villageMock.getVillageResources();
//        int actualResources = currentResources.get(MockedResourceBuilding.resourceName);
//
//        assertThat(actualResources, is(expectedResourcesPerHour));
//    }
}