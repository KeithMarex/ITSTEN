package nl.duckstudios.pintandpillage.dao;

import nl.duckstudios.pintandpillage.entity.buildings.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findById(long id);

}
