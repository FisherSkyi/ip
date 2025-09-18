package seb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadFile_noFile_emptyList() {
        String filePath = "data/test.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks = storage.loadTasks();
        TaskList realTasks = new TaskList();
        Task task1 = new Event("SEE Jane Street", "2023-10-30",
                "2023-12-31", PriorityType.UNSPECIFIEDP);
        task1.markAsDone();
        realTasks.addTasks(task1);
        Task task2 = new Event("Hack & Roll", "2023-11-20",
                "2023-11-30", PriorityType.LOW);
        realTasks.addTasks(task2);
        Task task3 = new Todo("assignment 0", PriorityType.HIGH);
        realTasks.addTasks(task3);
        Task task4 = new Deadline("homework 2", "2023-11-31", PriorityType.MEDIUM);
        realTasks.addTasks(task4);
        for (int i = 0; i < 4; i++) {
            assertEquals(tasks.getTasks(i).toString(), realTasks.getTasks(i).toString());
        }
    }
}
