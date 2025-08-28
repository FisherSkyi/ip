import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static void showWelcome() {
        System.out.println("    ____________________________________________________________\n" +
                           "     Hello! I'm Seb\n" +
                           "     What can I do for you?\n" +
                           "    ____________________________________________________________");
    }
    
}

