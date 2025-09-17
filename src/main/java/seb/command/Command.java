package seb.command;
import seb.Storage;
import seb.TaskList;

/**
 * All commands should be able to execute
 */
public interface Command {
    String execute(TaskList tasks, Storage storage);
}
