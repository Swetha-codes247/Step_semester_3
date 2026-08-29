package class_problems;
import java.util.Scanner;

public class PhoneMasker {
    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String last4 = phone.substring(6);
        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append("-").append(last4);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 10-digit phone number: ");
        String phone = sc.nextLine().trim();

        System.out.println(maskPhoneNumber(phone));
        sc.close();
    }
}
