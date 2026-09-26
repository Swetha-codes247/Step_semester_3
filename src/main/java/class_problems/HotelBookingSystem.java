package class_problems;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// Customer Class
class Customer {
    String name;
    public Customer(String name) {
        this.name = name;
    }
}

// Room Class with Categories and Pricing Strategy
class Room {
    String roomNumber;
    String category;
    double pricePerDay;

    public Room(String roomNumber, String category, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerDay = pricePerDay;
    }

    public double calculatePrice(LocalDate checkIn, LocalDate checkOut) {
        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        return days * pricePerDay;
    }
}

// Reservation Class
class Reservation {
    String reservationId;
    Room room;
    Customer customer;
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDate cancellationDeadline;
    String status; // "Active", "Cancelled"

    public Reservation(String reservationId, Room room, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        this.reservationId = reservationId;
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cancellationDeadline = checkIn.minusDays(1); // Deadline is 1 day before check-in
        this.status = "Active";
    }

    public void cancel(LocalDate currentDate) {
        if (currentDate.isBefore(cancellationDeadline) || currentDate.isEqual(cancellationDeadline)) {
            this.status = "Cancelled";
            System.out.println("Reservation for " + room.roomNumber + " cancelled successfully.");
        } else {
            System.out.println("Cancellation failed: Past the cancellation deadline.");
        }
    }
}

// Booking Manager (Handles rooms, overlapping check, and reservations)
class BookingManager {
    List rooms = new ArrayList<>();
    List reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void bookRoom(String reservationId, String roomNumber, Customer customer, LocalDate checkIn, LocalDate checkOut) {
        Room targetRoom = null;
        for (Room r : rooms) {
            if (r.roomNumber.equals(roomNumber)) {
                targetRoom = r;
                break;
            }
        }

        if (targetRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        // Check for overlapping reservations
        for (Reservation res : reservations) {
            if (res.room.roomNumber.equals(roomNumber) && res.status.equals("Active")) {
                // Overlap condition: (StartA <= EndB) and (EndA >= StartB)
                boolean isOverlapping = !checkOut.isBefore(res.checkIn) && !checkIn.isAfter(res.checkOut);
                if (isOverlapping) {
                    System.out.println("Booking failed: " + roomNumber + " is not available for " + checkIn + " to " + checkOut + ".");
                    return;
                }
            }
        }

        // If no overlap, create reservation
        Reservation newRes = new Reservation(reservationId, targetRoom, customer, checkIn, checkOut);
        reservations.add(newRes);
        double totalPrice = targetRoom.calculatePrice(checkIn, checkOut);
        System.out.printf("%s booked from %s to %s. Total price: $%.2f.\n", roomNumber, checkIn, checkOut, totalPrice);
    }

    public Reservation findReservation(String roomNumber) {
        for (Reservation res : reservations) {
            if (res.room.roomNumber.equals(roomNumber) && res.status.equals("Active")) {
                return res;
            }
        }
        return null;
    }
}

// Main Execution Class
public class HotelBookingSystem {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();

        // Setup Rooms
        Room deluxe101 = new Room("Deluxe Room 101", "Deluxe", 200.0);
        Room standard205 = new Room("Standard Room 205", "Standard", 150.0);
        manager.addRoom(deluxe101);
        manager.addRoom(standard205);

        Customer customer = new Customer("John Doe");

        // 1. Customer requests to book 'Deluxe Room 101' from 2024-12-01 to 2024-12-05[cite: 2]
        manager.bookRoom("R1", "Deluxe Room 101", customer, LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5));

        // 2. Customer requests to book 'Standard Room 205' from 2024-12-03 to 2024-12-07[cite: 2]
        manager.bookRoom("R2", "Standard Room 205", customer, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));

        // 3. Customer attempts to book 'Deluxe Room 101' from 2024-12-03 to 2024-12-07 (overlapping)[cite: 2]
        manager.bookRoom("R3", "Deluxe Room 101", customer, LocalDate.of(2024, 12, 3), LocalDate.of(2024, 12, 7));

        // 4. Customer cancels reservation for 'Deluxe Room 101' (before cancellation point)[cite: 2]
        Reservation resToCancel = manager.findReservation("Deluxe Room 101");
        if (resToCancel != null) {
            // Simulating a cancellation date before the deadline (e.g., 2024-11-28)
            resToCancel.cancel(LocalDate.of(2024, 11, 28));
        }
    }
}
