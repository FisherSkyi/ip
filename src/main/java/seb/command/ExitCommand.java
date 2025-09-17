package seb.command;
import seb.Storage;
import seb.TaskList;
/**
 * Represents the exit command.
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "     Bye. Hope to see you again soon!";
    }
}

