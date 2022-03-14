package pl.edu.pg.multithread.worker;

import pl.edu.pg.multithread.service.SessionService;
import pl.edu.pg.multithread.sleeper.SleeperTaskOutput;
import pl.edu.pg.multithread.task.Task;
import pl.edu.pg.multithread.task.TaskOutput;

import java.util.List;

/**
 * Execute a task
 */
public class Worker implements Runnable {
    SessionService session_service;

    private boolean running = true;

    public Worker(SessionService session_service) {
        this.session_service = session_service;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && running) {
            try {
                Task task = session_service.getTask();
                System.out.println("Thread executing");
                task.execute();
                session_service.addFinishedTask(task.getResult());
                session_service.writeOutputToFile(task.getResult());
                System.out.println("Thread finished");
            } catch (InterruptedException ex) {
                running = false;
                System.out.println("Thread interrupted");
            }
        }
    }

    public void stop() {
        running = false;
    }
}
