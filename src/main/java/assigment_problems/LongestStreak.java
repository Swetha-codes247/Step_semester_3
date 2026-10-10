package assigment_problems;

import java.util.Scanner;

public class LongestStreak {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        int[] costs = new int[n];

        System.out.println("Enter daily costs:");
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }

        System.out.print("Enter budget: ");
        long budget = sc.nextLong();

        int left = 0;
        int bestLength = 0;
        int bestStart = -1;
        long sum = 0;

        for (int right = 0; right < n; right++) {
            sum += costs[right];

            while (sum > budget && left <= right) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }

        System.out.println("Length: " + bestLength);
        System.out.println("Start Index: " + bestStart);

        sc.close();
    }
}

