package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a FindCommand
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks.
     * @param tasks   The TaskList to search in.
     * @param storage The Storage to use.
     * @return A string with the found tasks or a message if none are found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            return "     No tasks found matching your search.";
        }

        StringBuilder sb = new StringBuilder("     Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            sb.append("       ").append(i + 1).append(".").append(foundTasks.get(i).toString());
            if (i < foundTasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

