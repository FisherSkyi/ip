package seb.command;

import seb.AiHelper;
import seb.Storage;
import seb.TaskList;

/**
 * Represents a command to query the AI helper about app features.
 */
public class AgentCommand implements Command {
    private final String query;
    private final AiHelper aiHelper;
    
    /**
     * Constructs an AgentCommand with the user's query.
     * @param query the user's question about app features
     */
    public AgentCommand(String query) {
        this.query = query;
        this.aiHelper = new AiHelper();
    }
    


    @Override
    public String execute(TaskList tasks, Storage storage) {
        return aiHelper.commandAiToPerform(query, aiHelper, tasks, storage);
    }
}
