package seb;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public Task getTasks(int index) {
        return this.tasks.get(index);
    }
    public void deleteTasks(int index) {
        this.tasks.remove(index);
    }
    public void addTasks(Task t) {
        this.tasks.add(t);
    }
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for within the task descriptions.
     * @return An ArrayList<Task> containing all tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sets the priority of the task at the specified index.
     * @param index The index of the task (0-based).
     * @param priority The new priority value.
     */
    public void setPriority(int index, int priority) {
        this.tasks.get(index).setPriority(priority);
    }
}
