package assigment_problems;
import java.util.Scanner;

public class InventoryBalancer {
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        int maxQty = -1;
        String sectionName = "";
        int itemIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > maxQty) {
                maxQty = sectionA[i];
                sectionName = "Section A";
                itemIndex = i + 1; // 1-based item number
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > maxQty) {
                maxQty = sectionB[i];
                sectionName = "Section B";
                itemIndex = i + 1;
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB +
                " | Status: " + status + " | Highest Quantity: " + maxQty + " (" + sectionName + ", Item " + itemIndex + ")");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items per section: ");
        int n = sc.nextInt();

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter quantities for Section A:");
        for (int i = 0; i < n; i++) sectionA[i] = sc.nextInt();

        System.out.println("Enter quantities for Section B:");
        for (int i = 0; i < n; i++) sectionB[i] = sc.nextInt();

        analyzeInventory(sectionA, sectionB);
        sc.close();
    }
}
