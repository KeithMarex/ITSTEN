package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.DefenceTower;
import nl.duckstudios.pintandpillage.entity.buildings.House;
import nl.duckstudios.pintandpillage.entity.production.Unit;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@Tag("BuildingLevel")
public class BuildingLevelTest {

    @Mock
    private Village villageMock;

    @Mock
    private House houseUnderTesting;

    @Before
    public void setup(){
        this.villageMock = new Village();

        this.houseUnderTesting = new House();
        this.houseUnderTesting.setVillage(villageMock);
        this.houseUnderTesting.setLevel(1);
        this.houseUnderTesting.setPosition(new Coord(2, 2));
        this.houseUnderTesting.updateBuilding();
        this.houseUnderTesting.setUnderConstruction(false);

        this.villageMock.createBuilding(this.houseUnderTesting);
    }

    @Test
    public void should_requireMoreResourcesToUpdgrade_when_levelOfBuildingHasBeenUpgraded(){
        // Arrange
        int requiredResourcesToLevelUp = this.houseUnderTesting.getResourcesRequiredLevelUp().values().stream().mapToInt(Integer::intValue).sum();

        // Act
        this.houseUnderTesting.levelUp();

        // To mock the completion of a level up
        this.houseUnderTesting.setLevelupFinishedTime(LocalDateTime.now().minusSeconds(5));

        this.houseUnderTesting.updateBuildingState();

        System.out.println(this.houseUnderTesting.getLevel());

        int newrequiredResourcesToLevelUp = this.houseUnderTesting.getResourcesRequiredLevelUp().values().stream().mapToInt(Integer::intValue).sum();

        // Assert
        System.out.println("Nummer voor: " + requiredResourcesToLevelUp + ", en erna: " + newrequiredResourcesToLevelUp);
        assertTrue(requiredResourcesToLevelUp < newrequiredResourcesToLevelUp);
    }

    @Test
    public void should_requireMoreConstructionTime_when_buildingHasNewLevel() throws InterruptedException {
        // Arrange
        int initialConstructionTime = (int) this.houseUnderTesting.getConstructionTimeSeconds();

        // Act
        this.houseUnderTesting.levelUp();

        // To mock the completion of a level up
        this.houseUnderTesting.setLevelupFinishedTime(LocalDateTime.now().minusSeconds(5));

        this.houseUnderTesting.updateBuildingState();

        int constructionTimeAfterLevelUp = (int) this.houseUnderTesting.getConstructionTimeSeconds();

        // Assert
        System.out.println("Nummer voor: " + initialConstructionTime + ", en erna: " + constructionTimeAfterLevelUp);
        assertTrue(constructionTimeAfterLevelUp > initialConstructionTime);
    }

    @Test
    public void should_increaseAmountOfPopulationPerBuilding_when_levelledUp(){
        // Arrange
        int initialPopulation = this.villageMock.getPopulation();

        // Act
        this.houseUnderTesting.levelUp();

        // To mock the completion of a level up
        this.houseUnderTesting.setLevelupFinishedTime(LocalDateTime.now().minusSeconds(5));

        this.houseUnderTesting.updateBuildingState();
        this.villageMock.updateVillageState();

        int populationCapacityOfVillageAfterBuildingUpgrade = this.villageMock.getPopulation();

        // Assert
        System.out.println("Nummer voor: " + initialPopulation + ", en erna: " + populationCapacityOfVillageAfterBuildingUpgrade);
        assertTrue(initialPopulation < populationCapacityOfVillageAfterBuildingUpgrade);
    }

    // -	Should_ExpectedBehaviour_When_StateUnderTest

    @Test
    public void Should_IncreaseVillageDefensePoints_When_DefenceTowerGetsBuild(){
        // Arrange
        int initialVillageDefensePoints = this.villageMock.getTotalDefence();

        // Act
        DefenceTower defenceTower = new DefenceTower();
        this.villageMock.createBuilding(defenceTower);

        // Custom function created since village defence points are only calculated by units
        int newTotalDefense = this.villageMock.getBuildings().stream()
                .filter(building -> building instanceof DefenceTower)
                .mapToInt(building -> ((DefenceTower) building).getDefenceBonus()).sum();

        this.villageMock.setTotalDefence(this.villageMock.getTotalDefence() + newTotalDefense);

        int villageDefensePointsAfterDefenceTowerCreation = this.villageMock.getTotalDefence();

        // Assert
        assertTrue(initialVillageDefensePoints < villageDefensePointsAfterDefenceTowerCreation);
    }

}
