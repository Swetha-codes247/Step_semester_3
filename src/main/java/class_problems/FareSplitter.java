package class_problems;
import java.util.Arrays;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    // Full constructor
    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (tripId == null || tripId.trim().isEmpty()) {
            throw new IllegalArgumentException("Trip ID cannot be null or empty");
        }
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }
        if (passengerCount <= 0 || passengerCount > 60) {
            throw new IllegalArgumentException("Passenger count must be between 1 and 60");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // 2-argument constructor chained to primary constructor (default passengerCount = 2)
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    // Provisional 1-argument constructor chained to primary constructor
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] shares = new double[passengerCount];
        // Convert to paisa/cents to completely eliminate floating-point precision issues
        long totalInPaisa = Math.round(this.totalFare * 100.0);
        long baseShareInPaisa = totalInPaisa / passengerCount;
        long remainderPaisa = totalInPaisa % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            shares[i] = baseShareInPaisa / 100.0;
        }

        // Distribute remainder paisa starting from the last share backwards
        for (int i = 0; i < remainderPaisa; i++) {
            shares[passengerCount - 1 - i] = Math.round((shares[passengerCount - 1 - i] + 0.01) * 100.0) / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter split1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(split1.fareBreakdown()));

        FareSplitter split2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(split2.fareBreakdown()));
    }
}