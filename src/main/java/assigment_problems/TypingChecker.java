package assigment_problems;
import java.util.Scanner;

public class TypingChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        int matches = 0;
        int firstMismatchPos = -1;
        char origChar = ' ', typedChar = ' ';

        int len = Math.min(original.length(), typed.length());

        for (int i = 0; i < len; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matches++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based position
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matches / original.length()) * 100;

        System.out.print("Matched: " + matches + "/" + original.length() + " | Accuracy: " + String.format("%.2f", accuracy) + "% | ");
        if (firstMismatchPos == -1 && original.length() == typed.length()) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + firstMismatchPos + " ('" + origChar + "' vs '" + typedChar + "')");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original text: ");
        String original = sc.nextLine();

        System.out.print("Enter typed text: ");
        String typed = sc.nextLine();

        checkTypingAccuracy(original, typed);
        sc.close();
    }
}
