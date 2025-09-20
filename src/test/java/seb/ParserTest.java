package seb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seb.command.AddCommand;
import seb.command.Command;

public class ParserTest {
    @Test
    public void parse_todo_stringOutput() throws UnknownInputException, WrongDescriptionException {
        Parser parser = new Parser();
        Command c = parser.parseCommand("todo Read book");
        AddCommand real = new AddCommand(new Todo("Read book"));
        assertEquals(c.toString(), real.toString());
    }
    @Test
    public void parse_deadlineSpecialDate_formattedOutput() throws
            UnknownInputException, WrongDescriptionException, NoDateException {
        Parser parser = new Parser();
        Command c = parser.parseCommand("deadline Submit report /by 2025-08-29");
        AddCommand real = new AddCommand(new Deadline("Submit report", "2025-08-29"));
        assertEquals(c.toString(), real.toString());
    }
}
