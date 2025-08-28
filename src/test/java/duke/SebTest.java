package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SebTest {
    @Test
    public void add_deadline_unmark() {
        Deadline deadline = new Deadline("Submit report", "2025-08-29");
        assertEquals("[D][ ] Submit report (by: Aug 29 2025, 12:00AM)", deadline.toString());
    }

    @Test
    public void markAsDown_mark_stringOutput() {
        Task task = new Todo("Go shopping");
        task.markAsDone();
        assertEquals("[T][X] Go shopping", task.toString());
    }
}