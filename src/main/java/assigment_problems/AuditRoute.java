package assigment_problems;

import java.util.Scanner;

public class AuditRoute {
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

        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        System.out.print("Spiral order: ");

        while (top <= bottom && left <= right) {

            for (int j = left; j <= right; j++) {
                System.out.print(grid[top][j] + " ");
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                System.out.print(grid[i][right] + " ");
            }
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(grid[bottom][j] + " ");
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(grid[i][left] + " ");
                }
                left++;
            }
        }

        sc.close();
    }
}

