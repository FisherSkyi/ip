package duke.command;
import duke.*;
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "     Bye. Hope to see you again soon!";
    }
}

