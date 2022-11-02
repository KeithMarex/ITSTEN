package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Test: Bouw huizen om de populatie binnen mijn dorp te vergroten.
@ExtendWith(MockitoExtension.class)
@Tag("VillagePopulation")
public class VillagePopulationTest {
    Village villageUnderTesting;

    @Mock
    House mockedHouse;

    @Before
    public void setup(){
        this.villageUnderTesting = new Village();

        this.mockedHouse = new House();
        this.mockedHouse.setVillage(villageUnderTesting);
        this.mockedHouse.setLevel(1);
        this.mockedHouse.setPosition(new Coord(2, 2));
        this.mockedHouse.updateBuilding();
        this.mockedHouse.setUnderConstruction(false);

        this.villageUnderTesting.createBuilding(this.mockedHouse);
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

    @Test
    public void Should_IncreasePopulation_When_HouseHasBeenBuilt(){
        // Act
        int numberOfPopulationBeforeHouseBeingBuilt = this.villageUnderTesting.getPopulation();

        // Arrange
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(2, 5)));

        int numberOfPopulaitionAfterANewHouseHasBeenBuilt = this.villageUnderTesting.getPopulation();

        // Assert
        System.out.println("Nummer voor: " + numberOfPopulationBeforeHouseBeingBuilt + ", en erna: " + numberOfPopulaitionAfterANewHouseHasBeenBuilt);
        assertTrue(numberOfPopulaitionAfterANewHouseHasBeenBuilt > numberOfPopulationBeforeHouseBeingBuilt);
    }

    @Test
    public void Should_NotIncreasePopulation_When_HouseHasBeenBuiltOnInvalidBuildingPosition(){}

    @Test
    public void Should_NotIncreasePopulation_When_UpdateMethodHasNotBeenCalled(){
        // Act
        int numberOfPopulationBeforeHouseBeingBuilt = this.villageUnderTesting.getPopulation();

        // Arrange
        createNewHouse(new Coord(2, 5));

        int numberOfPopulaitionAfterANewHouseHasBeenBuilt = this.villageUnderTesting.getPopulation();

        // Assert
        System.out.println("Nummer voor: " + numberOfPopulationBeforeHouseBeingBuilt + ", en erna: " + numberOfPopulaitionAfterANewHouseHasBeenBuilt);
        assertEquals(numberOfPopulaitionAfterANewHouseHasBeenBuilt, numberOfPopulationBeforeHouseBeingBuilt);
    }

    // Oftewel, de back-end heeft helemaal geen check of er uberhaupt wel een build gebouwd mag worden daar
    @Test
    public void Should_AmountOfAvailablesTilesDecrease_When_NewHouseIsBuilt(){
        // Act
        int numberOfAvailableBuildingTiles = Arrays.stream(this.villageUnderTesting.getValidBuildPositions()).toList().stream()
                .filter(loc -> this.villageUnderTesting.getBuildings().stream().noneMatch(building -> building.getPosition().getX() == loc.position.getX() && building.getPosition().getY() == loc.position.getY())).toList().size();

        // Arrange
        this.villageUnderTesting.createBuilding(createNewHouse(new Coord(11, 2)));

        int numberOfAvailableBuildingTilesAfterHouseCreation = Arrays.stream(this.villageUnderTesting.getValidBuildPositions()).toList().stream()
                .filter(loc -> this.villageUnderTesting.getBuildings().stream().noneMatch(building -> building.getPosition().getX() == loc.position.getX() && building.getPosition().getY() == loc.position.getY())).toList().size();

        // Assert
        System.out.println("Nummer voor: " + numberOfAvailableBuildingTiles + ", en erna: " + numberOfAvailableBuildingTilesAfterHouseCreation);
        assertTrue(numberOfAvailableBuildingTilesAfterHouseCreation < numberOfAvailableBuildingTiles);
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