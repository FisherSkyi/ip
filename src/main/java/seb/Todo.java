package seb;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, int priority) {
        super(description, TaskType.TODO, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
