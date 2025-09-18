package seb;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static boolean isSilentLoading = false; // Flag for silent loading
    protected LocalDateTime dateTime;
    protected String dateString;
    /**
     * Creates a Deadline task.
     * @param description the description of the task
     * @param by the deadline date/time as a string
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.dateString = by;
        try {
            this.dateTime = TimeParser.parseDateTime(by.trim());
        } catch (IllegalArgumentException e) {
            // Only show error if not silent loading and not "null"
            if (!isSilentLoading && !by.equals("null")) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * initialize deadline with priority
     * @param description
     * @param by
     * @param priority
     */
    public Deadline(String description, String by, PriorityType priority) {
        super(description, TaskType.DEADLINE, priority);
        this.dateString = by;
        try {
            this.dateTime = TimeParser.parseDateTime(by.trim());
        } catch (IllegalArgumentException e) {
            // Only show error if not silent loading and not "null"
            if (!isSilentLoading && !by.equals("null")) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Returns a string representation of the Deadline task.
     * @return the string representation
     */
    @Override
    public String toString() {
        String result;
        if (dateTime == null) {
            result = "[D]" + super.toString() + " (by: " + dateString + ")";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            result = "[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
        }
        if (priority > 0) {
            result = "[P" + priority + "] " + result;
        }
        return result;
    }
    /**
     * Starts silent loading mode to suppress error messages during loading.
     */
    public static void startSilentLoading() {
        isSilentLoading = true;
    }
    /**
     * Ends silent loading mode.
     */
    public static void endSilentLoading() {
        isSilentLoading = false;
    }
}
