package seb;
/**
 * Exception thrown when a deadline or event task is created without a time or time range.
 */
public class NoDateException extends RuntimeException {
    /**
     * Constructor for NoDateException.
     * Thrown when a deadline or event task is created without a time or time range.
     */
    public NoDateException() {
        super("     OOPS!!! You need to input a time or time range for deadline and event task \n"
                + "     | for deadline, use deadline (description) /by   (time)\n"
                + "     | for event,    use event    (description) /from (time) /to (time)");
    }
}
