package class_problems;

import java.util.Scanner;

public class WarehouseSummary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows and columns: ");
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];

        System.out.println("Enter grid elements:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int total = 0;
        int max = -1;
        int maxRow = 0, maxCol = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Total items: " + total);
        System.out.println("Maximum bin: (" + maxRow + ", " + maxCol + ")");
        System.out.println("Maximum items: " + max);

        sc.close();
    }
}

