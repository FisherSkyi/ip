public class UnmarkCommand implements Command {
    private final int index;
    
    public UnmarkCommand(int index) {
        this.index = index;
    }
    
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.getTasks(index).unmarkAsDone();
        storage.saveTasks(tasks);
        
        return "     OK, I've marked this task as not done yet:\n" +
               "       " + tasks.getTasks(index).toString();
    }
}

