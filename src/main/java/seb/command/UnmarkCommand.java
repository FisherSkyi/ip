package seb.command;
import seb.Storage;
import seb.TaskList;
/**
 * Represents the unmark command.
 */
public class UnmarkCommand implements Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the unmark command by marking the task at the specified index as not done
     * and saving the updated task list to storage.
     * @param tasks
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.getTasks(index).unmarkAsDone();
        storage.saveTasks(tasks);
        return "     OK, I've marked this task as not done yet:\n"
                + "       " + tasks.getTasks(index).toString();
    }
}

