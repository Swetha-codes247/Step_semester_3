package class_problems;
import java.util.Scanner;

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    // Static helper method to find available room or return null
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom r : rooms) {
            if (r.occupied < r.beds) {
                return r;
            }
        }
        return null;
    }

    /*
     * Explanation:
     * Passing the array into safeAllot() passes the memory reference of the array,
     * not a deep copy of the array or room objects. All modifications happen directly
     * on the actual objects in memory.
     */
    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom available = findAvailableRoom(rooms);
        if (available != null) {
            available.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}

public class RoomAllotment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Case 1: An available room exists
        HostelRoom[] rooms1 = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };

        // Case 2: All rooms full
        HostelRoom[] rooms2 = {
                new HostelRoom("C-214", 3, 3),
                new HostelRoom("C-507", 2, 2)
        };

        System.out.print("Enter student name to allot: ");
        String name = sc.next();

        System.out.println("\n--- Attempt 1 (Rooms Available) ---");
        HostelRoom.safeAllot(rooms1, name);

        System.out.println("\n--- Attempt 2 (All Rooms Full) ---");
        HostelRoom.safeAllot(rooms2, name);
    }
}
