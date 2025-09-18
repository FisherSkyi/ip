package seb;
/**
 * Represents a task with a description, completion status, and type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected PriorityType priority = PriorityType.LOW; // 0 = no priority, 1 = highest, etc.
    public Task(String description, TaskType type) {
        this(description, type, PriorityType.UNSPECIFIEDP);
    }
    /**
     * Creates a Task with the given description, type, and priority.
     * @param description The description of the task.
     * @param type The type of the task (e.g., TODO, DEADLINE, EVENT).
     * @param priority The priority level of the task in integer.
     */
    public Task(String description, TaskType type, PriorityType priority) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.priority = priority;
    }
    public TaskType getType() {
        return this.type;
    }
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    public PriorityType getPriority() {
        return this.priority;
    }
    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }
    @Override
    public String toString() {
        String priorityStr = this.priority.toString();
        priorityStr = "[" + priorityStr + "] ";
        return priorityStr + this.getStatusIcon() + this.description;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task o = (Task) other;
        return this.description.equals(o.description)
                && this.isDone == o.isDone
                && this.type == o.type;
    }
}
