package duke;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import duke.command.*;

public class Parser {
    // task must not be empty, can contain spaces, must not start with space, time/date can be empty
    private static final Pattern TODO_RE = Pattern.compile("^todo\\s+(\\S.*)$");
    private static final Pattern DEADLINE_RE = Pattern.compile("^deadline\\s+(\\S.*)\\s+/by(?:\\s+(\\S.*))?$");
    private static final Pattern EVENT_RE = Pattern.compile("^event\\s+(\\S.*)\\s+/from(?:\\s+(\\S.*))?\\s+/to(?:\\s+(\\S.*))?$");
    // must be number after mark, unmark, delete
    private static final Pattern MARK_RE = Pattern.compile("^mark\\s+(\\d+)$");
    private static final Pattern UNMARK_RE = Pattern.compile("^unmark\\s+(\\d+)$");
    private static final Pattern DELETE_RE = Pattern.compile("^delete\\s+(\\d+)$");
    
    /**
     * Parses user input and returns the corresponding Command object.
     * @param input the user input string
     * @return the Command object representing the user input
     * @throws WrongDescriptionException if the task description is missing or incorrect
     * @throws NoDateException if the date/time for deadline or event is missing
     * @throws UnknownInputException if the input command is unknown
     */
    public Command parseCommand(String input) throws WrongDescriptionException, NoDateException, UnknownInputException {
        String trimmedInput = input.trim();
        
        if (trimmedInput.equals("bye")) {
            return new ExitCommand();
        } else if (trimmedInput.equals("list")) {
            return new ListCommand();
        } else if (trimmedInput.startsWith("todo")) {
            return parseTodoCommand(trimmedInput);
        } else if (trimmedInput.startsWith("deadline")) {
            return parseDeadlineCommand(trimmedInput);
        } else if (trimmedInput.startsWith("event")) {
            return parseEventCommand(trimmedInput);
        } else if (trimmedInput.startsWith("mark")) {
            return parseMarkCommand(trimmedInput);
        } else if (trimmedInput.startsWith("unmark")) {
            return parseUnmarkCommand(trimmedInput);
        } else if (trimmedInput.startsWith("delete")) {
            return parseDeleteCommand(trimmedInput);
        } else if (!trimmedInput.isEmpty()) {
            throw new UnknownInputException();
        } 
        return new NoOpCommand(); // empty input
    }
    
    /**
     * Parses a todo command and returns the corresponding AddCommand object.
     * @param input the user input string
     * @return the AddCommand object for the todo task
     * @throws WrongDescriptionException if the task description is missing or incorrect
     */
    private Command parseTodoCommand(String input) throws WrongDescriptionException {
        Matcher m = TODO_RE.matcher(input);
        if (m.matches()) {
            return new AddCommand(new Todo(m.group(1)));
        } else {
            throw new WrongDescriptionException("todo");
        }
    }
    
    /**
     * Parses a deadline command and returns the corresponding AddCommand object.
     * @param input the user input string
     * @return the AddCommand object for the deadline task
     * @throws WrongDescriptionException if the task description is missing or incorrect
     * @throws NoDateException if the deadline date/time is missing
     */
    private Command parseDeadlineCommand(String input) throws WrongDescriptionException, NoDateException {
        Matcher m = DEADLINE_RE.matcher(input);
        if (m.matches()) {
            if (m.group(2) == null) {
                throw new NoDateException();
            } else {
                return new AddCommand(new Deadline(m.group(1), m.group(2)));
            }
        } else {
            throw new WrongDescriptionException("deadline");
        }
    }
    
    /**
     * Parses an event command and returns the corresponding AddCommand object.
     * @param input the user input string
     * @return the AddCommand object for the event task
     * @throws WrongDescriptionException if the task description is missing or incorrect
     * @throws NoDateException if the event date/time range is missing
     */
    private Command parseEventCommand(String input) throws WrongDescriptionException, NoDateException {
        Matcher m = EVENT_RE.matcher(input);
        if (m.matches()) {
            if (m.group(2) == null || m.group(3) == null) {
                throw new NoDateException();
            } else {
                return new AddCommand(new Event(m.group(1), m.group(2), m.group(3)));
            }
        } else {
            throw new WrongDescriptionException("event");
        }
    }
    
    /**
     * Parses a mark command and returns the corresponding MarkCommand object.
     * @param input the user input string
     * @return the MarkCommand object for marking the task
     */
    private Command parseMarkCommand(String input) {
        Matcher m = MARK_RE.matcher(input);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1)) - 1;
            return new MarkCommand(index);
        } else {
            throw new IllegalArgumentException("Invalid mark command format");
        }
    }
    
    /**
     * Parses an unmark command and returns the corresponding UnmarkCommand object.
     * @param input the user input string
     * @return the UnmarkCommand object for unmarking the task
     */
    private Command parseUnmarkCommand(String input) {
        Matcher m = UNMARK_RE.matcher(input);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1)) - 1;
            return new UnmarkCommand(index);
        } else {
            throw new IllegalArgumentException("Invalid unmark command format");
        }
    }
    
    /**
     * Parses a delete command and returns the corresponding DeleteCommand object.
     * @param input the user input string
     * @return the DeleteCommand object for deleting the task
     */
    private Command parseDeleteCommand(String input) {
        Matcher m = DELETE_RE.matcher(input);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1)) - 1;
            return new DeleteCommand(index);
        } else {
            throw new IllegalArgumentException("Invalid delete command format");
        }
    }
}