package pl.edu.pg.multithread.divider;

import pl.edu.pg.multithread.task.Task;
import pl.edu.pg.multithread.task.TaskOutput;

import java.util.ArrayList;
import java.util.List;

public class DividerTask implements Task {
    private long number;
    private List<Long> dividers;
    private DividerTaskOutput out;

    public DividerTask(long number) {
        this.number = number;
        this.dividers = new ArrayList<>();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void execute() throws InterruptedException {
        for(long i = 1; i<number; i++) {
            if(number%i == 0) {
                dividers.add(new Long(i));
            }
            if(Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
        StringBuilder output = new StringBuilder(number + ": ");
        for(long divider:dividers) {
            output.append(divider).append(", ");
        }
        out = new DividerTaskOutput(output.toString());
    }

    @Override
    public TaskOutput getResult() {
        return out;
    }
}
