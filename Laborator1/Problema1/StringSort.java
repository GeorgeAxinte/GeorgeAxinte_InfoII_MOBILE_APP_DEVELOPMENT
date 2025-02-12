import java.util.Scanner;
public class StringSort {
    public static String sortString(String s) {
        StringBuilder lower = new StringBuilder();
        StringBuilder upper = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower.append(c);
            } else {
                upper.append(c);
            }
        }

        return lower.toString() + upper.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introdu un text: ");
        String input = scanner.nextLine();

        String output = sortString(input);
        System.out.println("Rezultat: " + output);

        scanner.close();
    }
}