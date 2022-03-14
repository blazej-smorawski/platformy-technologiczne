package pl.edu.pg.multithread.divider;

import pl.edu.pg.multithread.task.TaskOutput;

public class DividerTaskOutput implements TaskOutput {
    public String output;
    public DividerTaskOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return output;
    }
}
