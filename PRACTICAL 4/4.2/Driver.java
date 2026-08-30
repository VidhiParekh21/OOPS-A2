import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of log lines: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] logs = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter log " + (i + 1) + ": ");
            logs[i] = sc.nextLine();
        }

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        ChatFilter.filter(logs, keyword);

        sc.close();
    }
}
