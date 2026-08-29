package assigment_problems;
import java.util.Scanner;

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {
        this.occupiedCount++;
        System.out.println(vehicleNo + " allotted to slot " + slotNo);
    }

    // Static helper to find the first free slot
    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null; // Return null if no slot is available
    }

    /*
     * EXPLANATION:
     * In Java, objects and arrays are passed by reference value.
     * Passing 'slots' passes references to the actual memory locations, not duplicates of the objects.
     * Changes inside methods directly modify the original objects.
     */
    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot available = findAvailableSlot(slots);
        if (available != null) {
            available.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
}

public class Parking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter vehicle number: ");
        String vehicleNo = sc.nextLine();

        // Scenario 1: Slot available
        System.out.println("\n--- Testing with available slots ---");
        ParkingSlot[] slots1 = {
                new ParkingSlot("A1", 4, 3),
                new ParkingSlot("A2", 5, 5)
        };
        ParkingSlot.safeAllot(slots1, vehicleNo);

        // Scenario 2: All slots full
        System.out.println("\n--- Testing when all slots are full ---");
        ParkingSlot[] slots2 = {
                new ParkingSlot("A1", 4, 4),
                new ParkingSlot("A2", 5, 5)
        };
        ParkingSlot.safeAllot(slots2, vehicleNo);
    }
}
