package pl.edu.pg.multithread;


import pl.edu.pg.multithread.divider.DividerTask;
import pl.edu.pg.multithread.divider.DividerTaskOutput;
import pl.edu.pg.multithread.service.SessionService;
import pl.edu.pg.multithread.task.TaskOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int thread_count = Integer.parseInt(args[0]);
        SessionService service = new SessionService(thread_count);

        File file = new File(args[1]+".txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                long i = Long.parseUnsignedLong(sc.nextLine());
                System.out.println(i);
                service.addTask(new DividerTask(i));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.next();

            switch (input) {
                case "a":
                    service.addTask(new DividerTask(Long.parseUnsignedLong(scanner.next())));
                    break;
                case "p":
                    for (TaskOutput out : service.getFinishedTasksList()) {
                        System.out.println(((DividerTaskOutput) out).output);
                    }
                    break;
                default:
                    break;
            }
        } while (!input.equals("exit"));

        service.close();
    }
}