package nl.duckstudios.pintandpillage.mocks;

import lombok.Getter;
import lombok.Setter;
import nl.duckstudios.pintandpillage.entity.buildings.ResourceBuilding;
import nl.duckstudios.pintandpillage.model.ResourceType;

import java.util.HashMap;

@Getter
@Setter
public class MockedResourceBuilding extends ResourceBuilding {

    private String name;

    public MockedResourceBuilding(String description, ResourceType resourceType, String name){
        super.setDescription(description);
        super.setGeneratesResource(resourceType);
        this.name = name;

        this.updateBuilding();
    }

    private int updateResourcesPerHour() {
        return (int)(20 + 12 * Math.pow(super.getLevel(), 1.2));
    }

    @Override
    public int getPopulationRequired(int adjustment) {
        return (3 + (int) Math.pow(10, adjustment * 0.2) + 1) + (adjustment * 2);
    }

    @Override
    public void updateBuilding() {
        super.setResourcesPerHour(this.updateResourcesPerHour());
        super.setResourcesRequiredLevelUp(new HashMap<>() {
            {
                int level = MockedResourceBuilding.super.getLevel();
                put(MockedResourceBuilding.super.getGeneratesResource().name(), level * 25 + 25);
            }
        });
    }
}
