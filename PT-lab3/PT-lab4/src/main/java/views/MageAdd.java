package views;

import entities.Mage;
import entities.Tower;
import services.MageService;

import java.util.Scanner;

/**
 * View for adding new Mages. Stats fields are omitted because of being lazy.
 */
public class MageAdd implements View {

    /**
     * Mages managing service.
     */
    private final services.MageService MageService;

    /**
     * @param MageService  Mages managing service
     */
    public MageAdd(MageService MageService) {
        this.MageService = MageService;
    }

    @Override
    public void display() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.print("background: ");
        int level = scanner.nextInt();

        MageService.create(Mage.builder()
            .name(name)
            .level(level)
            .tower(new Tower())
            .build());
    }
}
