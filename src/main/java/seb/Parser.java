package seb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seb.command.AddCommand;
import seb.command.Command;
import seb.command.DeleteCommand;
import seb.command.ExitCommand;
import seb.command.FindCommand;
import seb.command.HiCommand;
import seb.command.ListCommand;
import seb.command.MarkCommand;
import seb.command.NoOpCommand;
import seb.command.SetPriorityCommand;
import seb.command.UnmarkCommand;
/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    // task must not be empty, can contain spaces, must not start with space, time/date can be empty
    private static final Pattern TODO_RE = Pattern.compile("^todo\\s+(\\S.*)$");
    private static final Pattern DEADLINE_RE = Pattern.compile("^deadline\\s+(\\S.*)\\s+/by(?:\\s+(\\S.*))?$");
    private static final Pattern EVENT_RE =
            Pattern.compile("^event\\s+(\\S.*)\\s+/from(?:\\s+(\\S.*))?\\s+/to(?:\\s+(\\S.*))?$");
    // must be number (index of the task) after mark, unmark, delete
    private static final Pattern MARK_RE = Pattern.compile("^mark\\s+(\\d+)$");
    private static final Pattern UNMARK_RE = Pattern.compile("^unmark\\s+(\\d+)$");
    private static final Pattern DELETE_RE = Pattern.compile("^delete\\s+(\\d+)$");
    private static final Pattern FIND_RE = Pattern.compile("^find\\s+(\\S.*)$");
    private static final Pattern PRIORITY_SET_RE = Pattern.compile("^priority\\s+(\\d+)\\s+(\\S+)$");
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
        if (trimmedInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (trimmedInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (trimmedInput.equalsIgnoreCase("hi")
                || trimmedInput.equalsIgnoreCase("hello")) {
            return new HiCommand();
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
        } else if (trimmedInput.startsWith("find")) {
            return parseFindCommand(trimmedInput);
        } else if (trimmedInput.startsWith("priority")) {
            return parseSetPriorityCommand(trimmedInput);
        } else if (!trimmedInput.isEmpty()) {
            throw new UnknownInputException();
        }
        return new NoOpCommand(); // empty input
    }
    private Command parseSetPriorityCommand(String input) {
        Matcher matcher = PRIORITY_SET_RE.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            PriorityType priority;
            String priorityStr = matcher.group(2);
            try {
                // Try to parse as integer first
                int priorityInt = Integer.parseInt(priorityStr);
                priority = PriorityType.fromInt(priorityInt);
            } catch (NumberFormatException e) {
                // If not integer, try to parse as enum name
                try {
                    priority = PriorityType.valueOf(priorityStr.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Invalid priority value. Setting to UNSPECIFIEDP.");
                    priority = PriorityType.UNSPECIFIEDP;
                }
            }
            return new SetPriorityCommand(index, priority);
        }
        return new NoOpCommand();
    }
    /**
     * Parses a todo command and returns the corresponding AddCommand object.
     * @param input the user input string
     * @return the AddCommand object for the todo task
     * @throws WrongDescriptionException if the task description is missing or incorrect
     */
    private Command parseTodoCommand(String input) throws WrongDescriptionException {
        String[] parts = input.split("/priority");
        Matcher matcher = TODO_RE.matcher(parts[0].trim());
        if (!matcher.matches()) {
            throw new WrongDescriptionException("todo");
        }
        String description = matcher.group(1).trim();
        PriorityType priority;
        if (parts.length > 1) {
            try {
                priority = PriorityType.fromInt(Integer.parseInt(parts[1].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid priority value. Setting to UNSPECIFIEDP.");
                priority = PriorityType.UNSPECIFIEDP;
            }
            return new AddCommand(new Todo(description, priority));
        } else {
            return new AddCommand(new Todo(description, PriorityType.UNSPECIFIEDP));
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
        String[] prioritySplit = input.split("/priority");
        String mainPart = prioritySplit[0].trim();
        Matcher matcher = DEADLINE_RE.matcher(mainPart);
        if (!matcher.matches()) {
            throw new WrongDescriptionException("deadline");
        }
        String description = matcher.group(1).trim();
        String by = matcher.group(2) == null ? "" : matcher.group(2).trim();
        PriorityType priority;
        if (prioritySplit.length > 1) {
            try {
                priority = PriorityType.fromInt(Integer.parseInt(prioritySplit[1].trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid priority value. Setting to UNSPECIFIEDP.");
                priority = PriorityType.UNSPECIFIEDP;
            }
            return new AddCommand(new Deadline(description, by, priority));
        } else {
            return new AddCommand(new Deadline(description, by, PriorityType.UNSPECIFIEDP));
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
        String[] prioritySplit = input.split("/priority");
        String mainPart = prioritySplit[0].trim();
        Matcher matcher = EVENT_RE.matcher(mainPart);
        if (!matcher.matches()) {
            throw new WrongDescriptionException("event");
        }
        String description = matcher.group(1).trim();
        String start = matcher.group(2) == null ? "" : matcher.group(2).trim();
        String end = matcher.group(3) == null ? "" : matcher.group(3).trim();
        PriorityType priority;
        if (prioritySplit.length > 1) {
            try {
                priority = PriorityType.fromInt(Integer.parseInt(prioritySplit[1].trim()));
            } catch (NumberFormatException e) {
                priority = PriorityType.UNSPECIFIEDP;
            }
            return new AddCommand(new Event(description, start, end, priority));
        }
        return new AddCommand(new Event(description, start, end, PriorityType.UNSPECIFIEDP));
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
     * Parses a find command and returns the corresponding FindCommand object.
     * @param input the user input string
     * @return the FindCommand object for finding tasks
     * @throws IllegalArgumentException if the keyword is missing
     */
    private Command parseFindCommand(String input) {
        Matcher m = FIND_RE.matcher(input);
        if (m.matches()) {
            return new FindCommand(m.group(1));
        } else {
            throw new IllegalArgumentException("Invalid find command format");
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
