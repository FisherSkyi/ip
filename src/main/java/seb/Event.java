package seb;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date/time.
 */
public class Event extends Task {
    private static boolean isSilentLoading = false; // Flag for silent loading
    // Store both parsed date/time and original strings
    protected LocalDate startDateTime;
    protected LocalDate endDateTime;
    protected String startString;
    protected String endString;
    /**
     * Creates an Event task.
     * @param description the description of the task
     * @param start the start date/time as a string
     * @param end the end date/time as a string
     */
    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        this.startString = start; // Always store the original strings
        this.endString = end;
        try {
            this.startDateTime = TimeParser.parseDateTime(start.trim());
            this.endDateTime = TimeParser.parseDateTime(end.trim());
        } catch (IllegalArgumentException e) {
            // Only show error if not silent loading and not "null"
            if (!isSilentLoading && !start.equals("null") && !end.equals("null")) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * initialize event with priority
     * @param description
     * @param start
     * @param end
     * @param priority
     */
    public Event(String description, String start, String end, PriorityType priority) {
        super(description, TaskType.EVENT, priority);
        this.startString = start; // Always store the original strings
        this.endString = end;
        try {
            // try to parse the date/time strings with 3 different formats
            this.startDateTime = TimeParser.parseDateTime(start.trim());
            this.endDateTime = TimeParser.parseDateTime(end.trim());
        } catch (IllegalArgumentException e) {
            // Only show error if not silent loading and not "null"
            if (!isSilentLoading && !start.equals("null") && !end.equals("null")) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Returns a string representation of the Event task.
     * @return the string representation
     */
    @Override
    public String toString() {
        String result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (startDateTime == null && endDateTime == null) {
            result = "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
        } else if (startDateTime == null) {
            result = "[E]" + super.toString() + " (from: " + startString
                    + " to: " + endDateTime.format(formatter) + ")";
        } else if (endDateTime == null) {
            result = "[E]" + super.toString() + " (from: " + startDateTime.format(formatter)
                    + " to: " + endString + ")";
        } else {
            result = "[E]" + super.toString() + " (from: " + startDateTime.format(formatter)
                    + " to: " + endDateTime.format(formatter) + ")";
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
