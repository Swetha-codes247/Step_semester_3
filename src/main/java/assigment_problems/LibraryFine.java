package assigment_problems;
import java.util.Scanner;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    // Constructor
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Instance method: calculates fine for this specific book
    public double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5.0 : 0.0;
    }

    // Instance method: checks if this specific book is severely overdue
    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    /*
     * JUSTIFICATION:
     * 'fineAmount()' is an instance method because fine depends on an individual book's overdue days.
     * 'totalFineCollected()' is static because it computes the overall total fine across all books,
     * belonging to the class as a whole rather than a single book.
     */
    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }
        return total;
    }
}

public class LibraryFine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookIssue[] issues = new BookIssue[5];

        System.out.println("Enter details for 5 books (Title, Borrower Name, Days Overdue):");
        for (int i = 0; i < 5; i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Borrower Name: ");
            String name = sc.nextLine();
            System.out.print("Days Overdue: ");
            int days = sc.nextInt();
            sc.nextLine(); // Clear buffer

            issues[i] = new BookIssue(title, name, days);
        }

        System.out.println("\n--- Book Status ---");
        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issue.title + " " + issue.daysOverdue + " days - " + status);
        }

        System.out.println("\nTotal fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}
