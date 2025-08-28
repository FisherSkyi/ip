public class Seb {
    private final String name = "Seb";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    
    public Seb(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }
    
    public void run() {
        Ui.showWelcome();
        tasks = storage.loadTasks();
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
    
    public static void main(String[] args) {
        new Seb("data/seb.txt").run();
    }
}
