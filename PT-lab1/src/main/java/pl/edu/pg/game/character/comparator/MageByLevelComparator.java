package pl.edu.pg.game.character.comparator;

import pl.edu.pg.game.character.entity.Mage;

import java.util.Comparator;

public class MageByLevelComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage m1, Mage m2) {
        int ret = m1.getLevel() - m2.getLevel();
        if (ret == 0) {
            ret = m1.getName().compareTo(m2.getName());
            if (ret == 0) {
                double diff = m1.getPower() - m2.getPower();
                if (diff > 0) {
                    ret = 1;
                } else if (diff < 0) {
                    ret = -1;
                }
            }
        }
        return ret;
    }
}
