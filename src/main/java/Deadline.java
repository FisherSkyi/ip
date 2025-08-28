import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected String dateString;
    private static boolean isSilentLoading = false; // Flag for silent loading

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
    
    @Override
    public String toString() {
        if (dateTime == null) {
            return "[D]" + super.toString() + " (by: " + dateString + ")";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
            return "[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
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
