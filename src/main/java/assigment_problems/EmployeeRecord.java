package assigment_problems;
import java.util.Scanner;

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    // Static counter across all instances
    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public void fullProfile() {
        double pay = employee.getSalary();
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        }

        String slotInfo = (slot != null) ? slot.slotNo : "no parking assigned";
        System.out.println(name + " | Pay: Rs " + pay + " | Slot: " + slotInfo);
    }
}

public class EmployeeRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User Inputs for 3 employees
        System.out.println("Enter Manager Details (Name, Salary, Bonus):");
        String mName = sc.nextLine();
        double mSalary = sc.nextDouble();
        double mBonus = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nEnter Regular Employee Details (Name, Salary):");
        String eName = sc.nextLine();
        double eSalary = sc.nextDouble();
        sc.nextLine();

        System.out.println("\nEnter Intern Details (Name, Salary, Stipend Cap):");
        String iName = sc.nextLine();
        double iSalary = sc.nextDouble();
        double iCap = sc.nextDouble();

        // Create Employee objects
        Employee emp1 = new ManagerEmployee("M01", mName, mSalary, mBonus);
        Employee emp2 = new Employee("E01", eName, eSalary);
        Employee emp3 = new InternEmployee("I01", iName, iSalary, iCap);

        // Create Slots (leaving the 3rd unassigned)
        ParkingSlot slot1 = new ParkingSlot("A1", 1, 1);
        ParkingSlot slot2 = new ParkingSlot("A2", 1, 1);

        // Create Company Records
        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord(mName, "REC-01", emp1, slot1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord(eName, "REC-02", emp2, slot2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord(iName, "REC-03", emp3, null);

        System.out.println("\n--- All Employee Profiles ---");
        r1.fullProfile();
        r2.fullProfile();
        r3.fullProfile();

        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
