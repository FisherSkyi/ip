package seb;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Utility class for parsing date and time strings into LocalDateTime objects.
 */
public class TimeParser {
    /**
     * Parses a date/time string into a LocalDateTime object.
     * @param input the date/time string to parse
     * @return the parsed LocalDateTime object
     */
    public static LocalDate parseDateTime(String input) {
        // Try multiple formats for parsing
        String[] formats = {
            "yyyy-MM-dd",
            "MM-dd-yyyy",
            "dd-MM-yyyy"
        };
        for (String format : formats) {
            try {
                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern(format));
                return date;
            } catch (DateTimeParseException ignored) {
                // Try next format
            }
        }
        // If none worked:
        throw new IllegalArgumentException(
                "Invalid date format. Please use one of: "
                + "yyyy-MM-dd | MM-dd-yyyy | dd-MM-yyyy"
        );
    }
}
