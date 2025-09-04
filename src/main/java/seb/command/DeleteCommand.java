package seb.command;
import seb.*;
/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the delete command by removing the task at the specified index from the task list
     * and saving the updated list to storage.
     * @param tasks The current list of tasks.
     * @param storage The storage system to save the updated task list.
     * @return A confirmation message indicating the task has been removed and the new task count.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task t = tasks.getTasks(index);
        tasks.deleteTasks(index);
        storage.saveTasks(tasks);
        
        return "     Noted. I've removed this task:\n" +
               "       " + t.toString() + "\n" +
               "     Now you have " + tasks.size() + " tasks in the list.";
    }
}

