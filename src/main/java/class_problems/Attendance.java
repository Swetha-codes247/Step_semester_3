package class_problems;
import java.util.Scanner;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    // Constructor to initialize student details
    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    // Instance method: updates individual attendance
    void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    // Instance method: depends on individual student's attendance
    boolean isEligible() {
        return this.attendance >= 75;
    }

    /*
     * Justification:
     * classAverage is static because the average attendance belongs to the entire
     * class/batch as a whole, not to a single student.
     * isEligible is non-static (instance method) because it checks the specific
     * attendance value of an individual student object.
     */
    static double classAverage(SrmStudent[] students) {
        int sum = 0;
        for (SrmStudent s : students) {
            sum += s.attendance;
        }
        return (double) sum / students.length;
    }
}

public class Attendance{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SrmStudent[] students = new SrmStudent[5];

        System.out.println("Enter details for 5 students (Name RegNo Attendance):");
        for (int i = 0; i < 5; i++) {
            String name = sc.next();
            String regNo = sc.next();
            int att = sc.nextInt();
            students[i] = new SrmStudent(name, regNo, att);
        }

        System.out.println("\n--- Attendance Status ---");
        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " " + s.attendance + "% " + status);
        }

        System.out.printf("Class average: %.1f%%\n", SrmStudent.classAverage(students));
    }
}
