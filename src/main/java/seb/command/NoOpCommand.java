package seb.command;
import seb.*;
public class NoOpCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}