public class InvalidTaskTypeException extends Exception {
    public InvalidTaskTypeException(String wrongTaskType) {
        super(String.format("%s is invalid task type. Choose from TODO, DEADLINE, EVENT", wrongTaskType));
    }
}