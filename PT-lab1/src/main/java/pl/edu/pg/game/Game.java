package pl.edu.pg.game;

import pl.edu.pg.game.character.comparator.MageByLevelComparator;
import pl.edu.pg.game.character.initializers.TestInitializer;
import pl.edu.pg.game.character.repository.MageRepo;
import pl.edu.pg.game.services.MageService;

public class Game {
    public static void main(String[] args) {
        boolean sorted = args.length >= 1 && Boolean.parseBoolean(args[0]);
        boolean alternativeCriteria = args.length == 2 && Boolean.parseBoolean(args[1]);
        Game application = new Game();

        MageService service = application.createMageService(sorted, alternativeCriteria);
        TestInitializer testInitializer = new TestInitializer(service);
        testInitializer.init();

        service.printAllMages();
        System.out.println(service.getAprenticeshipMap());
    }

    private MageService createMageService(boolean sorted, boolean alternative) {
        return (sorted && alternative)
                ? new MageService(new MageRepo(new MageByLevelComparator()))
                : new MageService(new MageRepo(sorted));
    }
}