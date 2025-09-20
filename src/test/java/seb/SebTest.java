package seb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SebTest {
    @Test
    public void add_deadline_unmark() {
        Deadline deadline = new Deadline("Submit report", "2025-08-29");
        assertEquals("[D][UNSPECIFIED] [ ] Submit report (by: Aug 29 2025)", deadline.toString());
    }

    @Test
    public void markAsDown_mark_stringOutput() {
        Task task = new Todo("Go shopping");
        task.markAsDone();
        assertEquals("[T][UNSPECIFIED] [X] Go shopping", task.toString());
    }
}
