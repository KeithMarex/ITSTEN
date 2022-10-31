package nl.duckstudios.pintandpillage.dao;

import nl.duckstudios.pintandpillage.entity.Village;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class VillageDAO {

    private VillageRepository villageRepository;

    public VillageDAO(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public Village save(Village village) {
        return this.villageRepository.save(village);
    }

    public Village getVillage(long id) throws EntityNotFoundException {
        return this.villageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Village> getVillages(long id) {
        return this.villageRepository.findByUserId(id);
    }

    public List<Village> getVillages() {
        return this.villageRepository.findAll();
    }

}
