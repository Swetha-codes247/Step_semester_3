package class_problems;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn;

    // Parameterized constructor acting as a validation gate
    public BusTicket(String passengerName, String destination) {
        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name: " + passengerName);
        }
        if (!isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination: " + destination);
        }
        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidName(String name) {
        // Name must not be null/blank and should only contain alphabetic characters and spaces
        return name != null && !name.trim().isEmpty() && name.trim().matches("^[a-zA-Z ]+$");
    }

    private static boolean isValidDestination(String destination) {
        // Destination must not be null/blank and should contain valid alphabetic city names
        return destination != null && !destination.trim().isEmpty() && destination.trim().matches("^[a-zA-Z ]+$");
    }

    public void markCheckedIn() {
        if (this.checkedIn) {
            throw new IllegalStateException("Ticket already checked in.");
        }
        this.checkedIn = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusTicket busTicket = (BusTicket) o;
        return passengerName.equalsIgnoreCase(busTicket.passengerName) &&
                destination.equalsIgnoreCase(busTicket.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerName.toLowerCase(), destination.toLowerCase());
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicatesSkipped = 0;

        Set<BusTicket> acceptedBookings = new HashSet<>();

        if (rawBookings != null) {
            for (String[] booking : rawBookings) {
                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                String name = booking[0];
                String dest = booking[1];

                try {
                    BusTicket ticket = new BusTicket(name, dest);
                    if (acceptedBookings.contains(ticket)) {
                        duplicatesSkipped++;
                    } else {
                        acceptedBookings.add(ticket);
                        valid++;
                    }
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicatesSkipped);
    }

    public static void main(String[] args) {
        String[][] raw = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };
        processBatch(raw);
    }
}
