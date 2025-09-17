package seb.command;
import seb.Storage;
import seb.TaskList;
/**
 * Represents the hi command.
 */
public class HiCommand implements Command {
    /**
     * Greets the user when the hi command is executed
     * @return A greeting message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "     Hello! I'm Seb.\n     What can I do for you?";
    }
}
