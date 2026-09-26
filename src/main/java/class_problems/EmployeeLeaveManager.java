package class_problems;
class LeaveRequest {
    String employeeName, status = "Pending";
    public LeaveRequest(String name) { this.employeeName = name; }
    public void review(String newStatus) {
        if ("Pending".equals(status)) {
            status = newStatus;
            System.out.println("Leave request for " + employeeName + " " + status.toLowerCase() + ". Status: " + status + ".");
        } else {
            System.out.println("Cannot change status: Approved request cannot revert to Pending.");
        }
    }
}

public class EmployeeLeaveManager {
    public static void main(String[] args) {
        System.out.println("Leave request submitted by John Doe for 2024-10-10 to 2024-10-12. Status: Pending.");
        LeaveRequest req1 = new LeaveRequest("John Doe");
        req1.review("Approved");

        System.out.println("Leave request submitted by Jane Smith for 2024-11-01 to 2024-11-05. Status: Pending.");
        LeaveRequest req2 = new LeaveRequest("Jane Smith");

        // Attempt to revert John Doe's status back to pending
        req1.review("Pending");
    }
}
