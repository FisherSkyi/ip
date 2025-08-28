import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description, TaskType.EVENT);
        try {
            this.start = TimeParser.parseDateTime(start.trim());
            this.end = TimeParser.parseDateTime(end.trim());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}