package nl.duckstudios.pintandpillage;

import nl.duckstudios.pintandpillage.entity.Village;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class BuildingUnitTest {

    @Mock
    private Village village;

    public BuildingUnitTest(){
        this.village = new VillageTestConfiguration().villageObject();
    }

}
