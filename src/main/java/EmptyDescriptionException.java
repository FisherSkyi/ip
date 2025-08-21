public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String taskType) {
        super("    ____________________________________________________________\n" +
                "     OOPS!!! The description of a " + taskType + " is not correct.\n" +
                "     | for todo,     use todo     (description)\n" +
                "     | for deadline, use deadline (description) /by   (time)\n" +
                "     | for event,    use event    (description) /from (time) /to (time)\n" +
                "    ____________________________________________________________");
    }
}