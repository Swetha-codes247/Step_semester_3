package class_problems;

import java.util.Scanner;

public class LibraryCatalog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] isbn = {
                "0001112223",
                "0002223334",
                "0003334445",
                "0004445556",
                "0005556667"
        };

        String[] title = {
                "Introduction to Algebra",
                "Beginning Python",
                "Classic Mythology",
                "Data and Society",
                "European History"
        };

        System.out.print("Enter ISBN to search: ");
        String target = sc.next();

        int left = 0;
        int right = isbn.length - 1;
        int found = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = isbn[mid].compareTo(target);

            if (result == 0) {
                found = mid;
                break;
            } else if (result < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (found != -1) {
            System.out.println("Book Title: " + title[found]);
        } else {
            System.out.println("Not Found");
        }

        sc.close();
    }
}

