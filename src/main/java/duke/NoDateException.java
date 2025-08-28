package duke;
public class NoDateException extends RuntimeException {
    public NoDateException() {
        super("     OOPS!!! You need to input a time or time range for deadline and event task \n" +
              "     | for deadline, use deadline (description) /by   (time)\n" +
              "     | for event,    use event    (description) /from (time) /to (time)");
    }
}
