package assigment_problems;

import java.util.Scanner;

public class CountInBand {

    static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] scores = new int[n];

        System.out.println("Enter scores in ascending order:");
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        System.out.print("Enter low and high: ");
        int low = sc.nextInt();
        int high = sc.nextInt();

        if (low > high) {
            System.out.println("Invalid range");
        } else {
            int first = lowerBound(scores, low);
            int last = upperBound(scores, high);

            System.out.println("Number of students: " + (last - first));
        }

        sc.close();
    }
}

