package assigment_problems;
import java.util.*;

class Student {
    String name;
    String type;
    int currentCredits, maxCredits;
    public Student(String name, String type, int currentCredits, int maxCredits) {
        this.name = name; this.type = type; this.currentCredits = currentCredits; this.maxCredits = maxCredits;
    }
}

class Elective {
    String name;
    int credits, capacity;
    List enrolled = new ArrayList<>();
    Queue waitlist = new LinkedList<>();

    public Elective(String name, int credits, int capacity) {
        this.name = name; this.credits = credits; this.capacity = capacity;
    }

    public void enroll(Student s) {
        if (s.currentCredits + credits > s.maxCredits) {
            System.out.println("Enrollment failed: " + s.name + " would exceed the " + s.type + " credit limit (" + (s.currentCredits + credits) + "/" + s.maxCredits + ").");
            return;
        }
        if (enrolled.size() < capacity) {
            enrolled.add(s);
            s.currentCredits += credits;
            System.out.println(s.name + " enrolled in " + name + " (credits: " + s.currentCredits + "/" + s.maxCredits + ").");
        } else {
            System.out.println(name + " is full.");
            waitlist.add(s);
            System.out.println(s.name + " added to waitlist (position " + waitlist.size() + ").");
        }
    }

    public void drop(Student s) {
        if (enrolled.remove(s)) {
            s.currentCredits -= credits;
            System.out.println(s.name + " dropped " + name + " (credits: " + s.currentCredits + "/" + s.maxCredits + ").");
            if (!waitlist.isEmpty()) {
                Student next = waitlist.poll();
                enrolled.add(next);
                next.currentCredits += credits;
                System.out.println(next.name + " promoted from waitlist and enrolled in " + name + " (credits: " + next.currentCredits + "/" + next.maxCredits + ").");
            }
        }
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);
        Student asha = new Student("Asha", "Regular", 20, 24);
        Student ravi = new Student("Ravi", "Honors", 22, 28);
        Student neha = new Student("Neha", "Exchange", 12, 20);
        Student kiran = new Student("Kiran", "Regular", 22, 24);

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);
        cloud.drop(asha);
    }
}
