package duke.command;
import duke.*;
public class NoOpCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}