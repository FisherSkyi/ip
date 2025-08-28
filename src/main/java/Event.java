import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String startString;
    protected String endString;
    private static boolean isSilentLoading = false; // Flag for silent loading

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

    @Override
    public String toString() {
        if (startDateTime == null && endDateTime == null) {
            // If parsing failed, just use the original strings
            return "[E]" + super.toString() + " (from: " + startString + " to: " + endString + ")";
        } else if (startDateTime == null) {
            // If only start parsing failed
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            return "[E]" + super.toString() + " (from: " + startString +
                   " to: " + endDateTime.format(formatter) + ")";
        } else if (endDateTime == null) {
            // If only end parsing failed
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            return "[E]" + super.toString() + " (from: " + startDateTime.format(formatter) +
                   " to: " + endString + ")";
        } else {
            // If parsing succeeded, format the dates nicely
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            return "[E]" + super.toString() + " (from: " + startDateTime.format(formatter) +
                   " to: " + endDateTime.format(formatter) + ")";
        }
    }
    
    // Static methods to control silent loading mode
    public static void startSilentLoading() {
        isSilentLoading = true;
    }
    
    public static void endSilentLoading() {
        isSilentLoading = false;
    }
}