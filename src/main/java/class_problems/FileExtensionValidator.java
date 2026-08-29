package class_problems;
import java.util.Scanner;

public class FileExtensionValidator {
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex != -1) {
            String ext = filename.substring(dotIndex + 1);

            if (ext.equalsIgnoreCase("pdf") ||
                    ext.equalsIgnoreCase("docx") ||
                    ext.equalsIgnoreCase("zip")) {
                return "Accepted";
            }
        }

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        System.out.println(validateFileExtension(filename));
        sc.close();
    }
}
