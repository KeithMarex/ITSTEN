package nl.duckstudios.pintandpillage.mocks;

import nl.duckstudios.pintandpillage.entity.buildings.Building;

public class MockedVikingHouse extends Building {

    public MockedVikingHouse(){

    }

    @Override
    public int getPopulationRequired(int adjustment) {
        return 0;
    }

    @Override
    public void updateBuilding() {

    }
}
