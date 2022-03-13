package pl.edu.pg.multithread.task;

/**
 * Simple interface representing a task to be done by thread
 */
public interface Task {
    boolean isDone();

    void execute() throws InterruptedException;

    TaskOutput getResult();
}
