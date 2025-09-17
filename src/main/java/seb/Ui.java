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
    /**
     * Displays a welcome message to the user.
     */
    public static void showWelcome() {
        System.out.println(
                  "    ____________________________________________________________\n"
                + "     Hello! I'm Seb\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________");
    }
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }
    /**
     * Returns a message indicating the priority has been set for a task.
     * @param task The task whose priority was set.
     * @param priority The new priority value.
     * @return The confirmation message.
     */
    public String showPrioritySet(Task task, int priority) {
        if (priority > 0) {
            return "Priority set to " + priority + " for task: " + task.toString();
        } else {
            return "Priority undecided for task: " + task.toString();
        }
    }
}
