import java.util.ArrayList;
import java.util.Scanner;

public class Seb {
    private final String name = "Seb";
    private static ArrayList<String> things = new ArrayList<>();

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
            }
            if (line.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println(String.format("     %d. %s",i, things.get(i)));
                }
                System.out.println("    ____________________________________________________________");
            } else if (!line.isEmpty()) {
                things.add(line);
                System.out.println(
                        "    ____________________________________________________________\n" +
                        String.format("     added: %s\n", line) +
                        "    ____________________________________________________________");
            }
        }
    }
}
