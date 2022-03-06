package pl.edu.pg.game.character.repository;

import pl.edu.pg.game.character.entity.Mage;
import pl.edu.pg.game.repository.MemRepo;

import java.util.Comparator;

public class MageRepo extends MemRepo<Mage> {
    public MageRepo() {
    }

    public MageRepo(boolean sorted) {
        super(sorted);
    }

    public MageRepo(Comparator<Mage> comparator) {
        super(comparator);
    }

    private void printMagesDepth(int depth) {
        for(Mage entity : set) {
            for(int i=0;i<depth;i++) {
                System.out.print("-");
            }
            System.out.println(entity);
            entity.getApprentices().printMagesDepth(depth+1);
        }
    }

    public void printMages() {
        printMagesDepth(1);
    }
}
