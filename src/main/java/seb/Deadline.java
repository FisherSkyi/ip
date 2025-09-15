package seb;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected String dateString;
    private static boolean isSilentLoading = false; // Flag for silent loading
    
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
    
    public Deadline(String description, String by, int priority) {
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
