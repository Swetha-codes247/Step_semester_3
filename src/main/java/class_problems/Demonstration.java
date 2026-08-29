package class_problems;
import java.util.Scanner;

// Broken version demonstrating static misuse
class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    /*
     * Why static is wrong here:
     * Marking name, regNo, and attendance as static means a single shared memory location
     * is used for all students. Creating student 2 overwrites student 1's values.
     */
    BrokenStudent(String n, String r, int a) {
        name = n;
        regNo = r;
        attendance = a;
    }
}

// Corrected version
class CorrectStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 10;

    CorrectStudent(String name, int attendance) {
        admissionCount++;
        this.name = name;
        this.regNo = "RA2311003010" + admissionCount;
        this.attendance = attendance;
    }

    void printIdCard() {
        System.out.println(this.name + " | " + this.regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + (admissionCount - 10));
    }
}

public class Demonstration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student 1 Name and Attendance:");
        String name1 = sc.next();
        int att1 = sc.nextInt();

        System.out.println("Enter Student 2 Name and Attendance:");
        String name2 = sc.next();
        int att2 = sc.nextInt();

        // 1. Broken version run
        System.out.println("\n--- Broken Version Demonstration ---");
        BrokenStudent s1 = new BrokenStudent(name1, "101", att1);
        BrokenStudent s2 = new BrokenStudent(name2, "102", att2);
        System.out.println("Student 1 name: " + BrokenStudent.name);
        System.out.println("Student 2 name: " + BrokenStudent.name);

        // 2. Fixed version run
        System.out.println("\n--- Corrected Version Demonstration ---");
        CorrectStudent cs1 = new CorrectStudent(name1, att1);
        CorrectStudent cs2 = new CorrectStudent(name2, att2);
        cs1.printIdCard();
        cs2.printIdCard();
        CorrectStudent.printTotalAdmissions();
    }
}
