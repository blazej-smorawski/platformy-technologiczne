package pl.edu.pg.multithread;


import pl.edu.pg.multithread.service.SessionService;
import pl.edu.pg.multithread.sleeper.SleeperTask;
import pl.edu.pg.multithread.sleeper.SleeperTaskOutput;
import pl.edu.pg.multithread.task.TaskOutput;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int thread_count = Integer.parseInt(args[0]);
        SessionService service = new SessionService(thread_count);

        service.addTask(new SleeperTask());
        service.addTask(new SleeperTask());

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.next();

            switch (input) {
                case "a":
                    service.addTask(new SleeperTask());
                    break;
                case "p":
                    for (TaskOutput out : service.getFinishedTasksList()) {
                        System.out.println("Done: howWellSlept = " + ((SleeperTaskOutput) out).howWellSlept);
                    }
                    break;
                default:
                    break;
            }
        } while (!input.equals("q"));

        service.close();
    }
}