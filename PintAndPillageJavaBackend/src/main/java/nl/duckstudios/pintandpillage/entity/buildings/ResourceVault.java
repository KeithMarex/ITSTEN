package nl.duckstudios.pintandpillage.entity.buildings;

import lombok.Getter;
import lombok.Setter;
import nl.duckstudios.pintandpillage.model.ResourceType;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResourceVault extends Building {

    private String name = "Vault";

    public ResourceVault(){
        this.setResourcesRequiredAtGivenLevel(getLevel());
    }

    @Override
    public void updateBuilding() {

    }

    private void setResourcesRequiredAtGivenLevel(Integer level){
        Map<String, Integer> resourcesRequired = new HashMap<>(){{
           put(ResourceType.Wood.name(), level * 10 + 25);
        }};

        this.setResourcesRequiredLevelUp(resourcesRequired);
    }
}
