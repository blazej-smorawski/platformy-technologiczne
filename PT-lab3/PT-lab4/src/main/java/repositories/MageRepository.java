package repositories;

import entities.Mage;

import javax.persistence.EntityManagerFactory;

public class MageRepository extends DataBaseRepo<Mage, String> {

    public MageRepository(EntityManagerFactory emf) {
        super(emf, Mage.class);
    }
}

