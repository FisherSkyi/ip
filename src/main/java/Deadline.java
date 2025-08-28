import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        try {
            this.by = TimeParser.parseDateTime(by.trim());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
