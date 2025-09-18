package seb.command;

import seb.PriorityType;
import seb.Storage;
import seb.TaskList;

/**
 * Represents a command to set the priority of a task.
 */
public class SetPriorityCommand implements Command {
    private final int index;
    private final PriorityType priority;
    /**
     * Creates a SetPriorityCommand.
     * @param index The index of the task (1-based from user input).
     * @param priority The new priority value.
     */
    public SetPriorityCommand(int index, PriorityType priority) {
        this.index = index - 1;
        this.priority = priority;
    }
    /**
     * Executes the command to set the priority of a task.
     * @param tasks The TaskList to update.
     * @param storage The Storage to save changes.
     * @return A string confirming the priority change.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (index < 0 || index >= tasks.size()) {
            return "     Invalid task index.";
        }
        tasks.setPriority(index, priority);
        storage.saveTasks(tasks);
        return String.format("     Set priority of task %d to %d.", index + 1, priority);
    }
}

