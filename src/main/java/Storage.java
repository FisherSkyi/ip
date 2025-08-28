import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            /**
             * example file content:
             * TODO | 1 | read book
             * DEADLINE | 0 | return book | June 6th
             * EVENT | 0 | project meeting | Aug 6th 2-4pm
             */
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\| ");
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                switch (type) {
                case "TODO":
                    Task t = new Todo(description);
                    if (isDone) {
                        t.markAsDone();
                    }
                    tasks.addTasks(t);
                    break;
                case "DEADLINE":
                    String by = parts[3];
                    Task d = new Deadline(description, by);
                    if (isDone) {
                        d.markAsDone();
                    }
                    tasks.addTasks(d);
                    break;
                case "EVENT":
                    String start = parts[3];
                    String end = parts[4];
                    Task e = new Event(description, start, end);
                    if (isDone) {
                        e.markAsDone();
                    }
                    tasks.addTasks(e);
                    break;
                default:
                    throw new InvalidTaskTypeException(type);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (InvalidTaskTypeException e) {
            System.out.println("Invalid type: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs(); // Create directories if not exist
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.getTasks(i);
                StringBuilder sb = new StringBuilder();
                sb.append(t.getType().name()).append(" | "); // Task type
                sb.append(t.isDone ? "1" : "0").append(" | "); // Completion status
                sb.append(t.getDescription()); // Description
                if (t instanceof Deadline) {
                    sb.append(" | ").append(((Deadline) t).by);
                } else if (t instanceof Event) {
                    sb.append(" | ").append(((Event) t).start).append(" | ").append(((Event) t).end);
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

