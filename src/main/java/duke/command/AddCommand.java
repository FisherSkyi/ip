package duke.command;
import duke.*;

public class AddCommand implements Command {
    private final Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }
    
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTasks(task);
        storage.saveTasks(tasks);
        return "     Got it. I've added this task:\n" +
               "       " + task.toString() + "\n" +
               String.format("     Now you have %d tasks in the list.", tasks.size());
    }
}

