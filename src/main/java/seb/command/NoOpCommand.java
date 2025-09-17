package seb.command;
import seb.Storage;
import seb.TaskList;
/**
 * Represents a no-operation command that does nothing when executed.
 */
public class NoOpCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}
