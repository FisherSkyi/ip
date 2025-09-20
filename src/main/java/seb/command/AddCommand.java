package seb.command;
import seb.Storage;
import seb.Task;
import seb.TaskList;

/**
 * Represents a command to add a task.
 */
public class AddCommand implements Command {
    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes the add command by adding the task to the task list and saving it to storage.
     * @param tasks The current list of tasks.
     * @param storage The storage system to save the updated task list.
     * @return A confirmation message indicating the task has been added and the new task count.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTasks(task);
        storage.saveTasks(tasks);
        return "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + String.format("     Now you have %d tasks in the list.", tasks.size());
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddCommand)) {
            return false;
        }
        AddCommand o = (AddCommand) other;
        return this.task.equals(o.task);
    }
    @Override
    public String toString() {
        return "AddCommand: " + task.toString();
    }
}

