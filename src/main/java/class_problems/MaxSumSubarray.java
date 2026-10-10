package class_problems;

import java.util.Scanner;

public class MaxSumSubarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] sales = new int[n];

        System.out.println("Enter sales values:");
        for (int i = 0; i < n; i++) {
            sales[i] = sc.nextInt();
        }

        System.out.print("Enter window size k: ");
        int k = sc.nextInt();

        if (k < 1 || k > n) {
            System.out.println("Invalid k");
            sc.close();
            return;
        }

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }

        int maxSum = sum;

        for (int i = k; i < n; i++) {
            sum = sum + sales[i] - sales[i - k];

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        System.out.println("Maximum sum: " + maxSum);

        sc.close();
    }
}

