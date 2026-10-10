package assigment_problems;

import java.util.Scanner;

public class FootfallReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of hours: ");
        int n = sc.nextInt();

        int[] visitors = new int[n];
        int[] prefix = new int[n + 1];

        System.out.println("Enter visitors per hour:");
        for (int i = 0; i < n; i++) {
            visitors[i] = sc.nextInt();
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        System.out.print("Enter number of queries: ");
        int q = sc.nextInt();

        System.out.println("Enter start and end indices:");
        for (int i = 0; i < q; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            if (start < 0 || end >= n || start > end) {
                System.out.println("Invalid range");
            } else {
                int total = prefix[end + 1] - prefix[start];
                System.out.println("Total footfall: " + total);
            }
        }

        sc.close();
    }
}

