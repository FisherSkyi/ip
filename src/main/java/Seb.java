import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seb {
    private final String name = "Seb";
    private static ArrayList<Task> things = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        things.clear();
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm Seb, a chatbot made by Letian\n" +
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
                        "    ____________________________________________________________"
                );
                break;
            } else if (line.matches("^mark \\d+$")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                things.get(index).markAsDone();
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       " + things.get(index).toString() + "\n" +
                        "    ____________________________________________________________");
            } else if (line.matches("^unmark \\d+$")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                things.get(index).unmarkAsDone();
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     OK, I've marked this task as not done yet:\n" +
                        "       " + things.get(index).toString() + "\n" +
                        "    ____________________________________________________________");
            } else if (line.equals("list")) {
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println("       " + (i+1) + "." + things.get(i).toString());
                }
                System.out.println("    ____________________________________________________________");
            } else if (line.matches("^todo .+$")) {
                things.add(new Todo(line.substring(5))); // here remove 'todo ' in line
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + things.get(things.size()-1).toString() + "\n" +
                        String.format("     Now you have %d tasks in the list.\n", things.size()) +
                        "    ____________________________________________________________");
            } else if (line.matches("^deadline .+ /by .+$")) {
                Matcher m = Pattern.compile("^deadline (.+) /by (.+)$").matcher(line);
                m.matches();
                things.add(new Deadline(m.group(1), m.group(2)));
                System.out.println(
                        "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       " + things.get(things.size()-1).toString() + "\n" +
                        String.format("     Now you have %d tasks in the list.\n", things.size()) +
                        "    ____________________________________________________________");
            } else if (line.matches("^event .+ /from .+ /to .+$")) {
                Matcher m = Pattern.compile("^event (.+) /from (.+) /to (.+)$").matcher(line);
                m.matches();
                things.add(new Event(m.group(1), m.group(2), m.group(3)));
                System.out.println(
                        "    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n" +
                                "       " + things.get(things.size()-1).toString() + "\n" +
                                String.format("     Now you have %d tasks in the list.\n", things.size()) +
                                "    ____________________________________________________________");
            } else if (!line.isEmpty()) {
                things.add(new Task(line));
                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("     added: %s\n", line) +
                        "    ____________________________________________________________"
                );
            }
        }
    }
}
