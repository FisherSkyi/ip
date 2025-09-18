package seb.command;

import seb.PriorityType;
import seb.Storage;
import seb.TaskList;

public class PriorityCommand implements Command {
    private int index;
    private PriorityType priority;
    public PriorityCommand(int index, PriorityType priority) {
        this.index = index;
        this.priority = priority;
    }
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.getTasks(this.index).setPriority(priority);
        return "     Priority of task " + (this.index + 1) + " set to " + priority + ".";
    }
}
