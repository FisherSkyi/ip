package seb;
import java.util.Scanner;

/**
 * Represents the user interface.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Reads a command from the user.
     * @return the command as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public static void showWelcome() {
        System.out.println("    ____________________________________________________________\n" +
                           "     Hello! I'm Seb\n" +
                           "     What can I do for you?\n" +
                           "    ____________________________________________________________");
    }
    
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }
    
    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }
}

