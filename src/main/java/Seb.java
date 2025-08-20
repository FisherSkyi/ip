import java.util.ArrayList;
import java.util.Scanner;

public class Seb {
    private final String name = "Seb";
    private static ArrayList<Task> things = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        things.clear();
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm Seb\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________"
        );
        while (true) {
            if (!sc.hasNextLine()) {
                break;
            }
            String line = sc.nextLine().trim();
            if (line.equals("bye")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                break;
            } else if (line.matches("^mark \\d+$")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                things.get(index).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:\n" +
                        String.format("       [%s] %s", things.get(index).getStatusIcon(), things.get(index).getDescription()));
                System.out.println("    ____________________________________________________________");
            } else if (line.matches("^unmark \\d+$")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                things.get(index).unmarkAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:\n" +
                        String.format("       [%s] %s", things.get(index).getStatusIcon(), things.get(index).getDescription()));
                System.out.println("    ____________________________________________________________");
            } else if (line.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println(String.format("     %d.[%s] %s",i, things.get(i).getStatusIcon(), things.get(i).getDescription()));
                }
                System.out.println("    ____________________________________________________________");
            } else if (!line.isEmpty()) {
                things.add(new Task(line));
                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("     added: %s\n", line) +
                        "    ____________________________________________________________");
            }
        }
    }
}
