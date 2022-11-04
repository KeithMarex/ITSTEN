import nl.duckstudios.pintandpillage.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({VillagePopulationTest.class,
        ResourceBuildingLevelTest.class,
        BuildingSpotTest.class,
        BuildingLevelTest.class,
        BuildingRequirementTest.class})
public class AllTestsSuite {
}
