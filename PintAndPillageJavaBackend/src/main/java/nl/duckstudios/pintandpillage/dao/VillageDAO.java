package nl.duckstudios.pintandpillage.dao;

import nl.duckstudios.pintandpillage.entity.Village;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class VillageDAO {

    private VillageRepository villageRepository;

    public VillageDAO(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public Village save(Village village) {
        return this.villageRepository.save(village);
    }

    public Optional<Village> getVillage(long id) {
        return this.villageRepository.findById(id);
    }

    public List<Village> getVillages(long id) {
        return this.villageRepository.findByUserId(id);
    }

    public List<Village> getVillages() {
        return this.villageRepository.findAll();
    }

}
