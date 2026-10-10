package assigment_problems;

import java.util.Scanner;
import java.util.HashMap;

public class CountPeriods {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();

        int[] transactions = new int[n];

        System.out.println("Enter transactions:");
        for (int i = 0; i < n; i++) {
            transactions[i] = sc.nextInt();
        }

        System.out.print("Enter target k: ");
        long k = sc.nextLong();

        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        long prefixSum = 0;
        long count = 0;

        for (int i = 0; i < n; i++) {
            prefixSum += transactions[i];

            count += map.getOrDefault(prefixSum - k, 0);

            map.put(prefixSum,
                    map.getOrDefault(prefixSum, 0) + 1);
        }

        System.out.println("Number of periods: " + count);

        sc.close();
    }
}

