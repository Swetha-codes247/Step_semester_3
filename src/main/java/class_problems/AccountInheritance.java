package class_problems;
import java.util.Scanner;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        double due = getDue();
        return due - (due * (scholarshipPercent / 100.0));
    }
}

public class AccountInheritance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Plain Fee Account
        System.out.println("Enter Plain Account (RegNo TotalFee Paid):");
        FeeAccount plain = new FeeAccount(sc.next(), sc.nextDouble(), sc.nextDouble());

        // Hostel Fee Account
        System.out.println("Enter Hostel Account (RegNo TotalFee Paid):");
        HostelFeeAccount hostel = new HostelFeeAccount(sc.next(), sc.nextDouble(), sc.nextDouble());

        // Scholarship Fee Account
        System.out.println("Enter Scholarship Account (RegNo TotalFee Paid Scholarship%):");
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount(sc.next(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());

        // Process output using instanceof
        FeeAccount[] accounts = { plain, hostel, scholarship };

        System.out.println("\n--- Due Details ---");
        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + s.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}
