import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeParser {
    public static LocalDateTime parseDateTime(String input) {
        // 1. Try ISO_LOCAL_DATE_TIME (2007-12-03T10:15:30)
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException ignored) {}
        
        // 2. Try yyyy-MM-dd (defaulting time to 00:00, 12:00AM)
        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
            return date.atStartOfDay();
        } catch (DateTimeParseException ignored) {}
        
        // 3. Try d/M/yyyy HHmm
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException ignored) {}
        
        // If none worked:
        throw new IllegalArgumentException(
                "Invalid date format. Please use one of: " +
                        "yyyy-MM-ddTHH:mm:ss, yyyy-MM-dd, or d/M/yyyy HHmm."
        );
    }
}
