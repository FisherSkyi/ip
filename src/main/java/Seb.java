import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seb {
    private final String name = "Seb";
    private static ArrayList<Task> things = new ArrayList<>();
    private static final Pattern TODO_RE = Pattern.compile("^todo\\s+(\\S.*)$");
    private static final Pattern DEADLINE_RE = Pattern.compile("^deadline\\s+(\\S.*)\\s+/by(?:\\s+(\\S.*))?$");
    private static final Pattern EVENT_RE = Pattern.compile("^event\\s+(\\S.*)\\s+/from(?:\\s+(\\S.*))?\\s+/to(?:\\s+(\\S.*))?$");

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
            try {
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
                } else if (line.matches("^delete \\d+$")) {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task t = things.get(index);
                    things.remove(index);
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Noted. I've removed this task:\n" +
                                    "       " + t.toString() + "\n" +
                                    "     Now you have " + things.size() + " tasks in the list.\n" +
                                    "    ____________________________________________________________");
                } else if (line.equals("list")) {
                    System.out.println(
                            "    ____________________________________________________________\n" +
                                    "     Here are the tasks in your list:");
                    for (int i = 0; i < things.size(); i++) {
                        System.out.println("       " + (i + 1) + "." + things.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                } else if (line.startsWith("todo")) { // below is 3 types of task
                    Matcher m = TODO_RE.matcher(line);
                    if (m.matches()) {
                        things.add(new Todo(m.group(1)));
                        System.out.println(
                                "    ____________________________________________________________\n" +
                                        "     Got it. I've added this task:\n" +
                                        "       " + things.get(things.size() - 1).toString() + "\n" +
                                        String.format("     Now you have %d tasks in the list.\n", things.size()) +
                                        "    ____________________________________________________________");
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                } else if (line.startsWith("deadline")) {
                    Matcher m = DEADLINE_RE.matcher(line);
                    if (m.matches()) {
                        if (m.group(2) == null) {
                            throw new NoDateException();
                        } else {
                            things.add(new Deadline(m.group(1), m.group(2)));
                            System.out.println(
                                    "    ____________________________________________________________\n" +
                                            "     Got it. I've added this task:\n" +
                                            "       " + things.get(things.size() - 1).toString() + "\n" +
                                            String.format("     Now you have %d tasks in the list.\n", things.size()) +
                                            "    ____________________________________________________________");
                        }
                    } else {
                        throw new EmptyDescriptionException("deadline");
                    }
                } else if (line.startsWith("event")) {
                    Matcher m = EVENT_RE.matcher(line);
                    if (m.matches()) {
                        if (m.group(2) == null || m.group(3) == null) {
                            throw new NoDateException();
                        } else {
                            things.add(new Event(m.group(1), m.group(2), m.group(3)));
                            System.out.println(
                                    "    ____________________________________________________________\n" +
                                            "     Got it. I've added this task:\n" +
                                            "       " + things.get(things.size() - 1).toString() + "\n" +
                                            String.format("     Now you have %d tasks in the list.\n", things.size()) +
                                            "    ____________________________________________________________");
                        }
                    } else {
                        throw new EmptyDescriptionException("event");
                    }
                } else if (!line.isEmpty()) {
                    throw new UnknownInputException();
                }
            } catch (EmptyDescriptionException | UnknownInputException | NoDateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
