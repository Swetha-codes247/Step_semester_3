package class_problems;
import java.util.Scanner;

// Reused FeeAccount
class CapFeeAccount {
    private double totalFee;
    private double amountPaid;

    public CapFeeAccount(double totalFee, double amountPaid) {
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        } else {
            System.out.println("Payment of Rs " + amount + " rejected (invalid amount).");
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

// Reused HostelFeeAccount
class CapHostelFeeAccount extends CapFeeAccount {
    public CapHostelFeeAccount(double totalFee, double amountPaid) {
        super(totalFee, amountPaid);
    }
}

// Reused HostelRoom
class CapHostelRoom {
    String roomNo;
    int beds;
    int occupied;

    CapHostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    boolean hasSpace() {
        return occupied < beds;
    }

    void occupy() {
        occupied++;
    }
}

// Composition Student Class
class SrmStudentCapstone {
    String name;
    String regNo;
    CapHostelFeeAccount feeAccount;
    CapHostelRoom room;

    static int totalStudents = 0;

    public SrmStudentCapstone(String name, String regNo, CapHostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null; // unallotted initially
        totalStudents++;
    }

    public void assignRoom(CapHostelRoom room) {
        if (room != null && room.hasSpace()) {
            room.occupy();
            this.room = room;
        }
    }

    public String fullStatus() {
        String roomStr = (this.room != null) ? this.room.roomNo : "unallotted";
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStr;
    }
}

public class Capstone {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create 2 available rooms
        CapHostelRoom room1 = new CapHostelRoom("C-214", 1, 0);
        CapHostelRoom room2 = new CapHostelRoom("C-507", 1, 0);

        // Read and create 3 students
        SrmStudentCapstone[] students = new SrmStudentCapstone[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Student " + (i + 1) + " (Name RegNo TotalFee InitialPaid):");
            String name = sc.next();
            String regNo = sc.next();
            double totalFee = sc.nextDouble();
            double paid = sc.nextDouble();
            students[i] = new SrmStudentCapstone(name, regNo, new CapHostelFeeAccount(totalFee, paid));
        }

        // Allot rooms to only the first two students
        students[0].assignRoom(room1);
        students[1].assignRoom(room2);

        // Process a payment (including a negative rejected payment)
        System.out.println("\nProcessing payments...");
        students[0].feeAccount.pay(60000);
        students[1].feeAccount.pay(20000);
        students[2].feeAccount.pay(-5000); // Rejected payment

        // Print statuses
        System.out.println("\n--- Final Status ---");
        for (SrmStudentCapstone s : students) {
            System.out.println(s.fullStatus());
        }
        System.out.println("Total students: " + SrmStudentCapstone.totalStudents);
    }
}
