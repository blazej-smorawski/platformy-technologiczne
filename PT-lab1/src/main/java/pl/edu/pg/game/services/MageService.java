package pl.edu.pg.game.services;

import pl.edu.pg.game.character.entity.Mage;
import pl.edu.pg.game.character.repository.MageApprenticesMap;
import pl.edu.pg.game.character.repository.MageRepo;

import java.util.List;

public class MageService {
    private final MageRepo repository;

    public MageService(MageRepo repository) {
        this.repository = repository;
    }

    public List<Mage> findAllMages() {
        return repository.findAll();
    }

    public void delete(Mage mage) {
        repository.delete(mage);
    }

    public void create(Mage mage) {
        repository.add(mage);
    }

    public void assignApprenticeTo(Mage apprentice, Mage dungeonMaster) {
        dungeonMaster.getApprentices().add(apprentice);
    }

    public void printAllMages() {
        repository.printMages();
    }

    public MageApprenticesMap getAprenticeshipMap() {
        MageApprenticesMap map = new MageApprenticesMap();

        for (Mage mage : repository.findAll()) {
            map.add(mage, mage.countApprentices());
        }
        return map;
    }
}
