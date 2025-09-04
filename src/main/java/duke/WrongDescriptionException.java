package duke;

/**
 * Exception thrown when the description of a task is incorrect.
 */
public class WrongDescriptionException extends Exception {
    /**
     * Constructor for WrongDescriptionException.
     * @param taskType
     */
    public WrongDescriptionException(String taskType) {
        super("     OOPS!!! The description of a "
                + taskType
                + " is not correct.\n"
                + "     | for todo,     use todo     (description)\n"
                + "     | for deadline, use deadline (description) /by   (time)\n"
                + "     | for event,    use event    (description) /from (time) /to (time)");
    }
}
