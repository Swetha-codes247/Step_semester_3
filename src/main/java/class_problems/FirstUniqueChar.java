package class_problems;
import java.util.Scanner;

public class FirstUniqueChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        char result = ' ';

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // If the character's first index is equal to its last index, it only appears once
            if (text.indexOf(c) == text.lastIndexOf(c)) {
                result = c;
                break;
            }
        }

        if (result != ' ') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        sc.close();
    }
}
