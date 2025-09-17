package seb;
/**
 * Exception thrown when the input command is unknown.
 */
public class UnknownInputException extends Exception {
    public UnknownInputException() {
        super("     OOPS!!! I'm just a chatbot. I can only handle task schedule right now.");
    }
}
