package pl.edu.pg.multithread.sleeper;

import pl.edu.pg.multithread.task.Task;
import pl.edu.pg.multithread.task.TaskOutput;

public class SleeperTask implements Task {
    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void execute() throws InterruptedException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new InterruptedException();
        }
    }

    @Override
    public TaskOutput getResult() {
        return new SleeperTaskOutput(10);
    }
}
