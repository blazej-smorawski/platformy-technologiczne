package pl.edu.pg.multithread.service;

import pl.edu.pg.multithread.task.Task;
import pl.edu.pg.multithread.task.TaskOutput;
import pl.edu.pg.multithread.worker.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Spawn workers, add tasks, receive results etc.
 */
public class SessionService {
    static private List<Task> taskList;
    static private List<TaskOutput> finishedTasksList;
    static private List<Thread> threads;

    public SessionService(int workerCount) {
        taskList = new ArrayList<>();
        finishedTasksList = new ArrayList<>();
        threads = new ArrayList<>(workerCount);

        for (int i = 0; i < workerCount; i++) {
            Thread thread = new Thread(new Worker(this));
            threads.add(thread);
            thread.start();
            System.out.println("Mainthread added worker nr: " + (i + 1));
        }
    }

    public synchronized void addTask(Task task) {
        {
            taskList.add(task);
            notify();
        }
        System.out.println("Mainthread task added");
    }

    public synchronized Task getTask() throws InterruptedException {
        try {
            while (taskList.isEmpty()) {
                wait();
            }
            return taskList.remove(0);
        } catch (InterruptedException ex) {
            System.out.println("getTask interrupted");
            throw new InterruptedException();
        }
    }

    public synchronized void addFinishedTask(TaskOutput output) {
        finishedTasksList.add(output);
    }

    public List<TaskOutput> getFinishedTasksList() {
        List<TaskOutput> ret = finishedTasksList;
        finishedTasksList = new ArrayList<>();
        return ret;
    }

    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
            System.out.println("Mainthread sent interruption signal");
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.out.println("wat");
            }
        }
    }
}
