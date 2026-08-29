package assigment_problems;
import java.util.Scanner;

// Base Employee Class
class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

// Manager Subclass
class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

// Intern Subclass
class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeTouching{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Plain Employee Input
        System.out.print("Enter Plain Employee Salary: ");
        double plainSalary = sc.nextDouble();
        Employee plain = new Employee("E01", "Plain Emp", plainSalary);

        // Manager Input
        System.out.print("Enter Manager Salary: ");
        double mgrSalary = sc.nextDouble();
        System.out.print("Enter Manager Team Bonus: ");
        double bonus = sc.nextDouble();
        ManagerEmployee manager = new ManagerEmployee("M01", "Manager Emp", mgrSalary, bonus);

        // Intern Input
        System.out.print("Enter Intern Salary: ");
        double internSalary = sc.nextDouble();
        System.out.print("Enter Intern Stipend Cap: ");
        double cap = sc.nextDouble();
        InternEmployee intern = new InternEmployee("I01", "Intern Emp", internSalary, cap);

        // Process using instanceof
        Employee[] employees = { plain, manager, intern };

        System.out.println("\n--- Pay Details ---");
        for (Employee emp : employees) {
            if (emp instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs " + ((ManagerEmployee) emp).effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs " + ((InternEmployee) emp).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }
    }
}
