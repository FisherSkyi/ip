package duke;
/**
 * Used in Storage.
 * Thrown when an invalid task type is encountered.
 */
public class InvalidTaskTypeException extends Exception {
    public InvalidTaskTypeException(String wrongTaskType) {
        super(String.format("%s is invalid task type. Choose from TODO, DEADLINE, EVENT", wrongTaskType));
    }
}