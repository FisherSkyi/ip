import java.util.Scanner;

public class Seb {
    private final String name = "Seb";

    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        System.out.println(
                "    ____________________________________________________________\n" +
                "     Hello! I'm [YOUR CHATBOT NAME]\n" +
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
            if (!line.isEmpty()) {
                System.out.println(line);
            }
        }
    }
}
