package seb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages loading and saving of tasks to a specified file.
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the specified file.
     * @return TaskList containing the loaded tasks.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);
        if (!file.exists()) {
            return tasks;
        }
        Deadline.startSilentLoading();
        Event.startSilentLoading();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\| ");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                try {
                    checkType(type, description, isDone, parts, tasks);
                } catch (Exception e) {
                    System.err.println("Warning: Problem loading task: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            // Disable silent loading mode after loading is complete
            Deadline.endSilentLoading();
            Event.endSilentLoading();
        }
        return tasks;
    }
    /**
     * Checks the task type and creates the corresponding task object.
     * @param type the type of the task (TODO, DEADLINE, EVENT)
     * @param description the description of the task
     * @param isDone whether the task is marked as done
     * @param parts the parts of the line split by "| "
     * @param tasks the TaskList to add the created task to
     * @throws InvalidTaskTypeException if the task type is invalid
     */
    private static void checkType(String type, String description, boolean isDone, String[] parts, TaskList tasks)
            throws InvalidTaskTypeException {
        switch (type) {
        case "TODO":
            PriorityType todoPriority = parts.length > 3
                    ? PriorityType.fromInt(Integer.parseInt(parts[3].trim()))
                    : PriorityType.UNSPECIFIEDP;
            Task t = new Todo(description, todoPriority);
            if (isDone) {
                t.markAsDone();
            }
            tasks.addTasks(t);
            break;
        case "DEADLINE":
            String by = parts.length > 3 ? parts[3].trim() : "";
            PriorityType deadlinePriority = parts.length > 4
                    ? PriorityType.fromInt(Integer.parseInt(parts[4].trim()))
                    : PriorityType.UNSPECIFIEDP;
            Task d = new Deadline(description, by, deadlinePriority);
            if (isDone) {
                d.markAsDone();
            }
            tasks.addTasks(d);
            break;
        case "EVENT":
            String start = parts.length > 3 ? parts[3].trim() : "";
            String end = parts.length > 4 ? parts[4].trim() : "";
            PriorityType eventPriority = parts.length > 5
                    ? PriorityType.fromInt(Integer.parseInt(parts[5].trim()))
                    : PriorityType.UNSPECIFIEDP;
            Task e = new Event(description, start, end, eventPriority);
            if (isDone) {
                e.markAsDone();
            }
            tasks.addTasks(e);
            break;
        default:
            throw new InvalidTaskTypeException(type);
        }
    }
    /**
     * Saves the given TaskList to the specified file.
     * @param tasks the TaskList to save
     */
    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs(); // Create directories if not exist
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTasks(i);
                StringBuilder sb = new StringBuilder();
                sb.append(t.getType().name()).append(" | ");
                sb.append(t.isDone ? "1" : "0").append(" | ");
                sb.append(t.getDescription());
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    sb.append(" | ").append(d.dateString).append(" | ").append(PriorityType.toInt(d.getPriority()));
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    sb.append(" | ").append(e.startString).append(" | ").append(e.endString)
                            .append(" | ").append(PriorityType.toInt(e.getPriority()));
                } else if (t instanceof Todo) {
                    sb.append(" | null | ").append(PriorityType.toInt(t.getPriority()));
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
