package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Coord;
import nl.duckstudios.pintandpillage.entity.Village;
import nl.duckstudios.pintandpillage.entity.buildings.Barracks;
import nl.duckstudios.pintandpillage.entity.buildings.Harbor;
import nl.duckstudios.pintandpillage.exceptions.BuildingConditionsNotMetException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@Tag("BuildingRequirement")
public class BuildingRequirementTest {

    @Mock
    private Village mockedVillage;

    @Before
    public void setup(){
        this.mockedVillage = new Village();
    }

    @Test
    public void should_buildBarrack_when_headquartersBuildingIsLevel5(){
        // Arrange
        Barracks barrack = new Barracks();
        barrack.setVillage(this.mockedVillage);
        barrack.setLevel(1);
        barrack.setPosition(new Coord(6, 2));
        barrack.updateBuilding();
        barrack.setUnderConstruction(false);

        // Act

        // Assert
        assertThrows(BuildingConditionsNotMetException.class, () -> this.mockedVillage.createBuilding(barrack));
    }

    @Test
    public void should_buildHarbor_when_buildingRequirementsAreMet(){
        // Arrange
        Harbor harbor = new Harbor();
        harbor.setVillage(this.mockedVillage);
        harbor.setLevel(1);
        harbor.setPosition(new Coord(6, 2));
        harbor.updateBuilding();
        harbor.setUnderConstruction(false);

        // Act

        // Assert
        assertThrows(BuildingConditionsNotMetException.class, () -> this.mockedVillage.createBuilding(harbor));
    }


}
