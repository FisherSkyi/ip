package seb;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static boolean isSilentLoading = false; // Flag for silent loading
    protected LocalDate date;
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
            this.date = TimeParser.parseDateTime(by.trim());
        } catch (IllegalArgumentException e) {
            // Only show error if not silent loading and not "null"
            if (!isSilentLoading && !by.equals("null")) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * initialize deadline with priority
     * @param description description of deadline
     * @param by deadline date as string
     * @param priority priority of the deadline
     */
    public Deadline(String description, String by, PriorityType priority) {
        super(description, TaskType.DEADLINE, priority);
        this.dateString = by;
        try {
            this.date = TimeParser.parseDateTime(by.trim());
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
        if (date == null) {
            result = "[D]" + super.toString() + " (by: " + dateString + ")";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            result = "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
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
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline o = (Deadline) other;
        return super.equals(o) && this.dateString.equals(o.dateString);
    }
}
