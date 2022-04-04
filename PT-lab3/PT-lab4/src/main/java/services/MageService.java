package services;

import entities.Mage;
import repositories.MageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MageService {

    private final MageRepository repository;

    public MageService(MageRepository repository) {
        this.repository = repository;
    }

    public List<Mage> findAllMages() {
        return repository.findAll();
    }

    public void delete(Mage Mage) {
        repository.delete(Mage);
    }

    public void create(Mage Mage) {
        repository.add(Mage);
    }
}
