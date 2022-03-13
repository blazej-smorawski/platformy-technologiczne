package pl.edu.pg.game.character.initializers;

import pl.edu.pg.game.character.entity.Mage;
import pl.edu.pg.game.services.MageService;

public class TestInitializer {
    private final MageService mageService;

    public TestInitializer(MageService mageService) {
        this.mageService = mageService;
    }

    /**
     * Dlaczego tracimy czas na tworzenie obiektu?
     */
    public void init() {
        Mage archMage = new Mage("Andrzej", 1, 5);
        Mage mage2 = new Mage("Bandrzej", 2, 4);
        Mage mage3 = new Mage("Cecylia", 3, 3);
        Mage mage4 = new Mage("Darek", 14, 2);
        Mage mage5 = new Mage("Arek", 115, 20);

        mageService.create(archMage);
        mageService.create(mage2);
        mageService.create(mage3);
        mageService.create(mage4);
        mageService.create(mage5);

        mageService.assignApprenticeTo(mage2, archMage);
        mageService.assignApprenticeTo(mage3, archMage);
        mageService.assignApprenticeTo(mage4, mage3);
    }
}
