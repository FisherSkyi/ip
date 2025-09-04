package seb.command;
import seb.*;
public interface Command {
    String execute(TaskList tasks, Storage storage);
}

