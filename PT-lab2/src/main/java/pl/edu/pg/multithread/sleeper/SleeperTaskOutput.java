package pl.edu.pg.multithread.sleeper;

import pl.edu.pg.multithread.task.TaskOutput;

public class SleeperTaskOutput implements TaskOutput {
    public int howWellSlept;

    public SleeperTaskOutput(int howWellSlept) {
        this.howWellSlept = howWellSlept;
    }
}
