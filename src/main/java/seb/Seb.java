package seb;
import seb.command.Command;
import seb.command.ExitCommand;

/**
 * The main class of the Seb application.
 * It initializes the application, loads tasks from storage,
 * and processes user commands in a loop until the user exits.
 */
public class Seb {
    private final String name = "Seb";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    /**
     * Constructs a Seb application with the specified file path for storage.
     * It initializes the UI, storage, parser, and loads tasks from the specified file.
     * @param filePath The file path where tasks are stored.
     */
    public Seb(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        tasks = storage.loadTasks();
    }
    /**
     * Runs the main loop of the Seb application.
     * It displays a welcome message, loads tasks from storage.
     * Then it enters a loop to read and process user commands until the user issues an exit command
     */
    public void run() {
        Ui.showWelcome();
        boolean isWorking = true;
        while (isWorking) {
            try {
                String input = this.ui.readCommand().trim();
                Ui.showLine();
                Command command = parser.parseCommand(input);
                String response = command.execute(tasks, storage);
                if (!response.isEmpty()) {
                    System.out.println(response);
                }
                if (command instanceof ExitCommand) {
                    isWorking = false;
                }
            } catch (WrongDescriptionException | UnknownInputException | NoDateException e) {
                Ui.showError(e);
            } finally {
                Ui.showLine();
            }
        }
    }
    public String getResponse(String userInput) throws UnknownInputException, WrongDescriptionException {
        try {
            String input = userInput.trim();
            Command command = parser.parseCommand(input);
            String response = command.execute(tasks, storage);
            return response;
        } catch (UnknownInputException | WrongDescriptionException e) {
            String error = e.getMessage();
            return error;
        }
    }
    public static void main(String[] args) {
        new Seb("data/seb.txt").run();
    }
}
