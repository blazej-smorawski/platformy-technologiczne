package pl.edu.pg.game.character.comparator;

import pl.edu.pg.game.character.entity.Mage;

import java.util.Comparator;

public class MageByHealthComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage m1, Mage m2) {
        return m1.getHealth() - m2.getHealth();
    }
}
