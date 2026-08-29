package assigment_problems;
import java.util.Scanner;

// 1. Broken Version (Static misuse)
class BrokenLibraryMember {
    /*
     * WHY STATIC IS WRONG HERE:
     * 'name', 'memberId', and 'booksIssued' describe a specific individual person.
     * Making them static creates only ONE copy in memory for the entire class,
     * so creating a new member overwrites previous members' data.
     */
    static String name;

    public BrokenLibraryMember(String name) {
        BrokenLibraryMember.name = name;
    }
}

// 2. Fixed Version
class FixedLibraryMember {
    // Instance fields (unique for every member)
    String name;
    String memberId;
    int booksIssued;

    // Static fields (shared by the whole library system)
    static String libraryName = "City Central Library";
    static int memberCount = 1000;

    public FixedLibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    public void printMemberCard() {
        System.out.println(this.name + " \t" + this.memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + (memberCount - 1000));
    }
}

public class LibraryMember {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name for Member 1: ");
        String name1 = sc.nextLine();
        System.out.print("Enter Name for Member 2: ");
        String name2 = sc.nextLine();

        // Broken Demo
        System.out.println("\n--- Broken Version Output (Overwrites data) ---");
        BrokenLibraryMember b1 = new BrokenLibraryMember(name1);
        BrokenLibraryMember b2 = new BrokenLibraryMember(name2);
        System.out.println("Member 1 name read: " + BrokenLibraryMember.name);
        System.out.println("Member 2 name read: " + BrokenLibraryMember.name);

        // Fixed Demo
        System.out.println("\n--- Fixed Version Output (Independent data) ---");
        FixedLibraryMember f1 = new FixedLibraryMember(name1, 2);
        FixedLibraryMember f2 = new FixedLibraryMember(name2, 1);
        f1.printMemberCard();
        f2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}
