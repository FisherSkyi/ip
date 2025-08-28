package duke.command;
import duke.*;
public interface Command {
    String execute(TaskList tasks, Storage storage);
}

