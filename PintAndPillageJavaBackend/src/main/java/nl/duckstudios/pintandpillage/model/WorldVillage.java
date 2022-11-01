package nl.duckstudios.pintandpillage.model;

import lombok.AllArgsConstructor;
import nl.duckstudios.pintandpillage.entity.Coord;

@AllArgsConstructor
public class WorldVillage {
    public long villageId;

    public String villageOwnerName;

    public Coord position;

    public String name;

    public int points;

    public long userId;

}
