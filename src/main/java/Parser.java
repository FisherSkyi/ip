import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    // task must not be empty, can contain spaces, must not start with space, time/date can be empty
    private static final Pattern TODO_RE = Pattern.compile("^todo\\s+(\\S.*)$");
    private static final Pattern DEADLINE_RE = Pattern.compile("^deadline\\s+(\\S.*)\\s+/by(?:\\s+(\\S.*))?$");
    private static final Pattern EVENT_RE = Pattern.compile("^event\\s+(\\S.*)\\s+/from(?:\\s+(\\S.*))?\\s+/to(?:\\s+(\\S.*))?$");
    // must be number after mark, unmark, delete
    private static final Pattern MARK_RE = Pattern.compile("^mark\\s+(\\d+)$");
    private static final Pattern UNMARK_RE = Pattern.compile("^unmark\\s+(\\d+)$");
    private static final Pattern DELETE_RE = Pattern.compile("^delete\\s+(\\d+)$");
    
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
    
    private Command parseTodoCommand(String input) throws WrongDescriptionException {
        Matcher m = TODO_RE.matcher(input);
        if (m.matches()) {
            return new AddCommand(new Todo(m.group(1)));
        } else {
            throw new WrongDescriptionException("todo");
        }
    }
    
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
    
    private Command parseMarkCommand(String input) {
        Matcher m = MARK_RE.matcher(input);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1)) - 1;
            return new MarkCommand(index);
        } else {
            throw new IllegalArgumentException("Invalid mark command format");
        }
    }
    
    private Command parseUnmarkCommand(String input) {
        Matcher m = UNMARK_RE.matcher(input);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1)) - 1;
            return new UnmarkCommand(index);
        } else {
            throw new IllegalArgumentException("Invalid unmark command format");
        }
    }
    
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