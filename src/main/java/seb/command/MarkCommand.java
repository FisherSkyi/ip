package seb.command;
import seb.*;
/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand implements Command {
    private final int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    /**
     * Executes the mark command by marking the task at the specified index as done
     * and saving the updated task list to storage.
     * @param tasks
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.getTasks(index).markAsDone();
        storage.saveTasks(tasks);
        return "     Nice! I've marked this task as done:\n" +
               "       " + tasks.getTasks(index).toString();
    }
}

