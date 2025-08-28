package duke;
import java.util.ArrayList;

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
}