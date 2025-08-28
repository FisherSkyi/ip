package duke.command;
import duke.*;
public class DeleteCommand implements Command {
    private final int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
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

