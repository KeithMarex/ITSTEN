package nl.duckstudios.pintandpillage.TDD;

import nl.duckstudios.pintandpillage.entity.buildings.Building;
import nl.duckstudios.pintandpillage.entity.buildings.ResourceVault;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

public class ResourceVaultTest {

    ResourceVault resourceVault;

    @Before
    public void setUp(){
        this.resourceVault = new ResourceVault();
    }

    @Test
    public void should_beOfTypeBuilding_when_resourceVaultHasBeenCreated(){
        // Arrange

        // Act

        // Assert
        assertThat(resourceVault, instanceOf(Building.class));
    }

    @Test
    public void should_containNameVault_when_resourceVaultHasBeenCreated(){
        // Arrange

        // Act
        String actualName = resourceVault.getName();
        String expectedName = "Vault";

        // Assert
        assertThat(expectedName, is(actualName));
    }

    @Test
    public void should_cost25WoodToBuild_when_resourceVaultOfLevel0WillBeBuild(){
        // Arrange
        int expectedAmountOfWoodCosts = 25;

        // Act
        int actualAmountOfWoodCosts = resourceVault.getResourcesRequiredLevelUp().get("Wood");

        // Assert
        assertThat(expectedAmountOfWoodCosts, is(actualAmountOfWoodCosts));
    }



}
