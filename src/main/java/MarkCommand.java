public class MarkCommand implements Command {
    private final int index;
    
    public MarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.getTasks(index).markAsDone();
        storage.saveTasks(tasks);
        
        return "     Nice! I've marked this task as done:\n" +
               "       " + tasks.getTasks(index).toString();
    }
}

