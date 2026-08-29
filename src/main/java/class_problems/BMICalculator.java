package class_problems;
import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of people: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("\nPerson " + i + " - Enter height in meters (e.g. 1.75): ");
            double height = sc.nextDouble();

            System.out.print("Person " + i + " - Enter weight in kg (e.g. 70): ");
            double weight = sc.nextDouble();

            double bmi = weight / (height * height);

            String status;
            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi <= 24.9) {
                status = "Normal";
            } else if (bmi <= 29.9) {
                status = "Overweight";
            } else {
                status = "Obese";
            }

            System.out.printf("Person %d -> BMI: %.2f | Status: %s\n", i, bmi, status);
        }

        sc.close();
    }
}
