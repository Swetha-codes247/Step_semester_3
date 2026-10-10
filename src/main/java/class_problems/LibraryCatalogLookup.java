package class_problems;

import java.util.Scanner;

public class LibraryCatalogLookup {
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

        System.out.print("Enter ISBN: ");
        String target = sc.next();

        int low = 0;
        int high = isbn.length - 1;
        int position = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isbn[mid].equals(target)) {
                position = mid;
                break;
            } else if (isbn[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (position >= 0) {
            System.out.println(title[position]);
        } else {
            System.out.println("Not Found");
        }

        sc.close();
    }
}

