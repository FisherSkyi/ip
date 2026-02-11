package seb;

import java.util.List;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import seb.command.Command;

public class AiHelper {
    private final ChatModel model;
    
    public AiHelper() {
        this.model = OpenAiChatModel.builder()
                .apiKey(System.getenv("LLM_API_KEY"))
                .baseUrl("https://api.groq.com/openai/v1")
                .modelName("llama-3.3-70b-versatile") // or another Groq model
                .build();
    }
    
    public ChatResponse getAiResponse(String systemPrompt, String userPrompt) {
        ChatRequest req = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(systemPrompt),
                        UserMessage.from(userPrompt)
                ))
                .build();
        
        ChatResponse res = model.chat(req);
        return res;
    }
    
    public static void main(String[] args) {
        AiHelper agent = new AiHelper();
//        String reply = agent.getAiResponse(
//                "You are a helpful assistant for a CLI app",
//                "How do I add a task with a deadline?").toString();
        String reply = agent.askAiAboutFeature(
                "How do I add a task with a deadline?", agent);
        System.out.println(reply);
    }
    
    public String askAiAboutFeature(String userPrompt, AiHelper aiHelper) {
        String systemPrompt = "You are helping users of a CLI app. Answer the user's query about the features of the app, based on the app's commands given below. Limit your answer to one sentence.\n\n"
                + "1. todo <description> - Adds a todo task.\n"
                + "2. deadline <description> /by <date> - Adds a task with a deadline.\n"
                + "3. event <description> /at <date> - Adds an event task.\n"
                + "4. list - Lists all tasks.\n"
                + "5. done <task_number> - Marks a task as done.\n"
                + "6. delete <task_number> - Deletes a task.\n"
                + "7. help - Shows the help message.";
        
        String testSystemPrompt = "You are an agent that is good at geography. Answer the user's query about geography based on the facts given below. Limit your answer to one sentence.\n\n"
                + "1. The capital of France is Paris.\n"
                + "2. The largest continent is Asia.\n"
                + "3. The longest river in the world is the Nile.\n"
                + "4. Mount Everest is the highest mountain above sea level.\n"
                + "5. The Great Barrier Reef is located in Australia.";
        
        return aiHelper.getAiResponse(systemPrompt, userPrompt).aiMessage().text();
    }
    
    public String commandAiToPerform(String userPrompt, AiHelper aiHelper, TaskList tasks, Storage storage) {
        String systemPrompt = "You are an assistant for a CLI task management app. Based on the user's request, generate a valid command that the app understands."
                + "Only respond with the command, nothing else.\n\n"
                + "Available commands:\n"
                + "1. todo <description> - Adds a todo task.\n"
                + "2. deadline <description> /by <date> - Adds a task with a deadline.\n"
                + "3. event <description> /at <date> - Adds an event task.\n"
                + "4. list - Lists all tasks.\n"
                + "5. done <task_number> - Marks a task as done.\n"
                + "6. delete <task_number> - Deletes a task.\n"
                + "7. help - Shows the help message.";
        String commandAsString = aiHelper.getAiResponse(systemPrompt, userPrompt).aiMessage().text();
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand(commandAsString);
            command.execute(tasks, storage);
            return "Command executed: " + command;
        } catch (Exception e) {
            return commandAsString;
        }
    }
    
    
}

