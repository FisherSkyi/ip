public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("       ").append(i + 1).append(".").append(tasks.getTasks(i).toString());
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

